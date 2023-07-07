package com.github.Lisim2023.quotes.extend;

import redis.clients.jedis.Jedis;

/**
 * <p>
 *  通过Jedis池获取Jedis实例操作缓存
 * </p>
 *
 * @author Lisim
 */
public class StringJedisPoolCacheHandler extends AbstractJedisStringCacheHandler {

    private JedisProvider jedisProvider;

    public StringJedisPoolCacheHandler(JedisProvider jedisProvider) {
        this.jedisProvider = jedisProvider;
    }





    @Override
    protected Jedis requireForJedis() {
        return jedisProvider.requireForJedis();
    }

    @Override
    protected void closeJedis(Jedis jedis) {
        if (jedis != null){
            jedis.close();
        }
    }





    public JedisProvider getJedisProvider() {
        return jedisProvider;
    }

    public void setJedisProvider(JedisProvider jedisProvider) {
        this.jedisProvider = jedisProvider;
    }
}
