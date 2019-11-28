package com.hust.hustsearch.service;

import com.hust.hustsearch.dao.CollegeDao;
import com.hust.hustsearch.entity.College;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :younghao
 * @ClassName: SearchService
 * @Description: TODO
 * @date : 11/27/19  3:37 PM
 */
@Service
public class SearchService implements ISearchService {
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private SolrClient solrClient;

    @Override
    public String initAllData() {
        //1、获取数据库数据
        System.out.println("获取学院数据成果");
        List<College> list = collegeDao.findAll();
        //2、讲数据转换为document，再保存到solr
        for (College college : list) {
            System.out.println(college.toString());
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", college.getId());
            document.setField("college_address", college.getAddress());
            document.setField("college_email", college.getEmail());
            document.setField("college_img", college.getImg());
            document.setField("college_index", college.getIndex());
            document.setField("college_introduction", college.getIntroduction());
            document.setField("college_name", college.getName());
            document.setField("college_introduction_link", college.getIntroductionLink());
            document.setField("college_english_name",college.getEnglishName());
            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
            }
        }
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("添加索引成功");
        return "success";
    }

    public College searchByKeyword(String keyword) {
        //1、组装查询条件
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAllEmpty(keyword)) {
            query.setQuery("college_name:" + keyword);
        } else {
            query.setQuery("college_name:*");
        }
        //2、执行查询
        College college = new College();
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < 1; i++) {
                college.setId(Integer.parseInt(list.get(i).getFieldValue("id").toString()));
                if (list.get(i).getFieldValue("college_name") != null) {
                    college.setName(list.get(i).getFieldValue("college_name").toString());
                }
                if (list.get(i).getFieldValue("college_img") != null) {
                    college.setImg(list.get(i).getFieldValue("college_img").toString());
                }
                if (list.get(i).getFieldValue("college_address") != null) {
                    college.setAddress(list.get(i).getFieldValue("college_address").toString());
                }
                if (list.get(i).getFieldValue("college_email") != null) {
                    college.setEmail(list.get(i).getFieldValue("college_email").toString());
                }
                if (list.get(i).getFieldValue("college_introduction") != null) {
                    college.setIntroduction(list.get(i).getFieldValue("college_introduction").toString());
                }
                if (list.get(i).getFieldValue("college_introduction_link") != null) {
                    college.setIntroductionLink(list.get(i).getFieldValue("college_introduction_link").toString());
                }
                if (list.get(i).getFieldValue("college_index") != null) {
                    college.setIndex(list.get(i).getFieldValue("college_index").toString());
                }
                if (list.get(i).getFieldValue("college_phone") != null) {
                    college.setPhone(list.get(i).getFieldValue("college_phone").toString());
                }
                if(list.get(i).getFieldValue("college_english_name")!=null){
                    college.setEnglishName(list.get(i).getFieldValue("college_english_name").toString());
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、查询结果List<Document> 转换为List<College>
        return college;
    }
}
