package com.github.Lisim2023.quotes.test.common;


import com.github.Lisim2023.quotes.extend.JedisProvider;

import com.github.Lisim2023.quotes.test.config.RedisPoolConfig;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;


@Component
public class JedisProviderImpl implements JedisProvider {

    @Override
    public Jedis requireForJedis() {
        return RedisPoolConfig.getJedis();
    }
}
