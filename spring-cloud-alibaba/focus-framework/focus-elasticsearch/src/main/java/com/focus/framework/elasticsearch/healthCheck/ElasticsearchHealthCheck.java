package com.focus.framework.elasticsearch.healthCheck;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.InfoResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 测试连接es
 */
@Component
@ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enabled", havingValue = "true", matchIfMissing = false)
@Slf4j
public class ElasticsearchHealthCheck {

    private final ElasticsearchClient client;

    public ElasticsearchHealthCheck(ElasticsearchClient client) {
        this.client = client;
    }

    @PostConstruct
    public void checkConnection() {
        try {
            InfoResponse info = client.info();
            log.info("Connected to Elasticsearch cluster: {}, version: {}",
                    info.clusterName(), info.version().number());
        } catch (Exception e) {
            log.error("Failed to connect to Elasticsearch", e);
            throw new RuntimeException("Failed to connect to Elasticsearch", e);
        }
    }
}
