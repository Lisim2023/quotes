package io.github.lisim2023.quotes.dict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *  标记于使用数据字典的字段
 * </p>
 *
 * @author Lisim
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    // 数据字典编码
    String value();
}
