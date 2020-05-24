#### 外部依赖
    mysql
    redis
#### 1.新建项目
    idea 或者 springsts 新建spring initializr
    demo 项目依赖：
        web
        test
        devtool:idea 设置https://www.jianshu.com/p/8cc2c10bd6ba
        actuator
        thymeleaf
     
#### 2.maven
    maven 官网下载maven 3.5
    为了加速依赖下载：配置aliyun镜像
    <mirror>
      <id>nexus-aliyun</id>
      <mirrorOf>central</mirrorOf>
      <name>Nexus aliyun</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
    </mirror>  

#### 3.添加控制器
    @Controller           
    @RestController
    
    @RequestMapping(path = "/hello")
    @GetMapping("/greet") 
##### (1)接口
##### (2)页面和渲染模板
    resources/templates
    用model传递数据
    
##### (3)首页和静态文件
    resources/static

    
#### 4.启动和扫描配置 
    启动 @SpringBootApplication
    扫描控制器包 @ComponentScan(basePackages = {"com.example.demo.controller"})
    
#### 5.测试接口
    (1)mockmvc
    (2)TestRestTemplate
    
#### 6.监控应用
            
#### 7.runner
    CommandLineRunner   
    SpringRunner  
        
#### 8.redis        
    (1)stater:
    spring-boot-starter-data-redis
    (2)config
    @Configuration
    @EnableCaching
    CachingConfigurerSupport
    KeyGenerator
    
    https://blog.csdn.net/qq_32771571/article/details/80040861
    https://www.jianshu.com/p/b9154316227e
    https://www.jianshu.com/p/9de4d4932e07
    （1）mapper缓存
        @Select("select * from user where id = #{id}")
        @Cacheable(cacheNames = "demoCache", key = "#id")
        public User getUserById(Integer id);

    （2）方法调用redis
     @Override
    public User getUser(int userid) {
        User order = (User)redisTemplate.opsForValue().get(userid+"");
        if(order != null){
            return order;
        }

        order = dao.queryUserById(userid);
        if(order != null){
            redisTemplate.opsForValue().set(userid+"", order);
        }
        return order;
    }
    （3）方法上注解缓存    
    @Cacheable(value = "userStatistics", key = "methodName")
    @Override
    public User getUserById(int id) {
        return dao.queryUserById(id);
    }
### 9.mybatis
    (1)stater:
    mybatis-spring-boot-starter
    (2)
    https://www.jianshu.com/p/2898d50dfb2e
    (3)
    mybatis-generator:generate
    (4)组合redis     
### swagger
    参考博客：
        https://www.cnblogs.com/niunafei/p/11120274.html
    访问地址:
        http://localhost:8080/swagger-ui.html
        可以查看接口和接口测试

    配置:
    
    (1)pom.xml
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
    (2)配置类
        SwaggerConfig.java 
### dubbo
	https://www.cnblogs.com/baijinqiang/p/10848259.html
	服务发布
	接口+服务实现+spring装配+服务暴露+服务注册zk

	服务消费
	接口+zk+服务代理对象生成+服务调用
