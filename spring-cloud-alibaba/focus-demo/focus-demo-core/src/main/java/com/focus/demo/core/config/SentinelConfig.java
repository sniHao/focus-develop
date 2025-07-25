package com.focus.demo.core.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Sentinel配置类
 * 用于启用Sentinel注解支持和配置相关功能
 *
 * @author focus
 */
@Configuration
public class SentinelConfig {

    /**
     * 注册SentinelResourceAspect，用于支持@SentinelResource注解
     *
     * @return SentinelResourceAspect实例
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    /**
     * 创建带有Sentinel功能的RestTemplate
     * 使用@SentinelRestTemplate注解为RestTemplate添加Sentinel功能
     *
     * @return RestTemplate实例
     */
    @Bean
    @SentinelRestTemplate
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}