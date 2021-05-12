package com.master.recruit.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 职位
 */
@Data
@Entity
@ApiModel("职位")
@Table(name = "tb_recruit")
public class Recruit {

    /**
     * ID
     */
    @Id
    @ApiModelProperty("ID")
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 职位名称
     */
    @Column(name = "jobname")
    @ApiModelProperty("职位名称")
    private String jobname;

    /**
     * 薪资范围
     */
    @Column(name = "salary")
    @ApiModelProperty("薪资范围")
    private String salary;

    /**
     * 经验要求
     */
    @ApiModelProperty("经验要求")
    @Column(name = "condition")
    private String condition;

    /**
     * 学历要求
     */
    @ApiModelProperty("学历要求")
    @Column(name = "education")
    private String education;

    /**
     * 任职方式
     */
    @Column(name = "type")
    @ApiModelProperty("任职方式")
    private String type;

    /**
     * 办公地址
     */
    @Column(name = "address")
    @ApiModelProperty("办公地址")
    private String address;

    /**
     * 企业ID
     */
    @Column(name = "eid")
    @ApiModelProperty("企业ID")
    private String eid;

    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    @Column(name = "createtime")
    private Date createtime;

    /**
     * 状态
     */
    @Column(name = "state")
    @ApiModelProperty("状态")
    private String state;

    /**
     * 网址
     */
    @Column(name = "url")
    @ApiModelProperty("网址")
    private String url;

    /**
     * 标签
     */
    @Column(name = "label")
    @ApiModelProperty("标签")
    private String label;

    /**
     * 职位描述
     */
    @ApiModelProperty("职位描述")
    @Column(name = "content1")
    private String content1;

    /**
     * 职位要求
     */
    @ApiModelProperty("职位要求")
    @Column(name = "content2")
    private String content2;

}
