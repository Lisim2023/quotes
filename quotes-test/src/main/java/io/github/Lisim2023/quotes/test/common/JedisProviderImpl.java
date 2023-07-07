package io.github.Lisim2023.quotes.test.common;


import io.github.lisim2023.quotes.extend.JedisProvider;

import io.github.Lisim2023.quotes.test.config.RedisPoolConfig;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;


@Component
public class JedisProviderImpl implements JedisProvider {

    @Override
    public Jedis requireForJedis() {
        return RedisPoolConfig.getJedis();
    }
}
