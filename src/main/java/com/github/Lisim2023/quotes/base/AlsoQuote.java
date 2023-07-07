package com.github.Lisim2023.quotes.base;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     递归标记
 *     用于标记于实体类中的 其他实体类 成员变量(可以是 单个对象, 集合, 或Map)
 *     表示在追加字典及引用数据时同时处理该成员变量中的实体类
 * </p>
 *
 * @author Lisim
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlsoQuote {
}
