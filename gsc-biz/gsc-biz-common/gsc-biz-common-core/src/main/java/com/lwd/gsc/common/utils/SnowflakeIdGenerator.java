package com.lwd.gsc.common.utils;

import lombok.extern.slf4j.Slf4j;
import java.net.*;
import java.util.Enumeration;
import java.util.Random;
import java.lang.management.ManagementFactory;

/**
 * 雪花算法ID生成器（单例模式）
 * 核心功能：生成全局唯一、时间有序的64位ID，适配容器和主机环境
 * 实现原理：基于"时间戳+数据中心ID+机器ID+序列号"的组合生成ID
 */
@Slf4j
public final class SnowflakeIdGenerator {
    /** 起始时间戳（毫秒）：2020-01-01 00:00:00，用于减少ID长度 */
    private static final long TWEPOCH = 1577808000000L;

    /** 机器ID位数：5位，支持最大31个机器节点 */
    private static final long WORKER_ID_BITS = 5L;

    /** 数据中心ID位数：5位，支持最大31个数据中心 */
    private static final long DATA_CENTER_ID_BITS = 5L;

    /** 序列号位数：12位，支持每毫秒最多生成4096个ID */
    private static final long SEQUENCE_BITS = 12L;

    /** 时钟回拨容忍值（毫秒）：允许系统时钟回拨的最大时间 */
    private static final long CLOCK_BACKWARD_TOLERANCE = 20L;

    /** 最大机器ID：31（由5位二进制计算得出） */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /** 最大数据中心ID：31（由5位二进制计算得出） */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /** 序列号掩码：4095（用于限制序列号在0-4095范围内） */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /** 机器ID左移位数：12位（跳过序列号的位数） */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /** 数据中心ID左移位数：17位（跳过序列号+机器ID的位数） */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /** 时间戳左移位数：22位（跳过序列号+机器ID+数据中心ID的位数） */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /** 单例实例（使用volatile确保可见性） */
    private static volatile SnowflakeIdGenerator INSTANCE;

    /** 机器ID：基于PID生成，确保同一机器不同进程的唯一性 */
    private final long workerId;

    /** 数据中心ID：基于IP生成，确保不同机器的唯一性 */
    private final long dataCenterId;

    /** 上次生成ID的时间戳（毫秒）：用于控制序列号自增 */
    private long lastTimestamp = -1L;

    /** 当前毫秒内的序列号：用于同一毫秒内生成多个ID时区分 */
    private long sequence = 0L;

    /** 随机数生成器：用于序列号初始化，减少ID规律性 */
    private final Random random = new Random();

    /**
     * 私有构造方法：禁止外部实例化
     * 初始化机器ID和数据中心ID，并校验合法性
     */
    private SnowflakeIdGenerator() {
        this.workerId = generateWorkerId();
        this.dataCenterId = generateDataCenterId();
        validateIds();
        log.info("雪花算法初始化完成 - 机器ID: {}, 数据中心ID: {}", workerId, dataCenterId);
    }

    /**
     * 获取单例实例（双重校验锁模式）
     * @return 全局唯一的雪花算法生成器实例
     */
    public static SnowflakeIdGenerator getInstance() {
        // 第一重校验：无锁快速判断实例是否已初始化
        if (INSTANCE == null) {
            // 同步块：确保只有一个线程进入初始化逻辑
            synchronized (SnowflakeIdGenerator.class) {
                // 第二重校验：防止多个线程同时等待锁时重复初始化
                if (INSTANCE == null) {
                    // 使用volatile禁止指令重排序，确保初始化安全
                    INSTANCE = new SnowflakeIdGenerator();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 生成下一个唯一ID（线程安全）
     * @return 64位长整型ID
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        handleClockBackward(timestamp);
        handleSequence(timestamp);
        lastTimestamp = timestamp;
        return combineId(timestamp);
    }

    /**
     * 生成字符串形式的唯一ID
     * @return 字符串类型的ID
     */
    public String nextIdStr() {
        return Long.toString(nextId());
    }

    /**
     * 处理时钟回拨：当系统时钟发生回退时的防护逻辑
     * @param timestamp 当前时间戳
     */
    private void handleClockBackward(long timestamp) {
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset > CLOCK_BACKWARD_TOLERANCE) {
                log.error("时钟回拨异常：回拨{}ms，超过容忍值{}ms", offset, CLOCK_BACKWARD_TOLERANCE);
                throw new IllegalStateException("时钟回拨超出容忍范围");
            }
            // 轻微回拨：使用上次时间戳继续生成，避免ID重复
            timestamp = lastTimestamp;
        }
    }

    /**
     * 处理序列号：控制同一毫秒内的序列号生成逻辑
     * @param timestamp 当前时间戳
     */
    private void handleSequence(long timestamp) {
        if (timestamp == lastTimestamp) {
            // 同一毫秒内：序列号自增，达到最大值时等待下一毫秒
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = waitNextMillis();
            }
        } else {
            // 不同毫秒：重置序列号为0，保持趋势递增性
            sequence = 0;
        }
    }

