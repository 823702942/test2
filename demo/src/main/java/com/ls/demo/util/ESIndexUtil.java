package com.ls.demo.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * ES索引相关操作 封装util工具类
 */
@Slf4j
public class ESIndexUtil {

    private RestHighLevelClient client = null;

    /**
     * 构造函数
     *
     * @param hostAddress ES服务器ip地址
     * @param esPort      ES服务器端口
     */
    public ESIndexUtil(String hostAddress, String esPort) {
        //获取es连接
        this.client = new RestHighLevelClient(RestClient.builder(
                new HttpHost(hostAddress, Integer.parseInt(esPort), "http")));
    }



    /**
     * 创建索引
     *
     * @throws IOException
     */
    public void createIndex(String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
      CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     * @throws IOException
     */
    public void deleteIndex(String indexName) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest();
        getIndexRequest.indices(indexName);
        if (client.indices().exists(getIndexRequest, RequestOptions.DEFAULT)) {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        }
    }


    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return
     * @throws IOException
     */
    public boolean existsIndex(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }


}
