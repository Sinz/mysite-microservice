package com.master.search.service;

import com.master.search.dao.ArticleSearchDao;
import com.master.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Classname ArticleService
 * @Description TODO
 * @Date 2021/5/6 16:20
 * @Created by Zhao.J
 */

@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

    public void save(Article article){
        articleSearchDao.save(article);
    }


    public Page<Article> findByKey(String key, int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);
        return articleSearchDao.findByTitleOrContentLike(key, key, pageable);
    }
}
