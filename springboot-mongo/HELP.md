
### 配置
    spring.data.mongodb.uri=mongodb://root:root@127.0.0.1:27017/admin
    
### 定义model
    @Document(value = "user")
    User    

### 定义数据处理类
    interfece UserRepository 
    extends MongoRepository<User,Integer>
    
### 测试
    SpringbootMongoApplicationTests    