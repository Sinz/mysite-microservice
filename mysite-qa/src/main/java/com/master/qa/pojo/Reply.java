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
 * 回答
 */
@Data
@Entity
@ApiModel("回答")
@Table(name = "tb_reply")
public class Reply  {

	/**
	 * 编号
	 */
	@Id
	@ApiModelProperty("编号")
	@Column(name = "id", nullable = false)
	private String id;

	/**
	 * 问题ID
	 */
	@ApiModelProperty("问题ID")
	@Column(name = "problemid")
	private String problemid;

	/**
	 * 回答内容
	 */
	@Column(name = "content")
	@ApiModelProperty("回答内容")
	private String content;

	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
	@Column(name = "createtime")
	private Date createtime;

	/**
	 * 更新日期
	 */
	@ApiModelProperty("更新日期")
	@Column(name = "updatetime")
	private Date updatetime;

	/**
	 * 回答人ID
	 */
	@Column(name = "userid")
	@ApiModelProperty("回答人ID")
	private String userid;

	/**
	 * 回答人昵称
	 */
	@Column(name = "nickname")
	@ApiModelProperty("回答人昵称")
	private String nickname;

}
