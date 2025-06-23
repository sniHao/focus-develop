package com.focus.demo.core.repository.elasticsearch;

import com.focus.demo.core.domain.entity.ElasticPoetry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zi-wei
 * @create 2025/6/7 14:45
 */
@Repository
public interface ElasticTestRepository extends ElasticsearchRepository<ElasticPoetry, String> {

}
