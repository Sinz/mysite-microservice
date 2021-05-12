package com.master.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
@Entity
@ApiModel("用户")
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@ApiModelProperty("ID")
	@Column(name = "id", nullable = false)
	private String id;

	/**
	 * 手机号码
	 */
	@Column(name = "mobile")
	@ApiModelProperty("手机号码")
	@Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$",message = "电话号码格式不正确")
	@NotBlank(message = "电话号码不允许为空")
	private String mobile;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@Column(name = "password")
	private String password;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	@Column(name = "nickname")
	private String nickname;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	@ApiModelProperty("性别")
	private String sex;

	/**
	 * 出生年月日
	 */
	@Column(name = "birthday")
	@ApiModelProperty("出生年月日")
	private Date birthday;

	/**
	 * 头像
	 */
	@ApiModelProperty("头像")
	@Column(name = "avatar")
	private String avatar;

	/**
	 * E-Mail
	 */
	@Column(name = "email")
	@ApiModelProperty("E-Mail")
	private String email;

	/**
	 * 注册日期
	 */
	@Column(name = "regdate")
	@ApiModelProperty("注册日期")
	private Date regdate;

	/**
	 * 修改日期
	 */
	@ApiModelProperty("修改日期")
	@Column(name = "updatedate")
	private Date updatedate;

	/**
	 * 最后登陆日期
	 */
	@Column(name = "lastdate")
	@ApiModelProperty("最后登陆日期")
	private Date lastdate;

	/**
	 * 在线时长（分钟）
	 */
	@Column(name = "online")
	@ApiModelProperty("在线时长（分钟）")
	private Long online;

	/**
	 * 兴趣
	 */
	@ApiModelProperty("兴趣")
	@Column(name = "interest")
	private String interest;

	/**
	 * 个性
	 */
	@ApiModelProperty("个性")
	@Column(name = "personality")
	private String personality;

	/**
	 * 粉丝数
	 */
	@ApiModelProperty("粉丝数")
	@Column(name = "fanscount")
	private Integer fanscount;

	/**
	 * 关注数
	 */
	@ApiModelProperty("关注数")
	@Column(name = "followcount")
	private Integer followcount;

}
