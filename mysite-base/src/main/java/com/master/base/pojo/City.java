package com.master.base.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 城市
 */
@Data
@Entity
@ApiModel("城市")
@Table(name = "tb_city")
public class City {

    /**
     * ID
     */
    @Id
    @ApiModelProperty("ID")
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 城市名称
     */
    @Column(name = "name")
    @ApiModelProperty("城市名称")
    private String name;

    /**
     * 是否热门
     */
    @Column(name = "ishot")
    @ApiModelProperty("是否热门")
    private String ishot;

}
