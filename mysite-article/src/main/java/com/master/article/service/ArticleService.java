package com.master.article.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.master.article.dao.ArticleDao;
import com.master.article.pojo.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * 服务层
 * 
 * @author Administrator
 *
 * TODO 还需要增加文章评论功能
 *
 *
 * Redis 基本操作：
 * stringRedisTemplate.opsForValue().set("test", "100",60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
 * stringRedisTemplate.opsForValue().get("test")//根据key获取缓存中的val
 * stringRedisTemplate.boundValueOps("test").increment(-1);//val做-1操作
 * stringRedisTemplate.boundValueOps("test").increment(1);//val +1
 * stringRedisTemplate.getExpire("test")//根据key获取过期时间
 * stringRedisTemplate.getExpire("test",TimeUnit.SECONDS)//根据key获取过期时间并换算成指定单位
 * stringRedisTemplate.delete("test");//根据key删除缓存
 * stringRedisTemplate.hasKey("546545");//检查key是否存在，返回boolean值
 * stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);//设置过期时间
 * stringRedisTemplate.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合
 * stringRedisTemplate.opsForSet().isMember("red_123", "1")//根据key查看集合中是否存在指定数据
 * stringRedisTemplate.opsForSet().members("red_123");//根据key获取set集合
 *
 */
@Slf4j
@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @Author Zhao.J
	 * @Description 点赞
	 * @Date 23:26 2021/4/27
	 * @param id :
	 * @return : int
	 **/
	public void addThumbup(String id){
		articleDao.addThumbup(id);
	}

	/**
	 * @Author Zhao.J
	 * @Description 审核
	 * @Date 23:26 2021/4/27
	 * @param id :
	 * @return : void
	 **/
	public void updateState(String id){
		articleDao.updateState(id);
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Article> findAll() {
		return articleDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Article> findSearch(Map whereMap, int page, int size) {
		Specification<Article> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return articleDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Article> findSearch(Map whereMap) {
		Specification<Article> specification = createSpecification(whereMap);
		return articleDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Article findById(String id) {

		// 先从缓存中查找
		Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
		if (article == null){
			// 如果不存在，在数据库中查找
			article = articleDao.findById(id).get();
			// 存入缓存中
			redisTemplate.opsForValue().set("article_" + id,article);
		}else {

			log.info("从Redis中获取。。。。");
		}
		return article;
	}

	/**
	 * 增加
	 * @param article
	 */
	public void add(Article article) {
		redisTemplate.opsForValue().set("article_" + article.getId(),article);
		article.setId( idWorker.nextId()+"" );
		articleDao.save(article);
	}

	/**
	 * 修改
	 * @param article
	 */
	public void update(Article article) {
		redisTemplate.delete("article_" + article.getId());
		articleDao.save(article);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		redisTemplate.delete("article_" + id);
		articleDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Article> createSpecification(Map searchMap) {

		return new Specification<Article>() {

			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 专栏ID
                if (searchMap.get("columnid")!=null && !"".equals(searchMap.get("columnid"))) {
                	predicateList.add(cb.like(root.get("columnid").as(String.class), "%"+(String)searchMap.get("columnid")+"%"));
                }
                // 用户ID
                if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                	predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
                }
                // 标题
                if (searchMap.get("title")!=null && !"".equals(searchMap.get("title"))) {
                	predicateList.add(cb.like(root.get("title").as(String.class), "%"+(String)searchMap.get("title")+"%"));
                }
                // 文章正文
                if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
                	predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
                }
                // 文章封面
                if (searchMap.get("image")!=null && !"".equals(searchMap.get("image"))) {
                	predicateList.add(cb.like(root.get("image").as(String.class), "%"+(String)searchMap.get("image")+"%"));
                }
                // 是否公开
                if (searchMap.get("ispublic")!=null && !"".equals(searchMap.get("ispublic"))) {
                	predicateList.add(cb.like(root.get("ispublic").as(String.class), "%"+(String)searchMap.get("ispublic")+"%"));
                }
                // 是否置顶
                if (searchMap.get("istop")!=null && !"".equals(searchMap.get("istop"))) {
                	predicateList.add(cb.like(root.get("istop").as(String.class), "%"+(String)searchMap.get("istop")+"%"));
                }
                // 审核状态
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                // 所属频道
                if (searchMap.get("channelid")!=null && !"".equals(searchMap.get("channelid"))) {
                	predicateList.add(cb.like(root.get("channelid").as(String.class), "%"+(String)searchMap.get("channelid")+"%"));
                }
                // URL
                if (searchMap.get("url")!=null && !"".equals(searchMap.get("url"))) {
                	predicateList.add(cb.like(root.get("url").as(String.class), "%"+(String)searchMap.get("url")+"%"));
                }
                // 类型
                if (searchMap.get("type")!=null && !"".equals(searchMap.get("type"))) {
                	predicateList.add(cb.like(root.get("type").as(String.class), "%"+(String)searchMap.get("type")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
