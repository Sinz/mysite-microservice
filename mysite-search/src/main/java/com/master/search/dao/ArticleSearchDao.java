package com.master.search.dao;

import com.master.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Classname ArticleSearchDao
 * @Description 文章访问接口
 * @Date 2021/5/6 16:18
 * @Created by Zhao.J
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
