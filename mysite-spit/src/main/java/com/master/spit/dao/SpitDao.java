package com.master.spit.dao;

import com.master.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Classname SpitDao
 * @Description
 * @Date 2021/4/28 17:49
 * @Created by Zhao.J
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    Page<Spit> findByParentid(String parentid , Pageable pageable);
}
