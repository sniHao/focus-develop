package com.focus.common.config;

import com.focus.common.interceptor.SignInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置
 * 注册通用拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private SignInterceptor signInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 签名拦截器
        registry.addInterceptor(signInterceptor)
                .addPathPatterns("/**")
                .order(1);
    }
}
