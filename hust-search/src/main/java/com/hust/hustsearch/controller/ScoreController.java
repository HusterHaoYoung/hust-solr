package com.hust.hustsearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hust.hustsearch.dao.MajorScoreDao;
import com.hust.hustsearch.entity.MajorScore;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author :younghao
 * @ClassName: ScoreController
 * @Description: TODO
 * @date : 12/11/19  3:21 PM
 */
@Controller
@RequestMapping("score")
public class ScoreController {
    @Autowired
    private MajorScoreDao majorScoreDao;

    @RequestMapping("getScore")
    @ResponseBody
    public JSONArray getScore(int provinceId, int year, int collegeId, int branchId){
        System.out.println("provinceId=" + provinceId + "  branch = " + branchId + "   year=" + year + "  collegeID = " + collegeId);
        List<MajorScore> majorScores = majorScoreDao.findScore(provinceId, year, collegeId, branchId);
        System.out.println(majorScores.size());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < majorScores.size(); i++) {
            System.out.println(majorScores.get(i));
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(majorScores.get(i)));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
