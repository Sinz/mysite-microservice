package com.master.gathering.dao;

import com.master.gathering.pojo.Usergath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsergathDao extends JpaRepository<Usergath, String>, JpaSpecificationExecutor<Usergath> {

}