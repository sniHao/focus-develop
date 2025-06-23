package com.focus.framework.elasticsearch.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 绑定向量模型
 *
 * @author zi-wei
 */
@RequiredArgsConstructor
@Service
public class ElasticsearchModelService {

    private final RestClient restClient;
    private final AlibabaVectorConfig alibabaVectorConfig;

    /**
     * 申请临时许可证
     *
     * @return
     * @throws IOException
     */
    @SneakyThrows
    public String startTrial() {
        Request request = new Request("POST", "/_license/start_trial?acknowledge=true");
        Response response = restClient.performRequest(request);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 绑定阿里向量大模型
     *
     * @return
     * @throws IOException
     */
    @SneakyThrows
    public String bindAliEmbeddingModel() {
        Request request = new Request("PUT", "/_inference/text_embedding/" + alibabaVectorConfig.getModelName());
        String jsonBody = String.format("""
                        {
                          "service": "%s",
                          "service_settings": {
                            "api_key": "%s",
                            "service_id": "%s",
                            "host": "%s",
                            "workspace": "%s"
                          }
                        }""",
                alibabaVectorConfig.getServiceName(),
                alibabaVectorConfig.getApiKey(),
                alibabaVectorConfig.getServiceId(),
                alibabaVectorConfig.getHost(),
                alibabaVectorConfig.getWorkspace());

        request.setJsonEntity(jsonBody);
        Response response = restClient.performRequest(request);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 验证模型
     *
     * @return
     * @throws IOException
     */
    @SneakyThrows
    public String verifyEmbeddingModel() {
        Request request = new Request("GET", "/_inference/text_embedding/" + alibabaVectorConfig.getModelName());
        Response response = restClient.performRequest(request);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 删除模型
     *
     * @return
     * @throws IOException
     */
    @SneakyThrows
    public String deleteEmbeddingModel() {
        Request request = new Request("DELETE", "/_inference/text_embedding/" + alibabaVectorConfig.getModelName());
        Response response = restClient.performRequest(request);
        return EntityUtils.toString(response.getEntity());
    }

}

@Data
@Component
class AlibabaVectorConfig {
    /**
     * 服务名
     */
    private String serviceName = "alibabacloud-ai-search";

    /**
     * 模型名称
     */
    private String modelName = "text-embedding-v1";

    /**
     * key
     */
    private String apiKey = "OS-9h592my316195d1f";

    /**
     * serviceId
     */
    private String serviceId = "ops-text-embedding-001";

    /**
     * 外网地址
     */
    private String host = "default-4o7x.platform-cn-shanghai.opensearch.aliyuncs.com";
    /**
     * 工作空间
     */
    private String workspace = "default";

}
