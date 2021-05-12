package com.master.qa.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 问题
 */
@Data
@Entity
@ApiModel("问题")
@Table(name = "tb_problem")
public class Problem  {

	/**
	 * ID
	 */
	@Id
	@ApiModelProperty("ID")
	@Column(name = "id", nullable = false)
	private String id;

	/**
	 * 标题
	 */
	@Column(name = "title")
	@ApiModelProperty("标题")
	private String title;

	/**
	 * 内容
	 */
	@ApiModelProperty("内容")
	@Column(name = "content")
	private String content;

	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
	@Column(name = "createtime")
	private Date createtime;

	/**
	 * 修改日期
	 */
	@ApiModelProperty("修改日期")
	@Column(name = "updatetime")
	private Date updatetime;

	/**
	 * 用户ID
	 */
	@Column(name = "userid")
	@ApiModelProperty("用户ID")
	private String userid;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	@Column(name = "nickname")
	private String nickname;

	/**
	 * 浏览量
	 */
	@Column(name = "visits")
	@ApiModelProperty("浏览量")
	private Long visits;

	/**
	 * 点赞数
	 */
	@ApiModelProperty("点赞数")
	@Column(name = "thumbup")
	private Long thumbup;

	/**
	 * 回复数
	 */
	@Column(name = "reply")
	@ApiModelProperty("回复数")
	private Long reply;

	/**
	 * 是否解决
	 */
	@Column(name = "solve")
	@ApiModelProperty("是否解决")
	private String solve;

	/**
	 * 回复人昵称
	 */
	@ApiModelProperty("回复人昵称")
	@Column(name = "replyname")
	private String replyname;

	/**
	 * 回复日期
	 */
	@ApiModelProperty("回复日期")
	@Column(name = "replytime")
	private Date replytime;

}
