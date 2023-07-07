package io.github.lisim2023.quotes.combo;

import io.github.lisim2023.quotes.dict.DictHelper;
import io.github.lisim2023.quotes.exceptions.*;
import io.github.lisim2023.quotes.quote.QuoteHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     同时追加字典和引用数据
 *     提供 DictHelper 和 QuoteHelper 的所有方法
 * </p>
 *
 * @author Lisim
 */
public interface ComboHelper extends DictHelper, QuoteHelper {

    // 将实体类对象转换为动态对象, 并同时追加字典和引用的数据
    Object appendQuoteAndDict(Object record) throws
            DictNotFoundException,
            DictValueNotFoundException,
            QuoteNotFoundException,
            QuoteKeyMissingException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;

    // 集合类数据的返回值统一使用ArrayList
    List<Object> appendQuoteAndDict(Collection<?> records) throws
            DictNotFoundException,
            DictValueNotFoundException,
            QuoteNotFoundException,
            QuoteKeyMissingException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;

    <T> Map<T, Object> appendQuoteAndDict(Map<T, ?> recordMap) throws
            DictNotFoundException,
            DictValueNotFoundException,
            QuoteNotFoundException,
            QuoteKeyMissingException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;
}
