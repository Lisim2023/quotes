package com.github.Lisim2023.quotes.test.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;


@Configuration
public class RedisPoolConfig {

    private static JedisPool pool;//jedis连接池

    @Value("${spring.redis.host:localhost}")
    private String redisIp;

    @Value("${spring.redis.port:6379}")
    private Integer redisPort;

    @Value("${spring.redis.lettuce.pool.max-active:50}")
    private Integer maxTotal; //最大连接数

    @Value("${spring.redis.lettuce.pool.max-idle:40}")
    private Integer maxIdle;//在jedispool中最大的idle状态(空闲的)的jedis实例的个数

    @Value("${spring.redis.lettuce.pool.min-idle:20}")
    private Integer minIdle;//在jedispool中最小的idle状态(空闲的)的jedis实例的个数

    @Value("${spring.redis.lettuce.pool.max-wait:1000 * 3}")
    private Integer timeOut;

    private static final Boolean testOnBorrow = true;//在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
    private static final Boolean testOnReturn = true;//在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。

    @PostConstruct
    public void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);

        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setBlockWhenExhausted(true);

        pool = new JedisPool(jedisPoolConfig, redisIp, redisPort, timeOut);
    }


    public static Jedis getJedis(){
        return pool.getResource();
    }


    public static void close(Jedis jedis){
        try {
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            System.out.println("return redis resource exception" + e);
        }
    }

}
