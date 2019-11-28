package com.hust.hustsearch;

import com.hust.hustsearch.dao.CollegeDao;
import com.hust.hustsearch.entity.College;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class HustSearchApplicationTests {
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private CollegeDao collegeDao;
    @Test
    public void addOrUpdateTest() throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id","33");
        document.setField("t_name","李冬");
        document.setField("t_age","三十四");
        document.setField("note","年度最佳讲师");

        solrClient.add(document);
        solrClient.commit();
    }
    @Test
    public void queryTest() throws IOException, SolrServerException {
        SolrQuery queryCondition = new SolrQuery();
        queryCondition.setQuery("*:*");

        QueryResponse response = solrClient.query(queryCondition);

        SolrDocumentList res = response.getResults();

        for (SolrDocument document: res){
            System.out.println(document.get("id"));
            System.out.println(document.get("t_name"));
            System.out.println(document.get("note"));
        }
    }
    @Test
    void contextLoads() {
    }


    @Test
    void dataTest(){
        List<College> colleges = collegeDao.findAll();
        for (College c:colleges){
            System.out.println(c.toString());
        }
    }
}
