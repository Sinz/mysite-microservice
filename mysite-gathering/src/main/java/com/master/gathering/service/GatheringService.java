package com.master.gathering.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import common.util.IdWorker;

import com.master.gathering.dao.GatheringDao;
import com.master.gathering.pojo.Gathering;

/**
 * 服务层
 * 
 * @author Administrator
 *#配置初始化大小/最小/最大
 * initial-size: 5
 * min-idle: 5
 * max-active: 20
 * # 配置获取连接等待超时的时间
 * max-wait: 60000
 * # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
 * time-between-eviction-runs-millis: 60000
 * # 配置一个连接在池中最小生存的时间，单位是毫秒
 * min-evictable-idle-time-millis: 300000
 * validation-query: SELECT 1 FROM DUAL
 * test-while-idle: true
 * test-on-borrow: false
 * test-on-return: false
 * # 打开PSCache，并且指定每个连接上PSCache的大小
 * pool-prepared-statements: true
 * #   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
 * max-pool-prepared-statement-per-connection-size: 20
 * #监控统计拦截的filters
 * filters: stat,wall
 * use-global-data-source-stat: true
 * # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
 * connect-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
 * # 配置监控服务器
 * stat-view-servlet:
 *   #是否允许开启监控
 *   enabled: true
 *   #登录用户名
 *   login-username: admin
 *   #登录密码
 *   login-password: 123456
 *   #是否允许重置监控数据
 *   reset-enable: false
 *   #druid访问路径
 *   url-pattern: /druid/*
 */
@Service
public class GatheringService {

	@Autowired
	private GatheringDao gatheringDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Gathering> findAll() {
		return gatheringDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Gathering> findSearch(Map whereMap, int page, int size) {
		Specification<Gathering> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return gatheringDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Gathering> findSearch(Map whereMap) {
		Specification<Gathering> specification = createSpecification(whereMap);
		return gatheringDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	@Cacheable(value = "gathering" , key = "#id")
	public Gathering findById(String id) {
		return gatheringDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param gathering
	 */
	@Cacheable(value = "gathering" , key = "#gathering.id")
	public void add(Gathering gathering) {
		gathering.setId( idWorker.nextId()+"" );
		gatheringDao.save(gathering);
	}

	/**
	 * 修改
	 * @param gathering
	 */
	@CacheEvict(value = "gathering" , key = "#gathering.id")
	public void update(Gathering gathering) {
		gatheringDao.save(gathering);
	}

	/**
	 * 删除
	 * @param id
	 */
	@CacheEvict(value = "gathering" , key = "#id")
	public void deleteById(String id) {
		gatheringDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Gathering> createSpecification(Map searchMap) {

		return new Specification<Gathering>() {

			@Override
			public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 编号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 活动名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 大会简介
                if (searchMap.get("summary")!=null && !"".equals(searchMap.get("summary"))) {
                	predicateList.add(cb.like(root.get("summary").as(String.class), "%"+(String)searchMap.get("summary")+"%"));
                }
                // 详细说明
                if (searchMap.get("detail")!=null && !"".equals(searchMap.get("detail"))) {
                	predicateList.add(cb.like(root.get("detail").as(String.class), "%"+(String)searchMap.get("detail")+"%"));
                }
                // 主办方
                if (searchMap.get("sponsor")!=null && !"".equals(searchMap.get("sponsor"))) {
                	predicateList.add(cb.like(root.get("sponsor").as(String.class), "%"+(String)searchMap.get("sponsor")+"%"));
                }
                // 活动图片
                if (searchMap.get("image")!=null && !"".equals(searchMap.get("image"))) {
                	predicateList.add(cb.like(root.get("image").as(String.class), "%"+(String)searchMap.get("image")+"%"));
                }
                // 举办地点
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                	predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
                }
                // 是否可见
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                // 城市
                if (searchMap.get("city")!=null && !"".equals(searchMap.get("city"))) {
                	predicateList.add(cb.like(root.get("city").as(String.class), "%"+(String)searchMap.get("city")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
