package com.ikang.idata.search.search.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/28
 */
@RestController
@RequestMapping("article")
public class ArticleController1 {
    @Resource
    private ArticleService articleService;

//    @GetMapping("/create")
//    public boolean create(){
//        return articleService.createIndexOfArticle();
//    }

//    @GetMapping("/delete")
//    public boolean delete() {
//        return articleService.deleteArticle();
//    }

//    @PostMapping("/add")
//    public IndexResponse add(@RequestBody ArticleEntity article){
//        return articleService.addArticle(article);
//    }

//    @GetMapping("/fransfer")
//    public String transfer(){
//        articleService.transferFromMysql();
//        return "successful";
//    }
//
//    @GetMapping("/query")
//    public List<ArticleEntity> query(String keyword){
//        return articleService.queryByKey(keyword);
//    }
}

