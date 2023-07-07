package com.github.Lisim2023.quotes.extend;


import com.github.Lisim2023.quotes.dict.DictCacheHandler;
import com.github.Lisim2023.quotes.quote.QuoteCacheHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.params.GetExParams;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  Jedis缓存操作
 * </p>
 *
 * @author Lisim
 */
public abstract class AbstractJedisStringCacheHandler implements DictCacheHandler<String>, QuoteCacheHandler<String> {

    abstract protected Jedis requireForJedis();

    abstract protected void closeJedis(Jedis jedis);



    @Override
    public void rename(String oldKey, String newKey){
        if (oldKey != null && newKey != null){
            Jedis jedis = requireForJedis();
            jedis.rename(oldKey, newKey);
            closeJedis(jedis);
        }
    }

    @Override
    public void cache(String key, String data) {
        if (key != null){
            Jedis jedis = requireForJedis();
            jedis.set(key, data);
            closeJedis(jedis);
        }
    }

    @Override
    public void cache(Map<String, String> data) {
        if (data != null){
            Jedis jedis = requireForJedis();
            Pipeline pipeline = jedis.pipelined();
            data.forEach(pipeline::set);
            pipeline.sync();
            pipeline.close();
            closeJedis(jedis);
        }

//        Jedis jedis = requireForJedis();
//        if (data != null) {
//            int len = data.size();
//            if (len > 0) {
//                String[] keys = data.keySet().toArray(new String[0]);
//                String[] strings = new String[len * 2];
//
//                for (int i = 0; i < len; i++) {
//                    strings[2 * i] = keys[i];
//                    strings[2 * i + 1] = data.get(keys[i]);
//                }
//                jedis.mset(strings);
//            }
//        }
//        closeJedis(jedis);
    }

    @Override
    public Long remove(String key) {
        if (key == null){
            return null;
        }
        Jedis jedis = requireForJedis();
        try {
            return jedis.del(key);
        }finally {
            closeJedis(jedis);
        }
    }

    @Override
    public Long remove(Collection<String> keys) {
        if (keys == null || keys.size() == 0){
            return 0L;
        }
        Jedis jedis = requireForJedis();
        try {
            return jedis.del(keys.toArray(new String[0]));
        }finally {
            closeJedis(jedis);
        }
    }

    @Override
    public String load(String key) {
        if (key == null){
            return null;
        }
        Jedis jedis = requireForJedis();
        try {
            return jedis.get(key);
        }finally {
            closeJedis(jedis);
        }
    }

    @Override
    public List<String> load(Collection<String> keys) {
        if (keys == null || keys.size() == 0){
            return null;
        }
        Jedis jedis = requireForJedis();
        try {
            return jedis.mget(keys.toArray(new String[0]));
        }finally {
            closeJedis(jedis);
        }
    }

    @Override
    public Set<String> keys(String pattern) {
        if (pattern == null){
            return null;
        }
        Jedis jedis = requireForJedis();
        try {
            return jedis.keys(pattern);
        }finally {
            closeJedis(jedis);
        }
    }





    @Override
    public void cache(String key, String data, long seconds) {
        if (key != null){
            Jedis jedis = requireForJedis();
            jedis.setex(key, seconds, data);
            closeJedis(jedis);
        }
    }

    @Override
    public void cache(Map<String, String> data, long seconds) {
        if (data != null){
            Jedis jedis = requireForJedis();
            Pipeline pipeline = jedis.pipelined();
            data.forEach((key, value) -> pipeline.setex(key, seconds, value));
            pipeline.sync();
            pipeline.close();
            closeJedis(jedis);
        }
    }

    @Override
    public String load(String key, long seconds) {
        if (key == null){
            return null;
        }
        Jedis jedis = requireForJedis();
        try {
            return jedis.getEx(key, new GetExParams().ex(seconds));
        }finally {
            closeJedis(jedis);
        }
    }

    @Override
    public List<String> load(Collection<String> keys, long seconds) {
        if (keys == null || keys.size() == 0) {
            return null;
        }
        Jedis jedis = requireForJedis();
        Pipeline pipelined = jedis.pipelined();
        for (String key : keys) {
            pipelined.expire(key, seconds);
        }
        pipelined.sync();
        pipelined.close();

        List<String> strings = jedis.mget(keys.toArray(new String[0]));
        closeJedis(jedis);
        return strings;
    }

    @Override
    public Long expire(String key, long seconds) {
        if (key == null){
            return null;
        }
        Jedis jedis = requireForJedis();
        try {
            return jedis.expire(key, seconds);
        }finally {
            closeJedis(jedis);
        }
    }




}
