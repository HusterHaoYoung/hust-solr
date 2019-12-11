package com.hust.hustsearch.service;

import com.hust.hustsearch.dao.ResearchDirectionDao;
import com.hust.hustsearch.dao.TeacherDao;
import com.hust.hustsearch.entity.PageResultBean;
import com.hust.hustsearch.entity.ResearchDirection;
import com.hust.hustsearch.entity.Teacher;
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
 * @ClassName: TeacherSearchService
 * @Description: TODO
 * @date : 12/2/19  9:43 PM
 */
@Service
public class TeacherSearchService implements ISearchService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ResearchDirectionDao researchDirectionDao;
    @Autowired
    private SolrClient solrClient;

    @Override
    public String initAllData() throws IOException, SolrServerException {
        //1、获取数据库数据
        System.out.println("正在获取教师数据");
        List<Teacher> list = teacherDao.findAll();
        //2、数据转换为document，再保存到solr
        for (Teacher teacher : list) {
            System.out.println(teacher.toString());
            List<ResearchDirection> researchDirections = researchDirectionDao.findByTeacherId(teacher.getId());
            String isDTutor = "";
            String isTutor = "";
            StringBuffer sb = new StringBuffer();
            for (ResearchDirection researchDirection : researchDirections) {
                sb.append(researchDirection.getName());
                sb.append("\n");
            }
            System.out.println(teacher.getName() + "   " + teacher.getIsDtutor());
            if (teacher.getIsDtutor() == 1) {
                isDTutor += "博士生导师";
            }
            if (teacher.getIsTutor() == 1) {
                isTutor += "硕士生导师";
            }
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", "teacher" + teacher.getId());
            document.setField("teacher_name", teacher.getName());
            document.setField("teacher_subject", teacher.getSubjectName());
            document.setField("teacher_isDTutor", isDTutor);
            document.setField("teacher_isTutor", isTutor);
            document.setField("teacher_college", teacher.getCollegeName());
            document.setField("teacher_introduction", teacher.getIntroduction());
            document.setField("teacher_honor", teacher.getHonor());
            document.setField("teacher_email", teacher.getEmail());
            document.setField("teacher_phone", teacher.getPhone());
            document.setField("teacher_lab", teacher.getLabName());
            document.setField("teacher_lab_id", teacher.getLabId());
            document.setField("teacher_lab_introduction", teacher.getLabIntroduction());
            document.setField("teacher_prorank", teacher.getProrank());
            document.setField("teacher_direction", sb.toString());
            document.setField("teacher_img", teacher.getImg());
            document.setField("teacher_lab_website", teacher.getLabWebsite());
            document.setField("teacher_homepage", teacher.getHomepage());

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
        System.out.println("添加教师索引成功");
        return "success";
    }

    public List<Teacher> searchByName(String teacherName) {
        //1、组装查询条件
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAllEmpty(teacherName)) {
            query.setQuery("teacher_name:" + teacherName);
        } else {
            query.setQuery("teacher_name:*");
        }
        //2、执行查询
        List<Teacher> teachers = new ArrayList<>();
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                Teacher teacher = new Teacher();
                teacher.setId(Integer.parseInt(list.get(i).getFieldValue("id").toString().substring(7)));
                if (list.get(i).getFieldValue("teacher_name") != null) {
                    teacher.setName(list.get(i).getFieldValue("teacher_name").toString());
                }
                if (list.get(i).getFieldValue("teacher_subject") != null) {
                    teacher.setSubjectName(list.get(i).getFieldValue("teacher_subject").toString());
                }
                if (list.get(i).getFieldValue("teacher_isDTutor") != null) {
                    teacher.setDtutor(list.get(i).getFieldValue("teacher_isDTutor").toString());
                }
                if (list.get(i).getFieldValue("teacher_isTutor") != null) {
                    teacher.setTutor(list.get(i).getFieldValue("teacher_isTutor").toString());
                }
                if (list.get(i).getFieldValue("teacher_college") != null) {
                    teacher.setCollegeName(list.get(i).getFieldValue("teacher_college").toString());
                }
                if (list.get(i).getFieldValue("teacher_address") != null) {
                    teacher.setAddress(list.get(i).getFieldValue("teacher_address").toString());
                }
                if (list.get(i).getFieldValue("teacher_introduction") != null) {
                    teacher.setIntroduction(list.get(i).getFieldValue("teacher_introduction").toString());
                }
                if (list.get(i).getFieldValue("teacher_honor") != null) {
                    teacher.setHonor(list.get(i).getFieldValue("teacher_honor").toString());
                }
                if (list.get(i).getFieldValue("teacher_email") != null) {
                    teacher.setEmail(list.get(i).getFieldValue("teacher_email").toString());
                }
                if (list.get(i).getFieldValue("teacher_phone") != null) {
                    teacher.setPhone(list.get(i).getFieldValue("teacher_phone").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab") != null) {
                    teacher.setLabName(list.get(i).getFieldValue("teacher_lab").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab_introduction") != null) {
                    teacher.setLabIntroduction(list.get(i).getFieldValue("teacher_lab_introduction").toString());
                }
                if (list.get(i).getFieldValue("teacher_prorank") != null) {
                    teacher.setProrank(list.get(i).getFieldValue("teacher_prorank").toString());
                }
                if (list.get(i).getFieldValue("teacher_img") != null) {
                    teacher.setImg(list.get(i).getFieldValue("teacher_img").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab_website") != null) {
                    teacher.setLabWebsite(list.get(i).getFieldValue("teacher_lab_website").toString());
                }
                if (list.get(i).getFieldValue("teacher_homepage") != null) {
                    teacher.setHomepage(list.get(i).getFieldValue("teacher_homepage").toString());
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、查询结果List<Document> 转换为List<College>
        return teachers;
    }

    public PageResultBean<Teacher> searchByTeacherName(String teacherName, Integer pageIndex, Integer rows) {
        PageResultBean<Teacher> pageResultBean = new PageResultBean<>();
        //1、组装查询条件
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAllEmpty(teacherName)) {
            query.setQuery("teacher_name:" + teacherName);
        } else {
            query.setQuery("teacher_name:*");
        }
        query.setStart((pageIndex - 1) * rows);
        query.setRows(rows);

        List<Teacher> teachers = new ArrayList<>();
        long totalCount = 0L;
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList list = response.getResults();
            totalCount = list.getNumFound();
            for (int i = 0; i < list.size(); i++) {
                Teacher teacher = new Teacher();
                teacher.setId(Integer.parseInt(list.get(i).getFieldValue("id").toString().substring(7)));
                if (list.get(i).getFieldValue("teacher_name") != null) {
                    teacher.setName(list.get(i).getFieldValue("teacher_name").toString());
                }
                if (list.get(i).getFieldValue("teacher_subject") != null) {
                    teacher.setSubjectName(list.get(i).getFieldValue("teacher_subject").toString());
                }
                if (list.get(i).getFieldValue("teacher_isDTutor") != null) {
                    teacher.setDtutor(list.get(i).getFieldValue("teacher_isDTutor").toString());
                }
                if (list.get(i).getFieldValue("teacher_isTutor") != null) {
                    teacher.setTutor(list.get(i).getFieldValue("teacher_isTutor").toString());
                }
                if (list.get(i).getFieldValue("teacher_college") != null) {
                    teacher.setCollegeName(list.get(i).getFieldValue("teacher_college").toString());
                }
                if (list.get(i).getFieldValue("teacher_address") != null) {
                    teacher.setAddress(list.get(i).getFieldValue("teacher_address").toString());
                }
//                if (list.get(i).getFieldValue("teacher_direction") != null) {
//                    teacher.setSubjectName(list.get(i).getFieldValue("teacher_direction").toString());
//                }
                if (list.get(i).getFieldValue("teacher_introduction") != null) {
                    teacher.setIntroduction(list.get(i).getFieldValue("teacher_introduction").toString());
                }
                if (list.get(i).getFieldValue("teacher_honor") != null) {
                    teacher.setHonor(list.get(i).getFieldValue("teacher_honor").toString());
                }
                if (list.get(i).getFieldValue("teacher_email") != null) {
                    teacher.setEmail(list.get(i).getFieldValue("teacher_email").toString());
                }
                if (list.get(i).getFieldValue("teacher_phone") != null) {
                    teacher.setPhone(list.get(i).getFieldValue("teacher_phone").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab") != null) {
                    System.out.println("****************"+list.get(i).getFieldValue("teacher_lab"));
                    teacher.setLabName(list.get(i).getFieldValue("teacher_lab").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab_id") != null) {
                    System.out.println("****************"+list.get(i).getFieldValue("teacher_lab_id"));
                    teacher.setLabId(Integer.parseInt(list.get(i).getFieldValue("teacher_lab_id").toString()));
                }
                if (list.get(i).getFieldValue("teacher_lab_introduction") != null) {
                    teacher.setLabIntroduction(list.get(i).getFieldValue("teacher_lab_introduction").toString());
                }
                if (list.get(i).getFieldValue("teacher_prorank") != null) {
                    teacher.setProrank(list.get(i).getFieldValue("teacher_prorank").toString());
                }
                if (list.get(i).getFieldValue("teacher_img") != null) {
                    teacher.setImg(list.get(i).getFieldValue("teacher_img").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab_website") != null) {
                    teacher.setLabWebsite(list.get(i).getFieldValue("teacher_lab_website").toString());
                }
                if (list.get(i).getFieldValue("teacher_homepage") != null) {
                    teacher.setHomepage(list.get(i).getFieldValue("teacher_homepage").toString());
                }
                teachers.add(teacher);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、查询结果List<Document> 转换为List<College>
        pageResultBean.setPageNum(pageIndex);
        pageResultBean.setPageSize(rows);
        pageResultBean.setList(teachers);
        pageResultBean.setTotal(totalCount);
        System.out.println("totalCount = " + totalCount);
        pageResultBean.setPages((int) (totalCount % rows == 0 ? (totalCount / rows) : (totalCount / rows + 1)));
        pageResultBean.setNavigatePages(4);
        return pageResultBean;
    }
    public PageResultBean<Teacher> searchByTeacherId(String teacherId, Integer pageIndex, Integer rows) {
        PageResultBean<Teacher> pageResultBean = new PageResultBean<>();
        //1、组装查询条件
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAllEmpty(teacherId)) {
            System.out.println("id:" + teacherId);
            query.setQuery("id:" + teacherId);
        } else {
            query.setQuery("teacher_name:*");
        }
        query.setStart((pageIndex - 1) * rows);
        query.setRows(rows);

        List<Teacher> teachers = new ArrayList<>();
        long totalCount = 0L;
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList list = response.getResults();
            totalCount = list.getNumFound();
            for (int i = 0; i < list.size(); i++) {
                Teacher teacher = new Teacher();
                teacher.setId(Integer.parseInt(list.get(i).getFieldValue("id").toString().substring(7)));
                if (list.get(i).getFieldValue("teacher_name") != null) {
                    teacher.setName(list.get(i).getFieldValue("teacher_name").toString());
                }
                if (list.get(i).getFieldValue("teacher_subject") != null) {
                    teacher.setSubjectName(list.get(i).getFieldValue("teacher_subject").toString());
                }
                if (list.get(i).getFieldValue("teacher_isDTutor") != null) {
                    teacher.setDtutor(list.get(i).getFieldValue("teacher_isDTutor").toString());
                }
                if (list.get(i).getFieldValue("teacher_isTutor") != null) {
                    teacher.setTutor(list.get(i).getFieldValue("teacher_isTutor").toString());
                }
                if (list.get(i).getFieldValue("teacher_college") != null) {
                    teacher.setCollegeName(list.get(i).getFieldValue("teacher_college").toString());
                }
                if (list.get(i).getFieldValue("teacher_address") != null) {
                    teacher.setAddress(list.get(i).getFieldValue("teacher_address").toString());
                }
//                if (list.get(i).getFieldValue("teacher_direction") != null) {
//                    teacher.setSubjectName(list.get(i).getFieldValue("teacher_direction").toString());
//                }
                if (list.get(i).getFieldValue("teacher_introduction") != null) {
                    teacher.setIntroduction(list.get(i).getFieldValue("teacher_introduction").toString());
                }
                if (list.get(i).getFieldValue("teacher_honor") != null) {
                    teacher.setHonor(list.get(i).getFieldValue("teacher_honor").toString());
                }
                if (list.get(i).getFieldValue("teacher_email") != null) {
                    teacher.setEmail(list.get(i).getFieldValue("teacher_email").toString());
                }
                if (list.get(i).getFieldValue("teacher_phone") != null) {
                    teacher.setPhone(list.get(i).getFieldValue("teacher_phone").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab") != null) {
                    teacher.setLabName(list.get(i).getFieldValue("teacher_lab").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab_id") != null) {
                    System.out.println("****************"+list.get(i).getFieldValue("teacher_lab_id"));
                    teacher.setLabId(Integer.parseInt(list.get(i).getFieldValue("teacher_lab_id").toString()));
                }
                if (list.get(i).getFieldValue("teacher_lab_introduction") != null) {
                    teacher.setLabIntroduction(list.get(i).getFieldValue("teacher_lab_introduction").toString());
                }
                if (list.get(i).getFieldValue("teacher_prorank") != null) {
                    teacher.setProrank(list.get(i).getFieldValue("teacher_prorank").toString());
                }
                if (list.get(i).getFieldValue("teacher_img") != null) {
                    teacher.setImg(list.get(i).getFieldValue("teacher_img").toString());
                }
                if (list.get(i).getFieldValue("teacher_lab_website") != null) {
                    teacher.setLabWebsite(list.get(i).getFieldValue("teacher_lab_website").toString());
                }
                if (list.get(i).getFieldValue("teacher_homepage") != null) {
                    teacher.setHomepage(list.get(i).getFieldValue("teacher_homepage").toString());
                }
                teachers.add(teacher);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、查询结果List<Document> 转换为List<College>
        pageResultBean.setPageNum(pageIndex);
        pageResultBean.setPageSize(rows);
        pageResultBean.setList(teachers);
        pageResultBean.setTotal(totalCount);
        pageResultBean.setPages((int) (totalCount % rows == 0 ? (totalCount / rows) : (totalCount / rows + 1)));
        pageResultBean.setNavigatePages(4);
        return pageResultBean;
    }
}
