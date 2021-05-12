package com.master.friend.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;



@Data
@Entity
@IdClass(Nofriend.class)
@Table(name = "tb_nofriend")
public class Nofriend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id
    @ApiModelProperty("用户ID")
    @Column(name = "userid", nullable = false)
    private String userid;

    /**
     * 好友ID
     */
    @Id
    @ApiModelProperty("好友ID")
    @Column(name = "friendid", nullable = false)
    private String friendid;

}
