### rocketmq
启动：
命名服务 
    start mqnamesrv.cmd

broker 
    start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true

Ui:
    https://github.com/apache/rocketmq-externals.git
    编译可能会报错：pom.xml 里面的maven-checkstyle-plugin 插件去掉。
    端口修改成8081,避免跟应用冲突。

### pom.xml
    <dependency>
        <groupId>org.apache.rocketmq</groupId>
        <artifactId>rocketmq-client</artifactId>
        <version>${rocketmq.version}</version>
    </dependency>   
    注意：版本和使用的rocketmq服务版本要一致,否则在自动创建topic到时候会失败。

### ui和页面
    http://localhost:8081/#/consumer ==>Ui地址
    http://localhost:8080/text/rocketmq ==>调用生产者发送消息,在监听的消费者会消费并打印消息


