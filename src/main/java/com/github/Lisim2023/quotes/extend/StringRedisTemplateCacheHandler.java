package com.github.Lisim2023.quotes.extend;

import com.github.Lisim2023.quotes.dict.DictCacheHandler;
import com.github.Lisim2023.quotes.quote.QuoteCacheHandler;

import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 *  使用RedisTemplate操作缓存
 * </p>
 *
 * @author Lisim
 */
public class StringRedisTemplateCacheHandler implements DictCacheHandler<String>, QuoteCacheHandler<String> {

    private RedisTemplate<String, String> redisTemplate;

    public StringRedisTemplateCacheHandler(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }




    @Override
    public void rename(String oldKey, String newKey){
        redisTemplate.rename(oldKey, newKey);
    }

    @Override
    public void cache(String key, String data) {
        redisTemplate.opsForValue().set(key, data);
    }

    @Override
    public void cache(Map<String, String> data) {
        redisTemplate.opsForValue().multiSet(data);
    }

    @Override
    public void cache(Map<String, String> data, long seconds) {
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.executePipelined((RedisCallback<String>) connection -> {
            data.forEach((key, value) -> {
                connection.set(redisSerializer.serialize(key), redisSerializer.serialize(value),
                        Expiration.seconds(seconds), RedisStringCommands.SetOption.UPSERT);
            });
            return null;
        }, redisSerializer);
    }

    @Override
    public Long remove(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key)) ? 1L : 0L;
    }

    @Override
    public Long remove(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public String load(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<String> load(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public void cache(String key, String data, long seconds) {
        redisTemplate.opsForValue().set(key, data, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Long expire(String key, long seconds) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, seconds, TimeUnit.SECONDS)) ? 1L : 0L;
    }

    @Override
    public String load(String key, long seconds) {
        return redisTemplate.opsForValue().getAndExpire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public List<String> load(Collection<String> keys, long seconds) {
        if (keys == null || keys.size() == 0){
            return null;
        }

        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        RedisCallback<String> redisCallback = connection -> {
            for (String key : keys) {
                connection.expire(redisSerializer.serialize(key), seconds);
            }
            return null;
        };

        redisTemplate.executePipelined(redisCallback);

        return redisTemplate.opsForValue().multiGet(keys);
    }





    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
