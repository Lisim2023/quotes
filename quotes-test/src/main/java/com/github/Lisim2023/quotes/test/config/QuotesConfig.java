package com.github.Lisim2023.quotes.test.config;


import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.github.Lisim2023.quotes.combo.ComboHelper;
import com.github.Lisim2023.quotes.combo.ComboHelperImpl;
import com.github.Lisim2023.quotes.combo.DefaultComboHelper;
import com.github.Lisim2023.quotes.dict.*;
import com.github.Lisim2023.quotes.extend.*;
import com.github.Lisim2023.quotes.quote.*;
import com.github.Lisim2023.quotes.test.base.MapEntity;
import com.github.Lisim2023.quotes.test.base.MapEntityAccessor;
import com.google.gson.Gson;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;

import javax.annotation.Resource;


@Configuration
public class QuotesConfig {

    @Value("${spring.redis.host:localhost}")
    private String redisIp;

    @Value("${spring.redis.port:6379}")
    private Integer redisPort;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private JedisProvider jedisProvider;

    @Resource
    private Gson gson;

    @Resource
    private DictDataProvider sysDictService;

    @Resource
    private QuoteDataProvider sysRoleService;

    /**
     * Default
     */
//    @Bean
//    public ComboHelper comboHelper(){
//        StringJedisPoolCacheHandler jedisPoolCacheHandler = new StringJedisPoolCacheHandler(jedisProvider);
//        FastJsonAccessor fastJsonAccessor = new FastJsonAccessor(new Feature[]{}, new SerializerFeature[]{});
//
//        DictCache dictCache = new DictCacheImpl<>(fastJsonAccessor, jedisPoolCacheHandler);
//        QuoteCache quoteCache = new QuoteCacheImpl<>(fastJsonAccessor, jedisPoolCacheHandler);
//
//
//        DefaultDictHelper dictHelper = new DefaultDictHelper(sysDictService);
//        dictHelper.setDictCache(dictCache);
//
//        DefaultQuoteHelper quoteHelper = new DefaultQuoteHelper(sysRoleService);
//        quoteHelper.setQuoteCache(quoteCache);
//
//        return new DefaultComboHelper(dictHelper, quoteHelper);
//    }


    /**
     * MapEntity
     */
//    @Bean
//    public ComboHelper comboHelper(){
//        MapEntityAccessor mapEntityAccessor = new MapEntityAccessor();
//
//        StringRedisTemplateCacheHandler redisTemplateCacheHandler = new StringRedisTemplateCacheHandler(redisTemplate);
//        FastJsonAccessor fastJsonAccessor = new FastJsonAccessor();
//
//        DictCache dictCache = new DictCacheImpl<>(fastJsonAccessor, redisTemplateCacheHandler);
//        QuoteCache quoteCache = new QuoteCacheImpl<>(fastJsonAccessor, redisTemplateCacheHandler);
//
//
//        DictHelperImpl<MapEntity> dictHelper = new DictHelperImpl<>(mapEntityAccessor, sysDictService);
//        dictHelper.setDictCache(dictCache);
//
//        QuoteHelperImpl<MapEntity> quoteHelper = new QuoteHelperImpl<>(mapEntityAccessor, sysRoleService);
//        quoteHelper.setQuoteCache(quoteCache);
//
//        return new ComboHelperImpl<>(mapEntityAccessor, dictHelper, quoteHelper);
//    }


    /**
     * JSON
     * 需要停用QuotesAspect, 启用QuotesJSONAspect
     */
    @Bean
    public ComboHelper comboHelper(){
        StringJedisCacheHandler jedisCacheHandler = new StringJedisCacheHandler(new Jedis(redisIp, redisPort));

//        GsonAccessor gsonAccessor = new GsonAccessor(gson);
//        FastJsonAccessor fastJsonAccessor = new FastJsonAccessor();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JacksonAccessor jacksonAccessor = new JacksonAccessor(objectMapper);

        DictCache dictCache = new DictCacheImpl<>(jacksonAccessor, jedisCacheHandler);
        QuoteCache quoteCache = new QuoteCacheImpl<>(jacksonAccessor, jedisCacheHandler);


        DictHelperImpl<ObjectNode> dictHelper = new DictHelperImpl<>(jacksonAccessor, sysDictService);
        dictHelper.setDictCache(dictCache);

        QuoteHelperImpl<ObjectNode> quoteHelper = new QuoteHelperImpl<>(jacksonAccessor, sysRoleService);
        quoteHelper.setQuoteCache(quoteCache);

        return new ComboHelperImpl<>(jacksonAccessor, dictHelper, quoteHelper);
    }

}
