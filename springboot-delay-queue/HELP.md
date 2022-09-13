# Getting Started

### Reference Documentation
    https://zhuanlan.zhihu.com/p/119391934
    
### 队列初始化
    QueueConfig
    
### 生成者
    ApplicationTests.submit():
         rDelayedQueue.offer("action submit 2 in:"+new Date(), delaySeconds, TimeUnit.SECONDS);
        
### 消费者 
    UserQueueTask.take():
         String job = rBlockingQueue.take();

