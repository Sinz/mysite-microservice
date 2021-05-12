package com.master.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Spit
 * @Description 使用mongodb 注意id 需要添加_
 * @Date 2021/4/28 17:25
 * @Created by Zhao.J
 */

@Data
public class Spit implements Serializable {

    @Id
    private String _id;

    private String contend;
    private Date publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;


}
