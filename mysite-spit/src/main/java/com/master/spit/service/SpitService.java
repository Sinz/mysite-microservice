package com.master.spit.service;

import com.master.spit.dao.SpitDao;
import com.master.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import common.util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Classname SpitService
 * @Description mongodb 的操作与 jpa 基本一致
 * @Date 2021/4/28 17:50
 * @Created by Zhao.J
 */

@Service
@Transactional
public class SpitService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }


    /**
     * @Author Zhao.J
     * @Description 发布吐槽
     * @Date 22:35 2021/4/28
     * @param spit :
     * @return : void
     **/
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date()); // 发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

        // 如果存在父节点，父节点回复数 + 1
        spit.getParentid();
        if (spit.getParentid() != null && !"".equals(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void delete(String id){
        spitDao.deleteById(id);
    }

    /**
     * @Author Zhao.J
     * @Description 根据父节点查询
     * @Date 21:50 2021/4/28
     * @param parentid :
     * @param page :
     * @param size :
     * @return : org.springframework.data.domain.Page<com.master.spit.pojo.Spit>
     **/
    public Page<Spit> findByParentId(String parentid, int page, int size){

        Pageable pageable = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid, pageable);
    }

    /**
     * @Author Zhao.J
     * @Description 点赞 使用原生查询更新优化效率
     * @Date 22:03 2021/4/28
     * @param spitId :
     * @return : void
     **/
    public void thumbup(String spitId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query,update,"spit");

    }

}
