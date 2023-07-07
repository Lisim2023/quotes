package com.github.Lisim2023.quotes.dict;

import com.github.Lisim2023.quotes.exceptions.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <P>
 *   数据字典助手, 功能包括:
 *   1.对实体类对象 追加 字典值对应的字典文本, 用于向前端返回数据.
 *   2.将对象数据(Map形式)中的字典值 替换 为字典文本(或逆向操作), 可用于Excel导入及导出.
 *   3.字典数据相关缓存操作.
 * </P>
 *
 * @author Lisim
 */
public interface DictHelper {

    // 将实体类对象转换为动态对象, 并 追加 字典值对应的文本
    Object appendDict(Object record) throws
            DictNotFoundException,
            DictValueNotFoundException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;

    // 集合类数据的返回值统一使用ArrayList
    List<Object> appendDict(Collection<?> records) throws
            DictNotFoundException,
            DictValueNotFoundException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;

    <T> Map<T, Object> appendDict(Map<T, ?> recordMap) throws
            DictNotFoundException,
            DictValueNotFoundException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;


    // 将对象数据中的字典文本 替换 为字典值, 以便转换为实体类对象
    void replaceDictInverse(Map<String, Object> objectData, Class<?> tClass) throws
            DictNotFoundException,
            DictValueNotFoundException,
            SerializationException;
    void replaceDictInverse(Collection<Map<String, Object>> objectsData, Class<?> tClass) throws
            DictNotFoundException,
            DictValueNotFoundException,
            SerializationException;

    // 将对象数据中的字典值 替换 为字典文本
    // 需要先将实体类对象转换为Map
    void replaceDict(Map<String, Object> objectData, Class<?> tClass) throws
            DictNotFoundException,
            DictValueNotFoundException,
            SerializationException;
    void replaceDict(Collection<Map<String, Object>> objectsData, Class<?> tClass) throws
            DictNotFoundException,
            DictValueNotFoundException,
            SerializationException;


    // 缓存字典数据
    void cacheDict(String dictCode, Map<String, Object> dictData);
    void cacheDictBatch(Map<String, Map<String, Object>> dictData);
    void cacheAllDict();

    // 重命名缓存
    void rename(String oldCode, String newCode);

    // 读取字典数据, 优先缓存
    // 缓存未命中则调用DictDataProvider获取, 并缓存这部分数据
    // 若inverse为true则反转字典
    Map<String, Object> getDict(String dictCode);
    Map<String, Object> getDict(String dictCode, boolean inverse);
    Map<String, Map<String, Object>> getDictBatch(List<String> dictCodeList);
    Map<String, Map<String, Object>> getDictBatch(List<String> dictCodeList, boolean inverse);

    // 从缓存中加载全部字典数据
    Map<String, Map<String, Object>> loadAllDict();

    // 从缓存中移除字典数据
    Long removeDict(String dictCode);
    Long removeDict(Collection<String> dictCodes);
    Long removeAllDict();
}
