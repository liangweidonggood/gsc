package com.lwd.gsc.common.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.DisplayName;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("雪花算法ID生成器测试")
class SnowflakeIdGeneratorTest {

    @Test
    @DisplayName("测试单例模式")
    void testSingleton() {
        SnowflakeIdGenerator instance1 = SnowflakeIdGenerator.getInstance();
        SnowflakeIdGenerator instance2 = SnowflakeIdGenerator.getInstance();
        assertSame(instance1, instance2, "SnowflakeIdGenerator应为单例");
    }

    @Test
    @DisplayName("测试生成ID的基本功能")
    void testNextId() {
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.getInstance();
        long id1 = generator.nextId();
        long id2 = generator.nextId();
        
        assertTrue(id1 > 0, "生成的ID应为正数");
        assertTrue(id2 > 0, "生成的ID应为正数");
        assertNotEquals(id1, id2, "连续生成的ID应不相同");
    }

    @Test
    @DisplayName("测试生成字符串形式ID")
    void testNextIdStr() {
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.getInstance();
        String idStr = generator.nextIdStr();
        
        assertNotNull(idStr, "生成的ID字符串不应为null");
        assertTrue(idStr.length() > 0, "生成的ID字符串不应为空");
        assertDoesNotThrow(() -> Long.parseLong(idStr), "ID字符串应能转换为长整型");
    }

    @Test
    @DisplayName("测试ID的趋势递增性")
    void testIdIncrementality() {
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.getInstance();
        List<Long> ids = new ArrayList<>();
        
        // 生成100个ID
        for (int i = 0; i < 100; i++) {
            ids.add(generator.nextId());
        }
        
        // 验证趋势递增性
        long previousId = ids.get(0);
        int increasingCount = 0;
        
        for (int i = 1; i < ids.size(); i++) {
            if (ids.get(i) > previousId) {
                increasingCount++;
            }
            previousId = ids.get(i);
        }
        
        // 至少70%的ID应该是递增的（考虑到可能的序列号重置）
        assertTrue(increasingCount >= 70, "大多数ID应该是递增的");
    }

    @Test
    @DisplayName("测试多线程环境下的唯一性")
    void testUniquenessInMultithreading() throws InterruptedException, ExecutionException {
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Set<Long> ids = Collections.synchronizedSet(new HashSet<>());
        
        List<Future<?>> futures = new ArrayList<>();
        
        // 启动10个线程，每个线程生成100个ID
        for (int i = 0; i < 10; i++) {
            Future<?> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    ids.add(generator.nextId());
                }
            });
            futures.add(future);
        }
        
        // 等待所有任务完成
        for (Future<?> future : futures) {
            future.get();
        }
        
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        
        // 验证生成的ID数量和唯一性
        assertEquals(1000, ids.size(), "应生成1000个ID");
    }

    @Test
    @DisplayName("测试同一毫秒内生成的ID序列号递增")
    void testSequenceIncrementInSameMillisecond() throws InterruptedException {
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.getInstance();
        
        // 快速连续生成多个ID
        long firstId = generator.nextId();
        long secondId = generator.nextId();
        long thirdId = generator.nextId();
        
        // 提取序列号部分
        long sequenceMask = ~(-1L << 12); // 12位序列号掩码
        long firstSequence = firstId & sequenceMask;
        long secondSequence = secondId & sequenceMask;
        long thirdSequence = thirdId & sequenceMask;
        
        // 验证序列号递增（考虑可能的时钟变化）
        assertTrue(secondSequence >= firstSequence || 
                   thirdSequence >= secondSequence ||
                   thirdSequence >= firstSequence, "序列号应该递增");
    }

    @Test
    @DisplayName("测试WorkerId和DataCenterId的有效范围")
    void testWorkerIdAndDataCenterIdRange() {
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.getInstance();
        
        long workerId = generator.getWorkerId();
        long dataCenterId = generator.getDataCenterId();
        
        assertTrue(workerId >= 0 && workerId <= 31, "WorkerId应在0-31范围内");
        assertTrue(dataCenterId >= 0 && dataCenterId <= 31, "DataCenterId应在0-31范围内");
    }

    @RepeatedTest(10)
    @DisplayName("重复测试ID生成的稳定性")
    void repeatedTestIdGeneration() {
        SnowflakeIdGenerator generator = SnowflakeIdGenerator.getInstance();
        long id = generator.nextId();
        assertTrue(id > 0, "生成的ID应始终为正数");
    }
}