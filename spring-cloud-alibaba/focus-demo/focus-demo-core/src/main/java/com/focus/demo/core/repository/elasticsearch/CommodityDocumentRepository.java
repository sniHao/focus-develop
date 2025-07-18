package com.focus.demo.core.repository.elasticsearch;

import com.focus.demo.core.domain.entity.CommodityDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zi-wei
 */
public interface CommodityDocumentRepository extends ElasticsearchRepository<CommodityDocument, String> {
}
