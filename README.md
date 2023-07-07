
Quotes
==========

### 使用注解从关联表引用数据并自动组装, 同时接供双向数据字典工具, 可组合使用




功能介绍
----------

* ### 使用 @Quote注解 从关联表引用数据
    * #### @Quote注解示例:
    ```java
    public class RoleUser {
    
        private String id;  
        
        @Quote(table = "sys_role", columns = "name", key = "id")
        private String roleId;
        
        // key的值默认为"id", 之后都省略
        @Quote(talbe = "sys_user", columns = {"username", "nickname"})
        private String userId;
    }
    ```
    对roleUser表进行单表查询即可得到如下结果:<br>
    <img src="https://github.com/Lisim2023/quotes/blob/master/images/quote_multiFields.png" />
    
    * #### 属性值可以是多个值, 用逗号分隔, 示例：
    ```java
    public class UserGroup {
  
        private String id;
        private String name;
        
        @Quote(table = "sys_user", columns = {"username", "nickname"})
        private String members;
    }
    ```
    同样仅需单表查询:<br>
    <img src="https://github.com/Lisim2023/quotes/blob/master/images/quote_multiValues.png" />

* ### 使用 @Dict注解 描述数据字典

    * #### @Dict注解 示例:
    ```java
    public class User {
    
        private String id;  
        private String name;
        private Integer age;
        
        @Dict("sex")
        private Integer sex;
        
        @Dict("hobbies")
        private String hobbies;
    }
    ```
  
    效果:<br>
    <img src="https://github.com/Lisim2023/quotes/blob/master/images/dict_multiValues.png" />

    * #### @Dict注解 可以对引用来的值使用
    需要对引用来的值预定义属性,<br>
    命名规则为:原属性名 + 中缀_quote_ + 字段名,<br>
    类型一律为String,<br>
    然后在预定义的属性上标注@Dict注解即可.<br>
    示例:
    ```java
    public class RoleUser {
    
        private String id;  
        
        @Quote(table = "sys_role", columns = "name")
        private String roleId;
        
        @Quote(talbe = "sys_user", columns = {"username", "nickname", "sex", "hobbies"})
        private String userId;
  
        // 预定义属性
        @Dict("sex")
        private String userId_quote_sex;
  
        @Dict("hobbies")
        private String userId_quote_hobbies;
    }
    ```
    效果:<br>
    <img src="https://github.com/Lisim2023/quotes/blob/master/images/dict_afterQuote.png" />
  

* ### 递归标记 @AlsoQuote
    @AlsoQuote注解的使用
    ```java
    public class classes {
        private String id;
        private String name;
    
        // Student类中如果有标注@Dict或@Quote注解也会一并处理 
        @AlsoQuote
        private List<Student> studentList;
        
        // 没有递归标记的不会处理
        private List<Teacher> teacherList;
    }
    ```
    效果见测试项目中的Menu

使用步骤
----------
1. ### 引入maven依赖

2. ### 实现数据提供接口
    数据提供接口没有默认实现<br>
    使用字典功能需要实现`DictDataProvider`, 见测试项目中的SysDictServiceImpl<br>
    使用引用功能需要实现`QuoteDataProvider`, 见测试项目中的SysRoleServiceImpl<br>
    <br>
    可以通过助手方法辅助完成数据缓存, 那么实现数据提供接口时只需从数据库查询<br>
    也可以将助手设置为不缓存(默认), 并在实现数据提供接口时应用现有缓存方案<br>
3. ### 配置助手
    仅使用引用功能, 配置引用助手`QuoteHelper`即可<br>
    仅使用字典功能, 配置字典助手`DictHelper`即可<br>
    两种功能都要使用则需要将两种助手组合为`ComboHelper`<br>
   简单示例:
   ````java
    @Configuration
    public class QuotesConfig {
    
        @Resource
        private DictDataProvider dictDataProvider;
    
        @Resource
        private QuoteDataProvider quoteDataProvider;
   
        // 根据需要配置助手
        @Bean
        public DictHelper dictHelper(){
            return new DefaultDictHelper(dictDataProvider);
        }
        @Bean
        public QuoteHelper quoteHelper(){
            return new DefaultQuoteHelper(quoteDataProvider);
        }
        @Bean
        public ComboHelper comboHelper(){
            return new DefaultComboHelper(dictHelper(), quoteHelper());
        }
    }
   ````
   详见测试项目

4. ### 调用助手方法追加数据
    推荐定义切面, 在切面中统一处理<br>
    建议添加自定义标识用以区分, 避免影响其他功能.<br>
   简单示例:
   ```java
   @Aspect
   @Component
   public class QuotesAspect {
   
       @Resource
       ComboHelper comboHelper;

       @Pointcut("execution(public * com.github.Lisim2023.quotes.test.modules..*.*Controller.*(..))")
       public void quote() {
       }

       @AfterReturning(value = "quote()", returning = "result")
       public void afterReturning(Object result){
           if (result instanceof AjaxResult) {
               
               // 只处理isQuote为true的数据, 确保不会影响其他功能 
               if (((AjaxResult<?>) result).isQuote()){
                   comboHelper.appendQuoteAndDict(((AjaxResult<?>) result).getData());
               }
           }
       }
   }
   ```

