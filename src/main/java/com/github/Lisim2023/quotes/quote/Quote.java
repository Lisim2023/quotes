package com.github.Lisim2023.quotes.quote;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *  数据引用
 *  标记于需要引用关联表数据的字段
 * </p>
 *
 * @author Lisim
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Quote {

    // 目标表名
    String table();

    // 需要引用的字段
    String[] columns();

    // 目标表的关联字段名
    String key() default "id";
}
