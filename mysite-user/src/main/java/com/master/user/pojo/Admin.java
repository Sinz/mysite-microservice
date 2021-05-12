package com.master.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 管理员
 */
@Data
@Entity
@ApiModel("管理员")
@Table(name = "tb_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @ApiModelProperty("ID")
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 登陆名称
     */
    @ApiModelProperty("登陆名称")
    @Column(name = "loginname")
    private String loginname;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @Column(name = "password")
    private String password;

    /**
     * 状态
     */
    @Column(name = "state")
    @ApiModelProperty("状态")
    private String state;

}
