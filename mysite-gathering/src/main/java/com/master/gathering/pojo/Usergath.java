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
 * 用户关注活动
 */
@Data
@Entity
@ApiModel("用户关注活动")
@Table(name = "tb_usergath")
public class Usergath implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id
    @ApiModelProperty("用户ID")
    @Column(name = "userid", nullable = false)
    private String userid;

    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    @Column(name = "gathid", nullable = false)
    private String gathid;

    /**
     * 点击时间
     */
    @Column(name = "exetime")
    @ApiModelProperty("点击时间")
    private Date exetime;

}
