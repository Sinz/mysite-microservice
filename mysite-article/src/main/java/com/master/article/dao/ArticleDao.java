package com.master.article.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.master.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * 数据访问接口
 * @author Administrator
 *
 *  为什么jpa 进行update 不会自动更新啊？？？？
 *
 *  * 坑爹呀！！！ 使用@Apiparam 注释的时候，因为导致@PathVarial 没有将值传进JPA 生成的sql语句！！！
 *  *
 *  * 正确： binding parameter [1] as [VARCHAR] - [1]
 *  * 错误： binding parameter [1] as [VARCHAR] - {[1]}
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{


    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup = thumbup+1 WHERE id = ?1 ", nativeQuery = true)
    public void addThumbup(String id);


    @Modifying
    @Query(value = "UPDATE tb_article SET state = 1 WHERE id = ?1 ", nativeQuery = true)
    public void updateState(String id);
}
