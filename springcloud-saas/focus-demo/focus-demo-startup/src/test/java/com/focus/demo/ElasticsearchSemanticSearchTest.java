package com.focus.demo;

import cn.hutool.json.JSONUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.focus.demo.core.domain.entity.CommodityDocument;
import com.focus.demo.core.repository.elasticsearch.CommodityDocumentRepository;
import com.focus.demo.startup.FocusDemoStartupApplication;
import com.focus.framework.elasticsearch.service.ElasticsearchModelService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * es向量 搜索测试
 *
 * @author zi-wei
 * @create 2025/6/21 11:54
 */
@SpringBootTest(classes = FocusDemoStartupApplication.class)
public class ElasticsearchSemanticSearchTest {

    @Autowired
    private ElasticsearchModelService elasticsearchModelService;
    @Autowired
    private CommodityDocumentRepository repository;
    @Autowired
    private ElasticsearchClient elasticsearchClient;


    /**
     * 分词/语义
     */
    @SneakyThrows
    @Test
    public void searchByName() {
        // 构建查询
        Query query = MatchQuery.of(m -> m.field("name").query("国产手机"))._toQuery();
        // 执行搜索
        SearchResponse<CommodityDocument> response = elasticsearchClient.search(
                s -> s.index("commodity_index").query(query).size(2), CommodityDocument.class);

        List<CommodityDocument> collect = response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());

        System.out.println(JSONUtil.toJsonPrettyStr(collect));
    }

    /**
     * 组合查询
     */
    @SneakyThrows
    @Test
    public void searchByCombination() {
        // 构建bool查询
        Query query = BoolQuery.of(b -> b
                .must(MatchQuery.of(m -> m
                                .field("name")
                                .query("国产手机"))._toQuery(),
                        TermQuery.of(t -> t
                                .field("classification")
                                .value("电子产品"))._toQuery()
                )
        )._toQuery();

        // 执行搜索
        SearchResponse<CommodityDocument> response = elasticsearchClient.search(s -> s
                .index("commodity_index")
                .query(query)
                .size(1), CommodityDocument.class);

        // 转换结果
        List<CommodityDocument> collect = response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonPrettyStr(collect));
    }

    @Test
    public void addData() {
        // 批量插入数据
        List<CommodityDocument> commodities = Arrays.asList(
                new CommodityDocument("1", "香蕉", "水果"),
                new CommodityDocument("2", "苹果", "水果"),
                new CommodityDocument("3", "柠檬", "水果"),
                new CommodityDocument("4", "vivo x200pro", "电子产品"),
                new CommodityDocument("5", "iphone 15pro", "电子产品"),
                new CommodityDocument("6", "联想ThinkBook 14", "电子产品"),
                new CommodityDocument("7", "五常大米", "农产品"),
                new CommodityDocument("8", "玉米", "农产品"),
                new CommodityDocument("9", "电饭煲", "家电"),
                new CommodityDocument("10", "海尔冰箱", "家电")
        );
        repository.saveAll(commodities);
    }

    /**
     * 绑定大模型
     *
     * @throws IOException
     */
    @Test
    void elasticsearchModelServiceTest() {
        //申请许可证
        String startTrial = elasticsearchModelService.startTrial();
        System.out.println(startTrial);

        //绑定模型
        String bindAliEmbeddingModel = elasticsearchModelService.bindAliEmbeddingModel();
        System.out.println(bindAliEmbeddingModel);

//        //查看模型
        String verifyEmbeddingModel = elasticsearchModelService.verifyEmbeddingModel();
        System.out.println(verifyEmbeddingModel);
//
//        //删除模型
//        String deleteEmbeddingModel = elasticsearchModelService.deleteEmbeddingModel();
//        System.out.println(deleteEmbeddingModel);

    }
}
