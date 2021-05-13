# mysite-请关注项目更新及时拉取最新版本

## 项目介绍

`mysite-microcervice`是一套微服务系统，采用了 Spring Boot 2、JPA、Docker、Elasticsearch等核心技术，同时提供了基于Vue的管理后台方便快速搭建系统。 

## 系统架构图

## 组织结构

``` lua
mysite
├── mysite-common -- 工具类及通用代码模块
├── mysite-article -- 文章模块
├── mysite-base -- 标签页
├── mysite-gatering -- 活动模块
├── mysite-qa -- 问答模块
├── mysite-user -- 招聘模块
```

## 技术选型

### 后端技术

| 技术                 | 说明                | 官网                                                         |
| -------------------- | ------------------- | ------------------------------------------------------------ |
| Spring Boot          | 容器+MVC框架        | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Spring Security      | 认证和授权框架      | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security) |
| MyBatis              | ORM框架             | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| MyBatisGenerator     | 数据层代码生成      | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html) |
| PageHelper           | MyBatis物理分页插件 | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper) |
| Swagger-UI           | 文档生产工具        | [https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) |
| Elasticsearch        | 搜索引擎            | [https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch) |
| RabbitMq             | 消息队列            | [https://www.rabbitmq.com/](https://www.rabbitmq.com/)       |
| Redis                | 分布式缓存          | [https://redis.io/](https://redis.io/)                       |
| MongoDb              | NoSql数据库         | [https://www.mongodb.com/](https://www.mongodb.com/)         |
| Docker               | 应用容器引擎        | [https://www.docker.com/](https://www.docker.com/)           |
| Druid                | 数据库连接池        | [https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
| OSS                  | 对象存储            | [https://github.com/aliyun/aliyun-oss-java-sdk](https://github.com/aliyun/aliyun-oss-java-sdk) |
| JWT                  | JWT登录支持         | [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt) |
| LogStash             | 日志收集            | [https://github.com/logstash/logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder) |
| Lombok               | 简化对象封装工具    | [https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |
| Seata                | 全局事务管理框架    | [https://github.com/seata/seata](https://github.com/seata/seata) |
| Portainer            | 可视化Docker容器管理| [https://github.com/portainer/portainer](https://github.com/portainer/portainer) |
| Spring JPA           | JPA              |

### 前端技术

| 技术       | 说明                  | 官网                                                         |
| ---------- | --------------------- | ------------------------------------------------------------ |
| Vue        | 前端框架              | [https://vuejs.org/](https://vuejs.org/)                     |
| Vue-router | 路由框架              | [https://router.vuejs.org/](https://router.vuejs.org/)       |
| Vuex       | 全局状态管理框架      | [https://vuex.vuejs.org/](https://vuex.vuejs.org/)           |
| Element    | 前端UI框架            | [https://element.eleme.io/](https://element.eleme.io/)       |
| Axios      | 前端HTTP框架          | [https://github.com/axios/axios](https://github.com/axios/axios) |
| v-charts   | 基于Echarts的图表框架 | [https://v-charts.js.org/](https://v-charts.js.org/)         |


## 环境搭建

### 开发环境

工具 | 版本号 | 下载
----|----|----
JDK | 1.8 | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Mysql | 5.7 | https://www.mysql.com/
Redis | 3.2 | https://redis.io/download
Elasticsearch | 6.2.2 | https://www.elastic.co/downloads
MongoDb | 3.2 | https://www.mongodb.com/download-center
RabbitMq | 3.7.14 | http://www.rabbitmq.com/download.html
nginx | 1.10 | http://nginx.org/en/download.html


## 表业务说明

## 遇坑集锦

* 1 为什么jpa 进行update 不会自动更新啊？？？？

```
    * 坑爹呀！！！ 使用@Apiparam 注释的时候，因为导致@PathVarial 没有将值传进JPA 生成的sql语句！！！将行内的@Apiparam移到方法上就成功了。
    * 正确： binding parameter [1] as [VARCHAR] - [1]
    * 错误： binding parameter [1] as [VARCHAR] - {[1]}
```


* 2 Redis 基本操作：
```

stringRedisTemplate.opsForValue().set("test", "100",60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
stringRedisTemplate.opsForValue().get("test")//根据key获取缓存中的val
stringRedisTemplate.boundValueOps("test").increment(-1);//val做-1操作
stringRedisTemplate.boundValueOps("test").increment(1);//val +1
stringRedisTemplate.getExpire("test")//根据key获取过期时间
stringRedisTemplate.getExpire("test",TimeUnit.SECONDS)//根据key获取过期时间并换算成指定单位
stringRedisTemplate.delete("test");//根据key删除缓存
stringRedisTemplate.hasKey("546545");//检查key是否存在，返回boolean值
stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);//设置过期时间
stringRedisTemplate.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合
stringRedisTemplate.opsForSet().isMember("red_123", "1")//根据key查看集合中是否存在指定数据
stringRedisTemplate.opsForSet().members("red_123");//根据key获取set集合

```

* 3 整合Druid 进行sql监控：

```yaml
#配置初始化大小/最小/最大
initial-size: 5
min-idle: 5
max-active: 20
# 配置获取连接等待超时的时间
max-wait: 60000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
time-between-eviction-runs-millis: 60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
min-evictable-idle-time-millis: 300000
validation-query: SELECT 1 FROM DUAL
test-while-idle: true
test-on-borrow: false
test-on-return: false
# 打开PSCache，并且指定每个连接上PSCache的大小
pool-prepared-statements: true
#   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
max-pool-prepared-statement-per-connection-size: 20
#监控统计拦截的filters
filters: stat,wall
use-global-data-source-stat: true
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
connect-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
# 配置监控服务器
stat-view-servlet:
  #是否允许开启监控
  enabled: true
  #登录用户名
  login-username: admin
  #登录密码
  login-password: 123456
  #是否允许重置监控数据
  reset-enable: false
  #druid访问路径
  url-pattern: /druid/*
```
    
* 4 JPA 查询过程trace：
```yaml
spring: 
  jpa:
    database: mysql
    show-sql: true
# JPA log 控制台输出
#logging:
#  level:
#    org.springframework.security:
#      - debug
#      - info
#    org.springframework.web: error
#    org.hibernate.SQL: debug
#    org.hibernate.engine.QueryParameters: debug
#    org.hibernate.engine.query.HQLQueryPlan: debug
#    org.hibernate.type.descriptor.sql.BasicBinder: trace
```
* 5 快速生成验证码的方法：        
  String code = String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));
  
* 6 springboot security 以及拦截器 集成 druid和knife4j时，需要注意添加拦截及不拦截路径，添加静态资源访问；
  
  ```
  // security
    @Override protected void configure(HttpSecurity http) throws Exception {

        //匹配的页面，符合限制才可访问
        http
                .authorizeRequests()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/doc.html","/webjars/**","/img.icons/**","/swagger-resources/**","/v2/api-docs").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable(); }
  ```
  ```
     @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        List swaggerList = new ArrayList();

        // 注册拦截器
        // /doc.html /webjars/** /swagger-resources/** /v2/api-docs /img.icons/** /druid/**
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(new ArrayList<>(noAuthUrlProperties.getShouldSkipUrls())); 
               
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //配置拦截器访问静态资源
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
  ```
  

* 7 docker镜像 
```
（1）私有仓库搭建

  1. 拉取私有仓库镜像：docker pull resigtry
  
  2. 启动私有仓库容器：docker run -di --name=registry -p 5000:5000 registry
  
  3. 打开浏览器查看：http://192.168.184.135:5000/v2/_catalog
  
  4. 修改daemon.json：vi /etc/docker/daemon.json
    添加{"insecure-registries":["192.168.184.135:5000"]}
  
  5. 重启docker服务：systemctl restart docker
  
 （2）镜像上传至私有仓库
 
  1. 标记此镜像为私有仓库的镜像：docker tag jdk1.8 192.168.184.135:5000/jdk1.8
  
  2. 再次启动私有服务容器：docker start registry
  
  3. 上传标记的镜像：docker push 192.168.184.135:5000/jdk1.8
  
  （3）docker maven 插件
  
  1. 修改宿主机的docker配置，让其可以远程访问： vi /lib/systemd/system/docker.service
  
  2. 其中ExecStart=后添加配置 -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
  
  3. 刷新配置，重启服务
  systemctl daemon-reload 
  systemctl restart docker 
  docker start registry
  
  4. docker maven plugin 设置如下， 然后直接maven clean , maven package就可以自动生成Dockerfile,并进行打包上传
  clean package -Dmaven.test.skip=true -DarchetypeCatalog=internal docker:build -DpushImage(不知道为什么，每次都要重新下一边依赖，不过上传到jenkins不影响)
  浏览器访问 http://192.168.184.135:5000/v2/_catalog ; 
  
  <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <!-- Docker maven plugin -->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.0.0</version>
                <executions>
                    <execution>
                        <id>build-image</id>
                        <!--用户只需执行mvn package ，就会自动执行mvn docker:build-->
                        <phase>package</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                </executions>

                <configuration>
                    <imageName>192.168.89.137:5000/${project.artifactId}:${project.version}</imageName>
                    <baseImage>jdk1.8</baseImage>
                    <entryPoint>["java","-jar","/${project.build.finalName}.jar"]</entryPoint>
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                    <dockerHost>http://192.168.89.137:2375</dockerHost>
                </configuration>
            </plugin>
            <!-- Docker maven plugin -->
        </plugins>
    </build>
    
  5. 宿主机查看镜像： docker images
  
  6. 启动容器： docker run -di --name=eureka -p 6868:6868 192.168.89.137:5000/mysite-eureka:1.0-SNAPSHOT
  
```

* 7. maven install 时，common包提示不存在， 
  对于需要被依赖的包，需要在pom文件中添加如下plugin
```
   
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <classifier>execute</classifier>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
