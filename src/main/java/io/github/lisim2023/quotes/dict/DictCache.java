package io.github.lisim2023.quotes.dict;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  为字典助手提供缓存操作
 * </p>
 *
 * @author Lisim
 */
public interface DictCache {


    // 缓存字典数据
    void cacheDict(String dictCode, Map<String, Object> dictData);
    void cacheDictBatch(Map<String, Map<String, Object>> dictData);

    // 从缓存加载字典数据
    Map<String, Object> loadDict(String dictCode);
    Map<String, Map<String, Object>> loadDictBatch(List<String> dictCodes);
    Map<String, Map<String, Object>> loadAllDict();

    // 从缓存移除字典数据
    Long removeDict(String dictCode);
    Long removeDict(Collection<String> dictCodes);
    Long removeAllDict();

    // 重命名缓存
    void rename(String oldCode, String newCode);

}
