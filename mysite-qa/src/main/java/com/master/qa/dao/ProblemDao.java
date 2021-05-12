package com.master.qa.dao;

import com.master.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


/**
 * @Classname ProblemDao
 * @Description 使用原生sql时，需要注意： 当选择页面size 数量小于 数据库中的数据数量时，会进行count操作
 *              需要自己定义countQuery !
 * @Date 2021/4/27 22:20
 * @Author by Zhao.J
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {


    /**
     * @Author Zhao.J
     * @Description  根据lableId 返回最新回答列表
     * @Date 20:32 2021/4/27
     * @param lableId :
     * @param pageable :
     * @return : org.springframework.data.domain.Page<com.master.qa.pojo.Problem>
     **/
    @Query(value =
                "SELECT tm.* FROM tb_problem tm , tb_pl tl \n" +
                "WHERE tm.id  = tl.problemid \n" +
                "AND tl.labelid = ? " +
                "ORDER BY tm.replytime DESC ",
            countQuery =
                "SELECT count(*) FROM tb_problem tm , tb_pl tl  +\n" +
                "WHERE tm.id  = tl.problemid  +\n" +
                "AND tl.labelid = ? ",
            nativeQuery = true)
    public Page<Problem> findNewListByLableId(String lableId, Pageable pageable);

    /**
     * @Author Zhao.J
     * @Description 根据lableId 返回最热回答列表
     * @Date 20:48 2021/4/27
     * @param lableId :
     * @param pageable :
     * @return : org.springframework.data.domain.Page<com.master.qa.pojo.Problem>
     **/
    @Query(value =
                "SELECT tm.* FROM tb_problem tm , tb_pl tl \n" +
                "WHERE tm.id  = tl.problemid \n" +
                "AND tl.labelid = ? \n" +
                "ORDER BY tm.reply DESC  ",
            countQuery =
                "SELECT count(*) FROM tb_problem tm , tb_pl tl \n" +
                "WHERE tm.id  = tl.problemid \n" +
                "AND tl.labelid = ? \n",
            nativeQuery = true)
    public Page<Problem> findHotListByLabelId(String lableId, Pageable pageable);

    /**
     * @Author Zhao.J
     * @Description  根据lableId 返回等待回答列表
     * @Date 20:48 2021/4/27
     * @param lableId :
     * @param pageable :
     * @return : org.springframework.data.domain.Page<com.master.qa.pojo.Problem>
     **/
    @Query(value =
                "SELECT tm.* FROM tb_problem tm , tb_pl tl \n" +
                "WHERE tm.id  = tl.problemid \n" +
                "AND tl.labelid = ? \n" +
                "AND tm.reply = 0 \n" +
                "ORDER BY tm.createtime DESC \n",
            countQuery =
                "SELECT count(*) FROM tb_problem tm , tb_pl tl \n" +
                "WHERE tm.id  = tl.problemid  \n" +
                "AND tl.labelid = ? \n" +
                "AND tm.reply = 0 \n" ,
            nativeQuery = true)
    public Page<Problem> findWaitListByLabelId(String lableId, Pageable pageable);

}