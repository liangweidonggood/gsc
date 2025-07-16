package com.lwd.gsc.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> T getAs(String key, TypeReference<T> typeReference) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        byte[] bytes = (byte[]) ops.get(key);
        if (bytes == null) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Redis 反序列化失败", e);
        }
    }
}
