

### 结绳记事

    数据：{1,2,5,7,11}
        一个数1byte,需要5byte

    绳子：
       0 0 1 1 0 0 1 0 
         1 2     5   7
    压缩原理    
        有值非0的下标就是数：下标--数,1 byte就可以表达8个数
        int 32位是32个数,所以 int[] 压缩率是1/32

### bitmap
    int[] 数组，元素添加add，元素存在判断exist        
    数据索引：
        data[index(n)]
        int[] -->index: (n>>5),offset: n&(2^5-1)=n 

### BloomFilter
    bitmap,k个hash 函数

    元素k次hash后添加到bitmap

    元素存在判断exist:  有一次hash 是 0就返回false, k次hash都返回true

### 分布式BloomFilter
    把bitmap数据放在redis    
        https://www.jianshu.com/p/cae51ad2486c
        
        (1)接口化--Bitmap 接口
        (2)实现
                本地bitmap
                调用redis bitmap
