package com.hust.hustsearch.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author :younghao
 * @ClassName: IndexController
 * @Description: TODO
 * @date : 11/29/19  10:46 AM
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() throws IOException, SolrServerException {
        System.out.println("--------------");
        return "index";
    }
}
