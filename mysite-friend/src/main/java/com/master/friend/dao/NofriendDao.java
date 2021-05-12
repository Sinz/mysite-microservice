package com.master.friend.dao;

import com.master.friend.pojo.Nofriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface NofriendDao extends JpaRepository<Nofriend, String>, JpaSpecificationExecutor<Nofriend> {

}