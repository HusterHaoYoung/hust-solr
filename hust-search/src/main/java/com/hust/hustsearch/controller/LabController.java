package com.hust.hustsearch.controller;

import com.hust.hustsearch.dao.LabDao;
import com.hust.hustsearch.dao.ResearchDirectionDao;
import com.hust.hustsearch.dao.ResearchResultDao;
import com.hust.hustsearch.dao.TeacherDao;
import com.hust.hustsearch.entity.*;
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
    @Autowired
    private ResearchDirectionDao researchDirectionDao;
    @Autowired
    private ResearchResultDao researchResultDao;
    @Autowired
    private TeacherDao teacherDao;
    @RequestMapping("initAllData")
    @ResponseBody
    public String initAllLabData() throws IOException, SolrServerException {
        System.out.println("--------------getting lab's data");
        return labSearchService.initAllData();
    }

    @GetMapping("page/{pageIndex}/{pageSize}")
    public String searchLabNamePage(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize, String labName, Model model) {
        System.out.println("---------------" + pageIndex + "---" + pageSize + "----" + labName);

        PageResultBean<Lab> labPageResultBean = labSearchService.searchByLabName(labName, pageIndex, pageSize);
        System.out.println("---size=" + labPageResultBean.getList().size());
        HashMap<Integer, List<ResearchDirection>> hashMap = new HashMap<>();
        HashMap<Integer,List<Teacher>> hashMap1 = new HashMap<>();
        HashMap<Integer,List<ResearchResult>> researchResultMap = new HashMap<>();
        for (Lab l : labPageResultBean.getList()) {
            hashMap.put(l.getId(), researchDirectionDao.findByLabId(l.getId()));
            hashMap1.put(l.getId(),teacherDao.findByLabId(l.getId()));
            researchResultMap.put(l.getId(),researchResultDao.findByLabId(l.getId()));
        }
        model.addAttribute("pageInfo", labPageResultBean);
        model.addAttribute("hashMap", hashMap);
        model.addAttribute("teacherMap",hashMap1);
        model.addAttribute("researchResultMap",researchResultMap);
        return "lab";
    }
}
