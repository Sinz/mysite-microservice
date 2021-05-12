package com.master.recruit.dao;

import com.master.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor {

    /**
     * @Classname RecruitDao
     * @Description 根据state 查看推荐职位
     * @Date 2021/4/27 17:06
     * @Author by Zhao.J
     */
     List<Recruit> findAllByState(String state);


    /**
     * @Classname RecruitDao
     * @Description 返回最新6个推荐职位， findBy 哪个条件， 就需要传入哪个条件
     * @Date 2021/4/27 17:15
     * @Author by Zhao.J
     */
     List<Recruit> findTop2ByStateOrderByCreatetime(String state);

}