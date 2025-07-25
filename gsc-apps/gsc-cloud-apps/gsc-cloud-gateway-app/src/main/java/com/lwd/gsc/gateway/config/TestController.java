package com.lwd.gsc.gateway.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lwd.gsc.common.config.TestRefreshProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author lwd
 */
@RequiredArgsConstructor
@RestController
public class TestController {
    private final TestRefreshProperties testRefreshProperties;
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @GetMapping("/config")
    public Mono<TestRefreshProperties> getConfig(){
        return Mono.just(testRefreshProperties);
    }
    //http://localhost:9092/test/service?svc=auth-service&path=public/getKey
    @GetMapping("/test/service")
    public Mono<Object> testAnyService(
            @RequestParam String svc,
            @RequestParam String path,
            @RequestParam(required = false, defaultValue = "auto") String responseType) {

        System.out.println("调用服务: " + svc + "，路径: " + path + "，期望类型: " + responseType);

        return webClientBuilder
                .baseUrl("lb://" + svc)
                .build()
                .get()
                .uri("/" + path)
                .retrieve()
                .toEntity(String.class)  // 先以字符串形式接收所有响应
                .flatMap(response -> {
                    String body = response.getBody();
                    MediaType contentType = response.getHeaders().getContentType();

                    // 1. 手动指定类型（覆盖 Content-Type）
                    if ("json".equalsIgnoreCase(responseType)) {
                        return parseAsJson(body);
                    } else if ("text".equalsIgnoreCase(responseType)) {
                        return Mono.just(body);
                    }

                    // 2. 自动判断（默认逻辑）
                    if (contentType != null && contentType.includes(MediaType.APPLICATION_JSON)) {
                        return parseAsJson(body);
                    } else {
                        return Mono.just(body);
                    }
                })
                .onErrorResume(e -> {
                    System.out.println("调用失败: " + e.getMessage());
                    return buildErrorResponse(svc, path, e);
                });
    }

    // 私有方法：安全地将字符串解析为 JsonNode
    private Mono<JsonNode> parseAsJson(String body) {
        try {
            if (body == null || body.trim().isEmpty()) {
                // 处理空响应
                return Mono.just(objectMapper.createObjectNode());
            }
            return Mono.just(objectMapper.readTree(body));
        } catch (Exception e) {
            // 解析失败时，将原始内容作为 "rawContent" 字段返回
            ObjectNode errorNode = objectMapper.createObjectNode();
            errorNode.put("success", false);
            errorNode.put("message", "响应内容不是有效的 JSON 格式");
            errorNode.put("rawContent", body);
            return Mono.just(errorNode);
        }
    }

    // 私有方法：构建统一的错误响应
    private Mono<ObjectNode> buildErrorResponse(String svc, String path, Throwable e) {
        ObjectNode errorNode = objectMapper.createObjectNode();
        errorNode.put("success", false);
        errorNode.put("message", "调用失败：" + e.getMessage());
        errorNode.put("serviceName", svc);
        errorNode.put("path", path);

        // 对于解析异常，添加额外的错误信息
        if (e instanceof com.fasterxml.jackson.core.JsonProcessingException) {
            errorNode.put("parseError", true);
            errorNode.put("errorDetail", "响应内容格式不符合 JSON 规范");
        }

        return Mono.just(errorNode);
    }

    @GetMapping("/test/auth/getKey")
    public Mono<String> testAuthGetKey() {
        return webClientBuilder
                .baseUrl("lb://auth-service")
                .build()
                .get()
                .uri("/public/getKey")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("调用失败：" + e.getMessage()));
    }

}
