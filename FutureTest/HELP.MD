### Thread

#### 继承 Thread
    public static void main(String[] args) throws Exception {
          
        Thread t= new Thread(){
            @Override
            public void run()  {
                System.out.println("async thread");
            }
        };
        t.start();//会调用本地方法生成线程
        //t.run();//普通方法调用,不会生成线程
        Thread.sleep(1000);
    }
#### 实现 Runnable

    public static void main(String[] args) throws Exception {
      
        Thread t= new Thread(new Runnable() {
            @Override
            public void run() throws Exception {
                System.out.println("async thread");
            }
        });
        t.start();//会调用本地方法生成线程
        //t.run();//普通方法调用,不会生成线程
        Thread.sleep(1000);
    }
### Callable+Future+Executor
    https://www.cnblogs.com/fingerboy/p/9948736.html
    特点：可以获取线程返回值,thread 不能获取返回值。

    实现Callable接口的call方法
    ==>submit到ExecutorService对象
    ==>submit返回的Future对象
    ==>Future对象调用get接收结果,get方法是堵塞的

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "async thread";
            }
        });
        Thread.sleep(1000);
        System.out.println(stringFuture.get());
    }
### FutureTask + Thread

    //MyTask 继承了FutureTask
    //MyCall 实现了Callable

    final MyTask task = new MyTask(new MyCall());
    final Thread t = new Thread(task);
    t.start();
    
    https://bbs.csdn.net/topics/240048674


### CompleteExecutor
    //多个线程返回的时候,先返回的先接收结果,堵塞
    completionService.take().get()
    疑问：  completionService.poll() 什么作用
    https://blog.csdn.net/linhaiyun_ytdx/article/details/89011008