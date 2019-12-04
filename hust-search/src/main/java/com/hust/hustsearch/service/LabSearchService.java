package com.hust.hustsearch.service;

import com.hust.hustsearch.dao.LabDao;
import com.hust.hustsearch.dao.ResearchDirectionDao;
import com.hust.hustsearch.entity.Lab;
import com.hust.hustsearch.entity.PageResultBean;
import com.hust.hustsearch.entity.ResearchDirection;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :younghao
 * @ClassName: LabSearchService
 * @Description: TODO
 * @date : 12/4/19  5:07 PM
 */
@Service
public class LabSearchService implements ISearchService{

    @Autowired
    private LabDao labDao;
    @Autowired
    private ResearchDirectionDao researchDirectionDao;
    @Autowired
    private SolrClient solrClient;
    @Override
    public String initAllData() throws IOException, SolrServerException {
        //1、获取实验室数据库数据
        System.out.println("正在获取实验室数据");
        List<Lab> list = labDao.findAll();
        //2、数据转换为document，再保存到solr
        for (Lab lab : list) {
            System.out.println(lab.toString());
            List<ResearchDirection> researchDirections = researchDirectionDao.findByLabId(lab.getId());
            StringBuffer sb = new StringBuffer();
            for (ResearchDirection researchDirection : researchDirections) {
                sb.append(researchDirection.getName());
                sb.append("\n");
            }
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", "lab" + lab.getId());
            document.setField("lab_name", lab.getName());
            document.setField("lab_college", lab.getCollegeName());
            document.setField("lab_phone", lab.getPhone());
            document.setField("lab_email", lab.getEmail());
            document.setField("lab_address", lab.getAddress());
            document.setField("lab_introduction", lab.getIntroduction());
            document.setField("lab_introduction_link", lab.getIntroductionLink());
            document.setField("lab_website", lab.getWebsite());
            document.setField("lab_researchDirection", sb);
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
        System.out.println("添加实验室索引成功");
        return "success";
    }
    public PageResultBean<Lab> searchByLabName(String labName, Integer pageIndex, Integer rows) {
        PageResultBean<Lab> pageResultBean = new PageResultBean<>();
        //1、组装查询条件
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAllEmpty(labName)) {
            query.setQuery("lab_name:" + labName);
        } else {
            query.setQuery("lab_name:*");
        }
        query.setStart((pageIndex - 1) * rows);
        query.setRows(rows);

        List<Lab> labs = new ArrayList<>();
        long totalCount = 0L;
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList list = response.getResults();
            totalCount = list.getNumFound();
            for (int i = 0; i < list.size(); i++) {
                Lab lab = new Lab();
                lab.setId(Integer.parseInt(list.get(i).getFieldValue("id").toString().substring(3)));
                if (list.get(i).getFieldValue("lab_name") != null) {
                    lab.setName(list.get(i).getFieldValue("lab_name").toString());
                }
                if (list.get(i).getFieldValue("lab_college") != null) {
                    lab.setCollegeName(list.get(i).getFieldValue("lab_college").toString());
                }
                if (list.get(i).getFieldValue("lab_phone") != null) {
                    lab.setPhone(list.get(i).getFieldValue("lab_phone").toString());
                }
                if (list.get(i).getFieldValue("lab_email") != null) {
                    lab.setEmail(list.get(i).getFieldValue("lab_email").toString());
                }
                if (list.get(i).getFieldValue("lab_address") != null) {
                    lab.setAddress(list.get(i).getFieldValue("lab_address").toString());
                }
                if (list.get(i).getFieldValue("lab_introduction") != null) {
                    lab.setIntroduction(list.get(i).getFieldValue("lab_introduction").toString());
                }
                if (list.get(i).getFieldValue("lab_introduction_link") != null) {
                    lab.setIntroductionLink(list.get(i).getFieldValue("lab_introduction_link").toString());
                }
                if (list.get(i).getFieldValue("lab_website") != null) {
                    lab.setWebsite(list.get(i).getFieldValue("lab_website").toString());
                }
                labs.add(lab);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、查询结果List<Document> 转换为List<College>
        pageResultBean.setPageNum(pageIndex);
        pageResultBean.setPageSize(rows);
        pageResultBean.setList(labs);
        pageResultBean.setTotal(totalCount);
        System.out.println("totalCount = " + totalCount);
        pageResultBean.setPages((int) (totalCount % rows == 0 ? (totalCount / rows) : (totalCount / rows + 1)));
        pageResultBean.setNavigatePages(4);
        return pageResultBean;
    }
}
