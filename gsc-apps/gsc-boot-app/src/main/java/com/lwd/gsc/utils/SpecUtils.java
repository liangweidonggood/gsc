package com.lwd.gsc.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * @author lwd
 */
public class SpecUtils {

    /**
     * 构建一个等于指定值的查询条件（类型安全）
     */
    public static <T, V> Specification<T> equal(Function<T, V> fieldExtractor, V value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            try {
                // 使用反射获取字段名
                Field field = findField(root.getModel().getJavaType(), fieldExtractor);
                String fieldName = field.getName();
                return criteriaBuilder.equal(root.get(fieldName), value);
            } catch (Exception e) {
                throw new RuntimeException("无法提取字段信息", e);
            }
        };
    }

    /**
     * 通过 Lambda 表达式提取字段名
     */
    private static <T> Field findField(Class<T> clazz, Function<T, ?> fieldFunction) throws Exception {
        // 获取 Lambda 表达式的类名
        String lambdaClassName = fieldFunction.getClass().getName();

        // 如果是 JDK 的代理类，抛出异常（说明不是有效 Lambda）
        if (lambdaClassName.contains("$$Lambda$")) {
            throw new IllegalArgumentException("仅支持实体类字段的 getter 方法");
        }

        // 获取 Lambda 中的方法名（即 getter 名称）
        String methodName = fieldFunction.getClass().getDeclaredMethods()[0].getName();

        // 解析字段名（从 getter 方法名转换）
        if (methodName.startsWith("get") && methodName.length() > 3) {
            String fieldName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
            return clazz.getDeclaredField(fieldName);
        } else if (methodName.equals("is")) {
            // 处理 boolean 字段的 isXxx()
            return clazz.getDeclaredField("is" + methodName);
        } else {
            throw new IllegalArgumentException("仅支持 getter 方法");
        }
    }
}
