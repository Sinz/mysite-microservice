package com.master.gathering.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动
 */
@Data
@Entity
@ApiModel("活动")
@Table(name = "tb_gathering")
public class Gathering implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@Id
	@ApiModelProperty("编号")
	@Column(name = "id", nullable = false)
	private String id;

	/**
	 * 活动名称
	 */
	@Column(name = "name")
	@ApiModelProperty("活动名称")
	private String name;

	/**
	 * 大会简介
	 */
	@Column(name = "summary")
	@ApiModelProperty("大会简介")
	private String summary;

	/**
	 * 详细说明
	 */
	@Column(name = "detail")
	@ApiModelProperty("详细说明")
	private String detail;

	/**
	 * 主办方
	 */
	@ApiModelProperty("主办方")
	@Column(name = "sponsor")
	private String sponsor;

	/**
	 * 活动图片
	 */
	@Column(name = "image")
	@ApiModelProperty("活动图片")
	private String image;

	/**
	 * 开始时间
	 */
	@ApiModelProperty("开始时间")
	@Column(name = "starttime")
	private Date starttime;

	/**
	 * 截止时间
	 */
	@Column(name = "endtime")
	@ApiModelProperty("截止时间")
	private Date endtime;

	/**
	 * 举办地点
	 */
	@Column(name = "address")
	@ApiModelProperty("举办地点")
	private String address;

	/**
	 * 报名截止
	 */
	@ApiModelProperty("报名截止")
	@Column(name = "enrolltime")
	private Date enrolltime;

	/**
	 * 是否可见
	 */
	@Column(name = "state")
	@ApiModelProperty("是否可见")
	private String state;

	/**
	 * 城市
	 */
	@Column(name = "city")
	@ApiModelProperty("城市")
	private String city;

}
