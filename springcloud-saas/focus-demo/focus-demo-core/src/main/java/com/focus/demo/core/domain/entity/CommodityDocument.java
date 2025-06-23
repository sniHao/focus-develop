package com.focus.demo.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;

/**
 * @author zi-wei
 * @create 2025/6/21 11:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "commodity_index")
@Mapping(mappingPath = "elasticsearch/CommodityMappings.json")//json映射
@JsonIgnoreProperties(ignoreUnknown = true)//忽略为定义字段
public class CommodityDocument implements Serializable {

    @Id
    private String id;

    /**
     * 标题
     */
    @Field(name = "name")
    private String name;

    /**
     * 作者
     */
    @Field(name = "classification")
    private String classification;
}
