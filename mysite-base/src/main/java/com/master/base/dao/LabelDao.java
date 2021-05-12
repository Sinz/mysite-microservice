package com.master.base.dao;


import com.master.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname LabelRepository
 * @Description label实体类JPA
 * @Date 2021/4/26 13:56
 * @Created by Zhao.J
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor {
}
