package com.master.base.controller;

import com.master.base.pojo.Label;
import com.master.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname BaseController
 * @Description 标签页
 * @Date 2021/4/26 11:00
 * @Created by Zhao.J
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
@Api("标签页")
public class LabelController {

    @Resource
    private HttpServletRequest request;

    @Autowired
    private LabelService labelService;

    @ApiOperation("findById: 所有标签页")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){

        return new Result(true, StatusCode.OK, "查询成功",labelService.findAll());
    }

    @ApiOperation("findById: 根据labelId查询标签页")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(
            @ApiParam(name = "labelId", value = "标签Id", required = true)
            @PathVariable("labelId") String labelId){
        return new Result(true, StatusCode.OK, "查询成功",labelService.findById(labelId));
    }

    @ApiOperation("save: 根据label对象保存标签页")
    @RequestMapping( method = RequestMethod.POST )
    public Result save(
        @ApiParam(name = "label", value = "标签对象", required = true)
        @RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @ApiOperation("update: 根据labelId / label对象修改标签页")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(
            @ApiParam(name = "labelId", value = "标签Id", required = true)
            @PathVariable String labelId,
            @ApiParam(name = "label", value = "标签对象", required = true)
            @RequestBody  Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @ApiOperation("deleteById: 根据labelId删除标签页")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(
            @ApiParam(name = "labelId", value = "标签Id", required = true)
            @PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    @ApiOperation("findSearch： 根据label对象条件查找")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(
            @ApiParam(name = "label", value = "标签对象", required = true)
            @RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查找成功", list);
    }


    @ApiOperation("pageQuery： 根据label/page/size条件分页查找")
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result pageQuery(
            @ApiParam(name = "label", value = "标签对象", required = true)
            @RequestBody Label label,
            @ApiParam(name = "page", value = "页码", required = true)
            @PathVariable int page,
            @ApiParam(name = "size", value = "页面大小", required = true)
            @PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label, page , size);
        return new Result(true, StatusCode.OK, "查找成功", new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
