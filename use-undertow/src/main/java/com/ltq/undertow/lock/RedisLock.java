package com.ltq.undertow.lock;

import java.util.Arrays;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//https://www.cnblogs.com/demingblog/p/9542124.html#redis-%E5%A6%82%E4%BD%95%E9%87%8A%E6%94%BE%E9%94%81
public class RedisLock implements Lock {

    public static int TTL = 30;
    public JedisPool pool;

    public RedisLock(String host, int port) {
        pool = new JedisPool(host, port);
    }

    @Override
    public Boolean lock(String key, String value) {
        // 连接redis
        // set key reqid ttl nx
        Jedis jedis = pool.getResource();
        while (true) {
            String result = jedis.set(key, value, "NX", "EX", TTL);
            if ("OK".equals(result)) {
                System.out.println(value + "  lock!");
                break;
            }
        }
        jedis.close();// 归还
        return true;
    }

    @Override
    public void unlock(String key, String value) {
        // 连接redis
        // get and del //get 之后判断是不是自己的锁，是则释放，成功
        Jedis jedis = pool.getResource();
        String lua = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";// lua脚本，用来释放分布式锁
        Object result = jedis.eval(lua, Arrays.asList(new String[] { key }), Arrays.asList(new String[] { value }));
        jedis.close();
        System.out.println(value + "  unlock!");
    }

}