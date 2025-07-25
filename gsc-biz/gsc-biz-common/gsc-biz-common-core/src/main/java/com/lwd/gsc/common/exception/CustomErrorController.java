package com.lwd.gsc.common.exception;

import com.lwd.gsc.common.result.ResResult;
import com.lwd.gsc.common.result.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 自定义全局错误处理器
 * @author lwd
 */
@RestController
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ResResult<ResultCode> handleError(HttpServletRequest request) {
        try {
            // 1. 创建WebRequest
            ServletWebRequest webRequest = new ServletWebRequest(request);

            // 2. 配置错误属性选项
            ErrorAttributeOptions options = ErrorAttributeOptions.defaults()
                    .including(ErrorAttributeOptions.Include.MESSAGE)
                    .including(ErrorAttributeOptions.Include.EXCEPTION);

            // 3. 获取错误属性
            Map<String, Object> error = errorAttributes.getErrorAttributes(webRequest, options);

            int status = (int) error.getOrDefault("status", 500);
            String message = (String) error.getOrDefault("error", "Unknown Error");

            if (status == HttpStatus.NOT_FOUND.value()) {
                return ResResult.fail(ResultCode.NOT_FOUND);
            }

            return ResResult.fail(message);

        } catch (Exception e) {
            return ResResult.fail("服务器内部错误: " + e.getMessage());
        }
    }
}
