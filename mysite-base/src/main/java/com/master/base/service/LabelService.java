package com.master.base.service;

import com.master.base.pojo.Label;
import com.master.base.dao.LabelDao;
import util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname LabelService
 * @Description label标签页服务
 * @Date 2021/4/26 14:01
 * @Created by Zhao.J
 */

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }


    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             * @Author Zhao.J
             * @Description 条件查询
             * @Date 16:12 2021/4/26
             * @Param [root 根对象。 where 类名 = label.getId]
             * @Param [criteriaQuery 查询关键字。 如group by / order by]
             * @Param [criteriaBuilder 封装条件对象， 如果直接返回null, 表示没有条件]
             * @return javax.persistence.criteria.Predicate
             **/
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<Predicate>();
                if(label.getLabelname() != null && !"".equals((label.getLabelname()))){
                    // where labelname like "% %"
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals((label.getState()))){
                    // where state = ""
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                parr = list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });
    }



    /**
     * @Author Zhao.J
     * @Description 封装分页对象
     * @Date 17:43 2021/4/26
     * @param label :
     * @param page :
     * @param size :
     * @return : com.github.pagehelper.Page<com.master.base.pojo.Label>
     **/
    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return (Page<Label>) labelDao.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(label.getLabelname() != null && !"".equals((label.getLabelname()))){
                    // where labelname like "% %"
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals((label.getState()))){
                    // where state = ""
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                parr = list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });

    }
}
