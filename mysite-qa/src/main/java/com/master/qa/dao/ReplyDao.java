package com.master.qa.dao;

import com.master.qa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReplyDao extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {


}