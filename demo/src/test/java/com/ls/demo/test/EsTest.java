package com.ls.demo.test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ls.demo.DemoApplication;
import com.ls.demo.dao.EmpMapper;
import com.ls.demo.dao.EmpModel;
import com.ls.demo.util.ESIndexUtil;
import com.ls.demo.util.EsDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 罗帅
 * @Date: 2021/1/5
 */
@Slf4j
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EsTest {
    @Autowired
    EmpMapper empMapper;

    String hostAddress = "127.0.0.1";
    String esPort = "9200";
    String indexName = "es_test";
    Integer pageSize = 10;

    /**
     * 1.创建索引
     */
    @Test
    public void createIndexTest() throws IOException {
        ESIndexUtil esIndexUtil = new ESIndexUtil(hostAddress, esPort);
        esIndexUtil.createIndex(indexName);
    }

    /**
     * 2.索引中添加数据
     */
    @Test
    public void addDataToIndexTest() throws IOException {
        //获取员工表数据
        List<EmpModel> empModelList = empMapper.selectList(new QueryWrapper<>());
        EsDataUtil esDataUtil = new EsDataUtil(hostAddress, esPort);
        BulkRequest request = new BulkRequest();

        for (EmpModel empModel : empModelList) {
            IndexRequest indexRequest = new IndexRequest(indexName, "_doc");
            indexRequest.source(JSON.toJSONString(empModel), XContentType.JSON);
            request.add(indexRequest);
        }

        BulkResponse response = esDataUtil.bulkUpdateOrInsertDoc(indexName, "_doc", request);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 3.查询
     */
    @Test
    public void queryDataTest(){
        SearchRequest request = new SearchRequest(indexName);
        //构造查询条件
        BoolQueryBuilder boolQuery = getBoolQueryBuilder();
        //首次查询 从第0条开始算
        Integer beginIndex = 0;
        List<EmpModel> res = new ArrayList<>();
        try {

            //获取总条数
            SearchResponse countResponse = getClient().search(
                    request.source(new SearchSourceBuilder()
                            .query(boolQuery)
                            .trackTotalHits(true)
                    ), RequestOptions.DEFAULT);
            //总条数
            Long count = countResponse.getHits().getTotalHits().value;
            log.info("查询到的总条数:{}", count);

            //分页查询
            SearchResponse response = getClient().search(
                    request.source(new SearchSourceBuilder()
                            .query(boolQuery)
                            .from(beginIndex)
                            .size(pageSize)
                            .trackTotalHits(true)
                    ), RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();

            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), EmpModel.class));
            }

        } catch (Exception e) {
            log.error("es分页查询出现异常:", e);
        }
        log.info("查询结果：{}",JSON.toJSONString(res));
    }

    /**
     * 生成查询条件
     * @return
     */
   public BoolQueryBuilder getBoolQueryBuilder(){
       BoolQueryBuilder boolQuery = new BoolQueryBuilder();
       //-----1.精确查询员工编号为Emp101577的数据
//       boolQuery.must(QueryBuilders.matchQuery("empId", "Emp101577"));

       //-----2.精确查询部门编号为Post100010的员工
//       boolQuery.must(QueryBuilders.matchQuery("mainPostId", "Post100010"));

       //-----3.模糊查询姓杨的员工
       // ?，它与任何单个字符匹配
       // *，可以匹配零个或多个字符，包括一个空字符
//       boolQuery.filter(QueryBuilders.wildcardQuery("empName", "*杨*"));

       //-----4.范围查询 查询phyId为1到10的数据
       boolQuery.filter(QueryBuilders.rangeQuery("phyId").from(1).to(100));
       return  boolQuery;
   }

    //获取ES连接
    public RestHighLevelClient getClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostAddress, Integer.valueOf(esPort), "http"))
        );
    }

}
