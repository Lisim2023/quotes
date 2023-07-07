package io.github.lisim2023.quotes.extend;

import redis.clients.jedis.Jedis;

/**
 * <p>
 *  通过此接口从项目获取Jedis实例
 * </p>
 *
 * @author Lisim
 */
public interface JedisProvider {

    /**
     * 获取Jedis实例
     * @return
     */
    Jedis requireForJedis();
}
