package io.github.lisim2023.quotes.dict;

import io.github.lisim2023.quotes.common.DataProvider;

import java.util.List;
import java.util.Map;

/**
 * <P>
 *   字典数据提供接口, 通过此接口获取字典数据
 * </P>
 *
 * @author Lisim
 */
public interface DictDataProvider extends DataProvider {

    // 返回值为Map<字典编码, Map<字典值, 字典文本>>

    Map<String, Map<String, Object>> queryDictData(List<String> dictCodeList);

    Map<String, Map<String, Object>> queryAllDictData();

}
