package com.hust.hustsearch.controller;


import com.hust.hustsearch.dao.LabDao;
import com.hust.hustsearch.dao.ProvinceDao;
import com.hust.hustsearch.dao.TeacherDao;
import com.hust.hustsearch.dao.WebnewsDao;
import com.hust.hustsearch.entity.*;
import com.hust.hustsearch.service.CollegeSearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @author :younghao
 * @ClassName: CollegeController
 * @Description: TODO
 * @date : 11/27/19  7:24 PM
 */
@Controller
@RequestMapping("search")
public class CollegeController {
    @Autowired
    private CollegeSearchService collegeSearchService;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private LabDao labDao;
    @Autowired
    private WebnewsDao webnewsDao;
    @Autowired
    private ProvinceDao provinceDao;

    @RequestMapping("initAllData")
    @ResponseBody
    public String initAllData() throws IOException, SolrServerException {
        System.out.println("--------------");
        return collegeSearchService.initAllData();
    }

    @RequestMapping("searchByKeyWord")
    public String searchByKeyword(String keyWord, Model model) {
        System.out.println("---------------" + keyWord);
        College college = collegeSearchService.searchByCollegeName(keyWord);
//        List<Teacher> teachers = teacherDao.findByCollegeId(college.getId(),8);
        List<Teacher> teachers = teacherDao.findByCollegeId(college.getId());
        List<Lab> labs = labDao.findByCollegeId(college.getId(), 10);
        List<Webnews> webnewsList = webnewsDao.findByWid("c" + college.getId(), 5);
        List<Province> provinces = provinceDao.findAll();
        model.addAttribute("college", college);
        model.addAttribute("teachers", teachers);
        model.addAttribute("labs", labs);
        model.addAttribute("webnewsList",webnewsList);
        model.addAttribute("provinces",provinces);
        return "college";
    }
}
