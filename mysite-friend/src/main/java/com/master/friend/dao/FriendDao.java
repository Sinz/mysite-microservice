package com.master.friend.dao;

import com.master.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface FriendDao extends JpaRepository<Friend, String>, JpaSpecificationExecutor<Friend> {


    /**
     * @Author Zhao.J
     * @Description 根据用户id 和 好友id 查询记录个数
     * @Date 17:16 2021/5/11
     * @param userid :
     * @param friendid :
     * @return : int
     **/
    @Query(value = "SELECT COUNT(*) FROM tb_friend tf WHERE tf.userid = ?1 AND tf.friendid = ?2 ", nativeQuery = true)
    public int selectCount(String userid,String friendid);


    /**
     * @Author Zhao.J
     * @Description 更新互相喜欢
     * @Date 17:27 2021/5/11
     * @param userid :
     * @param friendid :
     * @param islike :
     * @return : void
     **/
    @Modifying
    @Query(value = "UPDATE tb_friend tf SET tf.islike = ?3 WHERE tf.userid = ?1 AND tf.friendid = ?2 ", nativeQuery = true)
    public void updateLike(String userid, String friendid, String islike);

    /**
     * @Author Zhao.J
     * @Description 删除好友
     * @Date 23:58 2021/5/11
     * @param userid :
     * @param friendid :
     * @return : void
     **/
    @Modifying
    @Query(value = "DELETE FROM tb_friend tf WHERE tf.userid = ?1 AND tf.userid = ?2", nativeQuery = true)
    public void deleteFriend(String userid, String friendid);
}