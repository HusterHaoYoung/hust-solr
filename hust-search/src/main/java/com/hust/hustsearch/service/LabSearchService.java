package com.hust.hustsearch.service;

import com.hust.hustsearch.dao.LabDao;
import com.hust.hustsearch.dao.ResearchDirectionDao;
import com.hust.hustsearch.dao.TeacherDao;
import com.hust.hustsearch.entity.Lab;
import com.hust.hustsearch.entity.ResearchDirection;
import com.hust.hustsearch.entity.Teacher;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

}
