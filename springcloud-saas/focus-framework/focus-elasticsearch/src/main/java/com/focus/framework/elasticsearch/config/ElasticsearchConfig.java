package com.focus.framework.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Elasticsearch Config
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchConfig {
    /**
     * Elasticsearch 集群地址，多个地址用逗号分隔
     */
    private String uris;

    /**
     * Elasticsearch 用户名
     */
    private String username;

    /**
     * Elasticsearch 密码
     */
    private String password;

}

@Data
@Component
@ConfigurationProperties(prefix = "spring.elasticsearch.ssl")
class ElasticsearchSslConfig {
    /**
     * 验证模式
     */
    private String verificationMode;

    /**
     * Elasticsearch SSL 证书路径
     */
    private String certificateAuthorities;

}
