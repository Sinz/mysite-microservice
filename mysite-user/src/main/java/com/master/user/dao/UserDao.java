package com.master.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.master.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    /**
     * @Author Zhao.J
     * @Description 手机号查询用户
     * @Date 16:53 2021/5/8
     * @param mobile :
     * @return : com.master.user.pojo.User
     **/
    User findByMobile(String mobile);

    /**
     * @Author Zhao.J
     * @Description 判断号码是否被注册
     * @Date 17:11 2021/5/8
     * @param mobile :
     * @return : boolean
     **/
    boolean existsByMobile(String mobile);

    /**
     * @Author Zhao.J
     * @Description 增加粉丝数
     * @Date 0:07 2021/5/12
     * @param userid :
     * @param x :
     * @return : void
     **/
    @Modifying
    @Query(value = "UPDATE tb_user tu SET tu.fanscount = tu.fanscount + ?2 where tu.id = ?1 " ,nativeQuery = true)
    public void incFansCount(String userid ,int x);

    /**
     * @Author Zhao.J
     * @Description 增加关注数
     * @Date 0:11 2021/5/12
     * @param userid :
     * @param x :
     * @return : void
     **/
    @Modifying
    @Query(value = "UPDATE tb_user tu SET tu.followcount = tu.followcount + ?2 where tu.id = ?1; " ,nativeQuery = true)
    public void incFollowCount(String userid ,int x);
}
