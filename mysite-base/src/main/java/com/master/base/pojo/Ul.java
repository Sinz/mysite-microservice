package com.master.base.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_ul")
public class Ul {

    @Id
    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "labelid", nullable = false)
    private String labelid;

}
