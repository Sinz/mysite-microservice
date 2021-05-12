package com.master.qa.controller;
import java.util.Map;

import com.master.qa.client.BaseClient;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.master.qa.pojo.Problem;
import com.master.qa.service.ProblemService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;

	@Resource
	private HttpServletRequest request;

	@Autowired
	private BaseClient baseClient;

	@ApiOperation("findLabelById: 根据id查找标签")
	@RequestMapping(value = "/label/{labelid}", method = RequestMethod.GET)
	public Result findLabelById(@PathVariable String labelid){
		Result result = baseClient.findById((labelid));
		return result;
	}

	@ApiOperation("newList: 最新回答")
	@RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result newList(
			@ApiParam(name = "labelid" , value = "标签页" , required = true)
			@PathVariable String labelid,
			@ApiParam(name = "page" , value = "页码" , required = true)
			@PathVariable int page,
			@ApiParam(name = "size" , value = "数量" , required = true)
			@PathVariable int size){

		return new Result(true,StatusCode.OK,"查询成功", problemService.newList(labelid, page, size));

	}

	@ApiOperation("hotlist: 最热回答")
	@RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result hotList(
			@ApiParam(name = "labelid" , value = "标签页" , required = true)
			@PathVariable String labelid,
			@ApiParam(name = "page" , value = "页码" , required = true)
			@PathVariable int page,
			@ApiParam(name = "size" , value = "数量" , required = true)
			@PathVariable int size){

		return new Result(true,StatusCode.OK,"查询成功", problemService.hotList(labelid, page, size));

	}

	@ApiOperation("waitlist: 等待回答")
	@RequestMapping(value = "/waitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result waitList(
			@ApiParam(name = "labelid" , value = "标签页" , required = true)
			@PathVariable String labelid,
			@ApiParam(name = "page" , value = "页码" , required = true)
			@PathVariable int page,
			@ApiParam(name = "size" , value = "数量" , required = true)
			@PathVariable int size){

		return new Result(true,StatusCode.OK,"查询成功", problemService.waitList(labelid, page, size));

	}

	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		Claims claims = (Claims) request.getAttribute("user_claims");
		if (claims == null){
			return new Result(true,StatusCode.ACCESSERROR,"权限不足");
		}

		problem.setUserid(claims.getId());
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}


}
