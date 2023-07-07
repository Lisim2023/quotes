package io.github.lisim2023.quotes.extend;

import redis.clients.jedis.Jedis;

/**
 * <p>
 *  使用Jedis直连操作缓存(不关闭连接)
 * </p>
 *
 * @author Lisim
 */
public class StringJedisCacheHandler extends AbstractJedisStringCacheHandler {

    private Jedis jedis;

    public StringJedisCacheHandler(Jedis jedis) {
        this.jedis = jedis;
    }




    @Override
    protected Jedis requireForJedis() {
        return this.jedis;
    }

    @Override
    protected void closeJedis(Jedis jedis) {
    }





    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

}
