package com.hust.hustsearch.controller;


import com.hust.hustsearch.dao.LabDao;
import com.hust.hustsearch.dao.TeacherDao;
import com.hust.hustsearch.entity.College;
import com.hust.hustsearch.entity.Lab;
import com.hust.hustsearch.entity.Teacher;
import com.hust.hustsearch.service.SearchService;
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
    private SearchService searchService;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private LabDao labDao;
    @RequestMapping("initAllData")
    @ResponseBody
    public String initAllData() throws IOException, SolrServerException {
        System.out.println("--------------");
        return searchService.initAllData();
    }
    @RequestMapping("searchByKeyWord")
    public String searchByKeyword(String keyWord, Model model){
        System.out.println("---------------"+keyWord);
        College college = searchService.searchByKeyword(keyWord);
        List<Teacher> teachers = teacherDao.findByCollegeId(college.getId(),8);
        List<Lab> labs = labDao.findByCollegeId(college.getId(),10);
        model.addAttribute("college",college);
        model.addAttribute("teachers",teachers);
        model.addAttribute("labs",labs);
        return "college";
    }
}
