package com.focus.auth.config;

import com.focus.auth.resolver.FocusLoginUserArgumentResolver;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 认证相关配置
 * 注册 @FocusLoginUser 参数解析器
 */
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Resource
    private FocusLoginUserArgumentResolver focusLoginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(focusLoginUserArgumentResolver);
    }
}
