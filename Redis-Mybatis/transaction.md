### 1.spring IOC
    反射创建对象
        工厂模式
        单例模式
        对象池
    对象注入

    https://www.cnblogs.com/yoci/p/10642553.html
    bean 生命周期
        创建对象--设置属性--aware:setName、setBeanFactory、setApplicationContext-->
        BeanPostProcessor:Before init  -->initializingBean:afterPropertiesSet--> 
        init-method -->BeanPostProcessor:after init -->
        use -->DisableBean:destory-->destory-method

    bean管理：  
        1.读取配置
        2.实例化注册bean
        3.bean放入对象缓存池
        4.使用对象

    循环依赖：
        https://blog.csdn.net/Apeopl/article/details/90146337
        singletonObjects：第一级缓存，里面放置的是实例化好的单例对象；
        earlySingletonObjects：第二级缓存，里面存放的是提前曝光的单例对象；
        singletonFactories：第三级缓存，里面存放的是要被实例化的对象的对象工厂。

        所以当一个Bean调用构造函数进行实例化后，即使属性还未填充，就可以通过三级缓存向外暴露依赖的引用值（所以循环依赖问题的解决也是基于Java的引用传递），这也说明了另外一点，基于构造函数的注入，如果有循环依赖，Spring是不能够解决的。

### 2.spring AOP
    https://blog.csdn.net/zhangsweet1991/article/details/83859026
    
    代理模式
        生成字节码：proxy,asm
        
        https://www.cnblogs.com/ctgulong/p/5011614.html
        proxy代理：接口生成实现类，基于实现        
        cglib代理：生成实现类、生成子类，基于继承,所以final 方法不能代理。

    Aspect
        切入点
            --class 判断、方法判断，连接点的子集
        advice
            --执行额外代码和调用方法
            --Before、After、AfterReturning、Around、Throws
        代理目标
        切面：
            切入点+advice


### 3.事务隔离级别：
        读未提交        
            --脏读：A setV,B getV,A 回滚 ,B 提交,B持有的值V不应该存在。
            --处理方法：读提交

        读已提交
            不可重读
            --不可重读：
                对于统一记录,A getv=1000,B getv=1000;B 扣800,提交 ;A 扣除500,出现负数或者不够扣,但是A 之前看到的是1000,A 认为够的。
            幻读
            --幻读:   
                对于多个记录，A getVLog=10条,B getVLog=10条 ;A 扣500,提交 ;B getVLog=11条,多了一条。      
        可重复读
                实现原理：
                    https://blog.csdn.net/DILIGENT203/article/details/100751755
                    快照读：
                        undo log + 数据行获取到事务开启时DB_ROLL_PTR 的原始数据的过程就是“快照读”
                    当前读：
                        读取DB_TRX_ID 事务版本数据

                    undo log+DB_TRX_ID ,MVCC,多版本和可见性控制     
                    select:
                        快照读, 回溯undo log 找到事务开始前最大DB_ROLL_PTR 对应的行数据。
                    insert\update:
                        当前读===to do===
        串行化

### 4.spring 事务    
    事务传播Propagation：
        REQUIRED
             需要,[有则加入，否则创建]
        SUPPORTS 
             支持,[有则加入，否则无事务]
        MANDATORY
             托管,[有则加入，无则异常]
        REQUIRED_NEW
             新建,[总是新建，有则挂起旧事务]
        NOT_SUPPORTED        
             不支持,[有则挂起，否则无事务]
        NEVER        
             不支持,[有则异常，否则无事务]
        NESTED
             [有则嵌套，无则新建]        

    统一db异常
        --异常转换
    ThreadLocal
        --事务同步管理器    
                         
### 5.springMVC
                       --> DataBind  参数绑定
    DispatcherServlet  --> HandlerMapping  映射规则
                       --> HandlerAdapter -->Handler,Controller   相应请求
                       --> ViewResolve --> view 页面渲染

    异常处理器
        @ExceptionHandler --方法级别
        @ControllerAdvice --全局aop拦截所有控制器
        HandlerExceptionResolver                       
