package com.master.search.controller;

import com.master.search.pojo.Article;
import com.master.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname ArticleController
 * @Description TODO
 * @Date 2021/5/6 16:22
 * @Created by Zhao.J
 */

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {


    @Autowired
    private ArticleSearchService articleSearchService;

    @ApiOperation("save： 文章添加")
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@ApiParam(name = "article" , value = "文章" , required = true) @RequestBody Article article){
        articleSearchService.save(article);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @ApiOperation("findByKey： 文章主键查询")
    @RequestMapping(value = "/search/{key}/{page}/{size}", method = RequestMethod.GET)
    public Result findByKey(
            @ApiParam(name = "key" , value = "主键" , required = true) @PathVariable String key,
            @ApiParam(name = "page" , value = "页码" , required = true) @PathVariable int page,
            @ApiParam(name = "size" , value = "数量" , required = true) @PathVariable int size){

        Page<Article> pageData = articleSearchService.findByKey(key, page, size);
        return new Result(true,StatusCode.OK,"查询成功", new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));

    }
}
