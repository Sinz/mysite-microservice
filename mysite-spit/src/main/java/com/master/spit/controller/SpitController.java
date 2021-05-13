package com.master.spit.controller;

import com.master.spit.pojo.Spit;
import com.master.spit.service.SpitService;
import common.entity.PageResult;
import common.entity.Result;
import common.entity.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname SpitController
 * @Description TODO 增加浏览量 与 分享数
 * @Date 2021/4/28 19:48
 * @Created by Zhao.J
 */

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("findAll: 查询所有")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }


    @ApiOperation("findById： 查询单个")
    @ApiParam(name = "id" , value = "id",  required = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功", spitService.findById(id));
    }

    @ApiOperation("save： 保存")
    @ApiParam(name = "spit" , value = "spit",  required = true)
    @RequestMapping( method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @ApiOperation("update： 修改")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@ApiParam(name = "spit" , value = "spit",  required = true) @RequestBody Spit spit,
                         @ApiParam(name = "id" , value = "id",  required = true) @PathVariable String id){
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @ApiOperation("delete： 删除")
    @ApiParam(name = "spitId" , value = "spitId",  required = true)
    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result delete(@RequestBody String spitId){
        spitService.delete(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @ApiOperation("findByParentId： 根据父节点查询")
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentId(
            @ApiParam(name = "parentid" , value = "parentid",  required = true) @PathVariable String parentid,
            @ApiParam(name = "page" , value = "page",  required = true) @PathVariable int page,
            @ApiParam(name = "size" , value = "size",  required = true) @PathVariable int size){
        Page<Spit> pageData = spitService.findByParentId( parentid,  page,  size);
        return new Result(true,StatusCode.OK,"查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    @ApiOperation("thumbup： 点赞")
    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result thumbup(
            @ApiParam(name = "spitId" , value = "spitId",  required = true) @PathVariable String spitId){

        String userid = "haha";
        if(redisTemplate.opsForValue().get("thumbup_" + userid + "_" + spitId) != null){
            return new Result(true,StatusCode.REPERROR,"您已点过赞了");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userid + "_" + spitId , 1);
        return new Result(true,StatusCode.OK,"点赞成功");
    }


}
