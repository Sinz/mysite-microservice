package com.master.friend.controller;

import com.master.friend.service.FriendService;
import common.entity.Result;
import common.entity.StatusCode;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname FriendController
 * @Description TODO
 * @Date 2021/5/11 17:34
 * @Created by Zhao.J
 */
@CrossOrigin
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Resource
    private HttpServletRequest request;

    @ApiOperation("addFriend: 添加好友")
    @RequestMapping(value="/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type){
        Claims claims = (Claims)request.getAttribute("user_claims");

        if(claims == null){
            return new Result(true, StatusCode.LOGINERROR,"权限不足");
        }

        if (type.equals("1")){

            int flag = friendService.addFriend(claims.getId(), friendid);
            if( flag == 0){
                return new Result(true,StatusCode.REPERROR,"已添加过此好友");
            }
            if (flag == 1){
                return new Result(true,StatusCode.OK,"添加成功");
            }

        }else if(type.equals("2")){
            friendService.addNoFriend(claims.getId(), friendid);
            return new Result(true,StatusCode.OK,"添加成功");
        }
        return new Result(true,StatusCode.OK,"添加成功");
    }


    @ApiOperation("remove: 拉黑")
    @RequestMapping(value="/{friendid}",method=RequestMethod.DELETE)
    public Result remove(@PathVariable String friendid){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){ return new Result(false, StatusCode.ACCESSERROR,"无权访问"); }
        friendService.deleteFriend(claims.getId(), friendid);
        return new Result(true, StatusCode.OK, "删除成功"); }

}