5. ### 在实体类中根据需要对属性标注注解

配置选项
----------
展示可配置的选项及其默认值

```java
@Configuration
public class QuotesConfig {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private DictDataProvider sysDictService;

    @Resource
    private QuoteDataProvider sysRoleService;

    @Bean
    public ComboHelper comboHelper() {

        StringRedisTemplateCacheHandler redisTemplateCacheHandler = new StringRedisTemplateCacheHandler(redisTemplate);

        JacksonAccessor jacksonAccessor = new JacksonAccessor(objectMapper);

        // 字典
        DictHelperImpl<ObjectNode> dictHelper = new DictHelperImpl<>(jacksonAccessor, sysDictService);
        // 属性名称后缀
        DictHelperImpl.DICT_SUFFIX = "_dictLabel";

        // 字典缓存
        DictCache dictCache = new DictCacheImpl<>(jacksonAccessor, redisTemplateCacheHandler);
        // 缓存前缀
        // 请务必确保此前缀不会匹配到字典以外的其他数据
        DictCacheImpl.CACHE_KEY_PREFIX = "quotes:dict";

        dictHelper.setDictCache(dictCache);


        // 引用
        QuoteHelperImpl<ObjectNode> quoteHelper = new QuoteHelperImpl<>(jacksonAccessor, sysRoleService);
        // 属性名称中缀
        QuoteHelperImpl.QUOTE_INFIX = "_quote_";
        // 当引用的字段值为null时, 展示此字符
        QuoteHelperImpl.NULL_STRING = "null";

        // 引用缓存
        QuoteCache quoteCache = new QuoteCacheImpl<>(jacksonAccessor, redisTemplateCacheHandler);
        // 缓存前缀
        QuoteCacheImpl.CACHE_KEY_PREFIX = "quotes:quote";
        // 默认缓存时间(秒)
        QuoteCacheImpl.DEFAULT_CACHE_SECONDS = 30 * 60L;
        // 可对个别表单独设置缓存时间(表名:秒)
        // 设为0则该表不缓存
        QuoteCacheImpl.TABLE_CACHE_SECONDS = new HashMap<>();

        quoteHelper.setQuoteCache(quoteCache);

        // 组合
        return new ComboHelperImpl(jacksonAccessor, dictHelper, quoteHelper);
    }

}
```

组装数据的方式
----------
  * ### 完全预定义属性(默认)
    需要对所有从关联表或数据字典获取的数据都在类中预定义属性<br>
    预定义的属性类型一律为String<br>
    引用属性命名规则:原属性名 + 中缀 + 字段名<br>
    字典属性命名规则:原属性名 + 后缀<br>
    <br>
    若引用了某个字段但没有预定义属性, 会被忽略, 不会拋出异常, 使用时请留意
  * ### 转换为JSON对象
    将原数据对象转换为JSON包提供的对象, 并将结果添加至新对象<br>
    通常接口返回的数据最终都会被转为JSON String, 在此之前先转为JSON包里的对象理论上不会有什么影响<br>
    目前提供有:
    * `JacksonAccessor` --- `ObjectNode`
    * `FastJsonAccessor` --- `JSONObject`
    * `GsonAccessor` --- `JsonObject`
    
    这种方式只在 "先引用后字典" 的情况需要预定义属性<br>
    
  * ### 自定义
    实现`DynamicObjectAccessor`动态对象操作接口(可参考前两种方式), 并配置到项目中即可<br>
    示例项目中的 MapEntityAccessor --- 以内置Map的方式实现动态添加数据, 也可作为参考<br>
    <br>
    <font color = "red">注意</font>: 助手分析时会将Map类型视为数据对象的容器, 而不是数据对象, 所以不要用实体类实现Map接口
    
  
缓存数据
----------
* ### 缓存方式
    目前提供的缓存方式都是基于Redis, 且数据类型都是String<br>
    包括:
    * `StringJedisCacheHandler` --- Jedis直连
    * `StringJedisPoolCacheHandler` --- Jedis池
    * `StringRedisTemplateCacheHandler` --- RedisTemplate
  
    其他缓存方式待扩充<br>
    也可自行实现`DictCacheHandler`与`QuoteCacheHandler`接口完成扩展
    
* ### 缓存序列化
    目前提供的序列化方式都是借助JSON工具序列化为JSON String<br>
    包括:
    * `JacksonAccessor` --- Jackson
    * `FastJsonAccessor` --- FastJson
    * `GsonAccessor` --- Gson

    实现`CacheSerializer`接口即可自由扩展<br>
    <br>
    <font color = "red">注意</font>: 缓存方式与缓存序列化的泛型类型必须保持一致<br>
    当以其他类型实现了`CacheSerializer`接口时, 比如Map<br>
    需要同样以Map作为泛型类型实现`DictCacheHandler`与`QuoteCacheHandler`接口, 并配合使用
