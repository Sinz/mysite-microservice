package com.master.base.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Classname Label
 * @Description 标签类
 * @Date 2021/4/26 13:21
 * @Created by Zhao.J
 */
@Data
@Entity
@Table( name = "tb_label")
@ApiModel("标签")
public class Label {

    /**
     * 标签ID
     */
    @Id
    @ApiModelProperty("标签ID")
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    @Column(name = "labelname")
    private String labelname;

    /**
     * 状态
     */
    @Column(name = "state")
    @ApiModelProperty("状态")
    private String state;

    /**
     * 使用数量
     */
    @Column(name = "count")
    @ApiModelProperty("使用数量")
    private Long count;

    /**
     * 是否推荐
     */
    @ApiModelProperty("是否推荐")
    @Column(name = "recommend")
    private String recommend;

    /**
     * 粉丝数
     */
    @Column(name = "fans")
    @ApiModelProperty("粉丝数")
    private Long fans;
}
