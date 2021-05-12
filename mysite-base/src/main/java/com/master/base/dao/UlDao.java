package com.master.base.dao;

import com.master.base.pojo.Ul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UlDao extends JpaRepository<Ul, String>, JpaSpecificationExecutor {

}