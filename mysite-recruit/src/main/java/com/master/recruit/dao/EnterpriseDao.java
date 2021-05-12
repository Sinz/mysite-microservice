package com.master.recruit.dao;

import com.master.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor {

    public List<Enterprise> findByIshot(String isHot);

}