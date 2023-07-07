package com.github.Lisim2023.quotes.quote;

import com.github.Lisim2023.quotes.common.DataProvider;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  引用数据提供接口
 *  通过此接口从数据源查询引用到的数据
 * </p>
 *
 * @author Lisim
 */
public interface QuoteDataProvider extends DataProvider {

    // 返回值为List<Map<字段名, 字段值>>
    List<Map<String, Object>> queryQuoteData(String table, String key, List<String> values);
}
