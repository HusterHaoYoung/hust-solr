package com.hust.hustsearch.controller;


import com.hust.hustsearch.dao.LabDao;
import com.hust.hustsearch.dao.ResearchDirectionDao;
import com.hust.hustsearch.dao.TeacherDao;
import com.hust.hustsearch.entity.*;
import com.hust.hustsearch.service.CollegeSearchService;
import com.hust.hustsearch.service.TeacherSearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author :younghao
 * @ClassName: TeacherController
 * @Description: TODO
 * @date : 12/2/19  11:07 AM
 */
@Controller
@RequestMapping("searchTeacher")
public class TeacherController {

    @Autowired
    private TeacherSearchService teacherSearchService;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private LabDao labDao;
    @Autowired
    private ResearchDirectionDao researchDirectionDao;
    @RequestMapping("initAllData")
    @ResponseBody
    public String initAllTeacherData() throws IOException, SolrServerException {
        System.out.println("--------------getting teacher's data");
        return teacherSearchService.initAllData();
    }

    @GetMapping("page/{pageIndex}/{pageSize}")
    public String teacherPage(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize, String teacherName,Model model) {
        System.out.println("---------------" + pageIndex+"---"+pageSize+"----"+teacherName);
//        List<Teacher> teachers = teacherSearchService.searchByName(teacherName);

        PageResultBean<Teacher> teachers = teacherSearchService.searchByTeacherName(teacherName, pageIndex,pageSize);
        System.out.println("---size="+teachers.getList().size());
        HashMap<Integer,List<ResearchDirection>> hashMap = new HashMap<>();
        for (Teacher t:teachers.getList()){
            hashMap.put(t.getId(),researchDirectionDao.findByTeacherId(t.getId()));
        }
        model.addAttribute("pageInfo", teachers);
        model.addAttribute("hashmap",hashMap);
        return "teacher";
    }
}
