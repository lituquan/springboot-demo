# Getting Started

### learn reference
    [安装和配合springboot](https://www.cnblogs.com/hanease/p/15931768.html)
    [动态刷新配置](http://www.javashuo.com/article/p-zhzewvam-mk.html)
    (1)安装数据库和配置库表
    (2)安装apollo(需要JVM环境)
        ./demo.sh start
        默认会启动一个eureka和一个apollo服务
    (3)启动本项目
            
### 使用配置

#### 1.连接apollo,读取配置
        @Value("${xxx}")
#### 2.对象填充
    AppConfig 
    指定前缀：@ConfigurationProperties(prefix = "user")
    加入配置：@Configuration
    属性name、id从apollo加载
    
#### 3.数组填充
    AppConfig
    指定前缀："user"
    列表属性：list
    索引+字段
        user.list[0].name = hello
        user.list[0].id = 1
#### 4.map填充
    AppConfig
    指定前缀："user"
    列表属性：maps
    索引+字段        
        user.maps.first = 张飞
        user.maps.second = 李四
        user.collects = {1:张三,2:李四}
          
### 自动更新配置

#### 1.@Value("${xxx}")

#### 2.RefreshScope + @ApolloConfigChangeListener
    org.springframework.cloud.context.config.annotation.RefreshScope
    监听器:
        ApolloConfigChangeListener,可以指定命名空间和前缀
    刷新属性:
        refreshScope.refresh("refreshConfig");
        
#### 3.EnvironmentChangeEvent
    监听器:
        ApolloConfigChangeListener
    更新Key:
        applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
    
### 集群                
                