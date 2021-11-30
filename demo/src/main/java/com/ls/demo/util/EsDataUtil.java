package com.ls.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * excel文档操作
 *
 * @author 罗帅
 * @date 2020/11/19
 */
@Slf4j
public class EsDataUtil {

    private RestHighLevelClient client = null;

    /**
     * 构造方法
     */
    public EsDataUtil(String hostAddress, String esPort) {
        //获取es连接
        this.client = new RestHighLevelClient(RestClient.builder(
                new HttpHost(hostAddress, Integer.parseInt(esPort), "http")));
    }



    public void add(String index, String type, String json) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type);
        indexRequest.source(json, XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    }


    public void add(String index, String type, String json, String id) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        indexRequest.source(json, XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除
     *
     * @param indexName 索引名称
     * @param typeName  TYPE名称
     * @param docId     文档ID
     * @throws IOException
     */

    public void deleteDocWithId(String indexName, String typeName, String docId) throws IOException {
        DeleteRequest request = new DeleteRequest(
                indexName,
                typeName,
                docId);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response + "" + response.getResult());
    }


    public void refresh(String indexName) throws IOException {
        RefreshRequest refreshRequest = new RefreshRequest(indexName);
        RefreshResponse refresh = client.indices().refresh(refreshRequest, RequestOptions.DEFAULT);
    }


    public UpdateResponse update(String index, String type, Map<String, Object> map, String id) throws IOException {
        UpdateRequest request = new UpdateRequest(index, type, id).doc(map);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        return updateResponse;
    }

    /**
     * 批量增加与修改
     *
     * @param indexName   索引名称
     * @param typeName    TYPE名称
     * @param BulkRequest 批量请求
     * @throws IOException
     */
    public BulkResponse bulkUpdateOrInsertDoc(String indexName, String typeName, BulkRequest builder) throws IOException {
        BulkResponse bulkResponse = client.bulk(builder, RequestOptions.DEFAULT);
        return bulkResponse;
    }

    /**
     * 批量删除
     *
     * @param indexName 索引名称
     * @param typeName  TYPE名称
     * @param docIdArr  _ID数组
     * @throws IOException
     */

    public void bulkDeleteDoc(String indexName, String typeName, String[] docIdArr) throws IOException {
        BulkRequest bulkRequestBuilder = new BulkRequest();
        for (String docId : docIdArr) {
            bulkRequestBuilder.add(new DeleteRequest(indexName, typeName, docId));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequestBuilder, RequestOptions.DEFAULT);
    }

    /**
     * 删除查询的数据
     *
     * @param indexName  索引名称
     * @param typeName   TYPE名称
     * @param fieldName  查询字段名称
     * @param fieldValue 查询字段值
     * @throws IOException
     */
    public void deleteByQuery(String indexName, String typeName, String fieldName, String fieldValue) throws IOException {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(indexName).types(typeName).setQuery(QueryBuilders.termQuery(fieldName, fieldValue));
        BulkByScrollResponse bulkByScrollResponse = client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
    }
}
