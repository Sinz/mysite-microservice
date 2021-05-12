package com.master.friend.service;

import com.master.friend.client.UserClient;
import com.master.friend.dao.FriendDao;
import com.master.friend.dao.NofriendDao;
import com.master.friend.pojo.Friend;
import com.master.friend.pojo.Nofriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname FriendService
 * @Description TODO
 * @Date 2021/5/11 17:35
 * @Created by Zhao.J
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private NofriendDao nofriendDao;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private UserClient userClient;

    public int addFriend(String userid, String friendid){

        // 判断两人是否为好友
        if (friendDao.selectCount(userid, friendid) > 0){
            return 0;
        }

        // 增加好友记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        // 判断对方是否为你的好友, 若是, 则更新为互相喜欢
        if (friendDao.selectCount(friendid, userid) > 0){
            friendDao.updateLike(userid, friendid, "1");
            friendDao.updateLike(friendid, userid, "1");
        }

        userClient.incFollowcount(userid,1);//增加自己的关注数
        userClient.incFanscount(friendid,1);//增加对方的粉丝数
        return 1;
    }


    /**
     * @Author Zhao.J
     * @Description 向不喜欢列表中添加记录
     * @Date 23:30 2021/5/11
     * @param userid :
     * @param friendid :
     * @return : void
     **/
    public void addNoFriend(String userid , String friendid){
        Nofriend nofriend = new Nofriend();
        nofriend.setUserid(userid);
        nofriend.setFriendid(friendid);
        nofriendDao.save(nofriend);
    }

    /**
     * @Author Zhao.J
     * @Description  拉黑
     * @Date 0:00 2021/5/12
     * @param userid :
     * @param friendid :
     * @return : void
     **/
    public void deleteFriend(String userid, String friendid ){
        friendDao.deleteFriend(userid,friendid);
        friendDao.updateLike(friendid,userid,"0");
        addNoFriend(userid,friendid);//向不喜欢表中添加记录

        userClient.incFollowcount(userid,-1);//减少自己的关注数
        userClient.incFanscount(friendid,-1);//减少对方的粉丝数
    }


}