    /**
     * 等待至下一毫秒：当序列号达到最大值时调用
     * @return 下一毫秒的时间戳
     */
    private long waitNextMillis() {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            try {
                // 短暂休眠，减少CPU占用
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 组合生成最终ID：通过位运算将各部分组合为64位ID
     * @param timestamp 当前时间戳
     * @return 组合后的唯一ID
     */
    private long combineId(long timestamp) {
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * 生成机器ID：基于进程PID或环境变量
     * 优先级：环境变量PID → JVM进程ID → 随机值
     * @return 0-31之间的机器ID
     */
    private long generateWorkerId() {
        try {
            // 优先从环境变量获取（容器环境推荐）
            String pidEnv = System.getenv("PID");
            if (pidEnv != null) {
                long workerId = Long.parseLong(pidEnv) % (MAX_WORKER_ID + 1);
                log.info("从环境变量PID获取WorkerID: {}", workerId);
                return workerId;
            }

            // 从JVM进程信息获取
            String jvmName = ManagementFactory.getRuntimeMXBean().getName();
            String pid = jvmName.split("@")[0];
            long workerId = Long.parseLong(pid) % (MAX_WORKER_ID + 1);
            log.info("从JVM进程信息获取WorkerID: {}", workerId);
            return workerId;
        } catch (NumberFormatException e) {
            log.error("WorkerID格式错误，使用随机值", e);
            return random.nextLong(MAX_WORKER_ID + 1);
        } catch (Exception e) {
            log.error("获取WorkerID失败，使用随机值", e);
            return random.nextLong(MAX_WORKER_ID + 1);
        }
    }

    /**
     * 生成数据中心ID：基于IP地址或环境变量
     * 优先级：环境变量DATA_CENTER_ID → IP地址 → 随机值
     * @return 0-31之间的数据中心ID
     */
    private long generateDataCenterId() {
        try {
            // 优先从环境变量获取（容器环境推荐）
            String dcEnv = System.getenv("DATA_CENTER_ID");
            if (dcEnv != null) {
                return Long.parseLong(dcEnv) % (MAX_DATA_CENTER_ID + 1);
            }

            // 从有效IP地址计算
            String ip = getValidIpAddress();
            if (ip != null) {
                String[] segments = ip.split("\\.");
                return Long.parseLong(segments[segments.length - 1]) % (MAX_DATA_CENTER_ID + 1);
            }
        } catch (Exception e) {
            // 异常处理：生成随机ID
            log.warn("生成DataCenterID失败，使用随机值 - 原因: {}", e.getMessage());
        }
        return random.nextLong(MAX_DATA_CENTER_ID + 1);
    }

    /**
     * 获取有效IP地址：排除虚拟网卡和回环地址
     * @return 有效的IPv4地址，失败时返回null
     */
    private String getValidIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                // 跳过虚拟网卡和禁用的网卡
                if (ni.isVirtual() || !ni.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    // 筛选有效IPv4地址
                    if (addr instanceof Inet4Address
                            && !addr.isLoopbackAddress()
                            && !addr.isLinkLocalAddress()) {
                        return addr.getHostAddress();
                    }
                }
            }
            // 兜底：返回本地主机地址
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.warn("获取有效IP地址失败", e);
            return null;
        }
    }

    /**
     * 校验机器ID和数据中心ID的合法性
     * 确保生成的ID在有效范围内（0-31）
     */
    private void validateIds() {
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException("WorkerID超出范围：" + workerId);
        }
        if (dataCenterId < 0 || dataCenterId > MAX_DATA_CENTER_ID) {
            throw new IllegalArgumentException("DataCenterID超出范围：" + dataCenterId);
        }
    }

    // 以下为调试用Getter方法
    public long getWorkerId() {
        return workerId;
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }
}
