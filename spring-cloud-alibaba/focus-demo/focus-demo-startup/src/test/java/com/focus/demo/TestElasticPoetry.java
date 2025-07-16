package com.focus.demo;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.InfoResponse;
import com.focus.demo.core.domain.entity.ElasticPoetry;
import com.focus.demo.core.repository.elasticsearch.ElasticTestRepository;
import com.focus.demo.startup.FocusDemoStartupApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

import java.io.IOException;

/**
 * @author zi-wei
 * @create 2025/6/7 14:48
 */
@SpringBootTest(classes = FocusDemoStartupApplication.class)
public class TestElasticPoetry {

    @Autowired
    private ElasticTestRepository elasticTestRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Test
    void createIndex() throws IOException {
        elasticsearchClient.indices().create(c -> c.index("products"));
    }

    @Test
    void testConnection() throws Exception {
        InfoResponse info = elasticsearchClient.info();
        System.out.println("Cluster name: " + info.clusterName());
        System.out.println("ES version: " + info.version().number());
    }

    @Test
    void saveProduct() {
        ElasticPoetry elasticPoetry = new ElasticPoetry();
        ElasticPoetry save = elasticTestRepository.save(elasticPoetry);
        System.out.println(save);
    }

    @Test
    void findById() {
        ElasticPoetry elasticPoetry = elasticTestRepository.findById("1").orElse(null);
        System.out.println(elasticPoetry);
    }

}
