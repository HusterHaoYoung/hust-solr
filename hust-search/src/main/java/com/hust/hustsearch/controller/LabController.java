package com.hust.hustsearch.controller;

import com.hust.hustsearch.dao.LabDao;
import com.hust.hustsearch.entity.PageResultBean;
import com.hust.hustsearch.entity.ResearchDirection;
import com.hust.hustsearch.entity.Teacher;
import com.hust.hustsearch.service.LabSearchService;
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
 * @ClassName: LabController
 * @Description: TODO
 * @date : 12/2/19  11:45 AM
 */

@Controller
@RequestMapping("searchLab")
public class LabController {
    @Autowired
    private LabSearchService labSearchService;
    @RequestMapping("initAllData")
    @ResponseBody
    public String initAllLabData() throws IOException, SolrServerException {
        System.out.println("--------------getting lab's data");
        return labSearchService.initAllData();
    }
    @GetMapping("page/{pageIndex}/{pageSize}")
    public String searchLabNamePage(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize, String labName, Model model) {
        System.out.println("---------------" + pageIndex+"---"+pageSize+"----"+labName);
//        List<Teacher> teachers = teacherSearchService.searchByName(teacherName);

//        PageResultBean<Teacher> teachers = teacherSearchService.searchByTeacherName(teacherName, pageIndex,pageSize);
//        System.out.println("---size="+teachers.getList().size());
        HashMap<Integer, List<ResearchDirection>> hashMap = new HashMap<>();
//        for (Teacher t:teachers.getList()){
//            hashMap.put(t.getId(),researchDirectionDao.findByTeacherId(t.getId()));
//        }
//        model.addAttribute("pageInfo", teachers);
        model.addAttribute("hashmap",hashMap);
        return "lab";
    }
}
