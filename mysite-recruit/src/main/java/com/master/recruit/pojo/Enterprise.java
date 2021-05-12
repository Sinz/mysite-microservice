package com.master.recruit.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 企业
 */
@Data
@Entity
@ApiModel("企业")
@Table(name = "tb_enterprise")
public class Enterprise {

    /**
     * ID
     */
    @Id
    @ApiModelProperty("ID")
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 企业名称
     */
    @Column(name = "name")
    @ApiModelProperty("企业名称")
    private String name;

    /**
     * 企业简介
     */
    @Column(name = "summary")
    @ApiModelProperty("企业简介")
    private String summary;

    /**
     * 企业地址
     */
    @Column(name = "address")
    @ApiModelProperty("企业地址")
    private String address;

    /**
     * 标签列表
     */
    @Column(name = "labels")
    @ApiModelProperty("标签列表")
    private String labels;

    /**
     * 坐标
     */
    @ApiModelProperty("坐标")
    @Column(name = "coordinate")
    private String coordinate;

    /**
     * 是否热门
     */
    @Column(name = "ishot")
    @ApiModelProperty("是否热门")
    private String ishot;

    /**
     * LOGO
     */
    @Column(name = "logo")
    @ApiModelProperty("LOGO")
    private String logo;

    /**
     * 职位数
     */
    @ApiModelProperty("职位数")
    @Column(name = "jobcount")
    private Integer jobcount;

    /**
     * URL
     */
    @Column(name = "url")
    @ApiModelProperty("URL")
    private String url;

}
