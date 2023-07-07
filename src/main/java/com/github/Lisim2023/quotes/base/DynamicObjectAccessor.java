package com.github.Lisim2023.quotes.base;

import com.github.Lisim2023.quotes.exceptions.DynamicObjectAccessException;

import java.util.Map;


/**
 * 动态对象操作接口
 * @param <DynamicO> 动态对象类型
 *
 * @author Lisim
 */
public interface DynamicObjectAccessor<DynamicO> {

    // 将java对象转换为可动态添加属性的对象
    // 若对象本身即可动态添加属性, 或已预定义属性, 返回对象本身即可
    DynamicO javaObjectToDynamicObject(Object record) throws DynamicObjectAccessException;


    // 创建动态对象数组
    // 为兼容各JSON包中不同的对象数组而抽象出来的方法, 其他情况返回new ArrayList<Object>即可
    Object createDynamicObjectArray() throws DynamicObjectAccessException;


    // 为 createDynamicObjectArray 方法中产生的动态对象数组添加成员
    // 这里没有使用泛型, 需要做类型转换
    void addMemberToDynamicObjectArray(Object dynamicObjectArray, Object member) throws DynamicObjectAccessException;


    // 为动态对象添加属性
    void addProperties(DynamicO dynamicObject, Map<String, Object> properties) throws DynamicObjectAccessException;


    // 在对原对象进行递归时, 更新新对象的属性
    // 仅在 javaObjectToDynamicObject 方法执行过程中产生了新的对象时需要实现
    void updatePropertiesOnCopy(DynamicO dynamicObject, Map<String, Object> properties) throws DynamicObjectAccessException;
}
