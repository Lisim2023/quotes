package io.github.lisim2023.quotes.base;

import io.github.lisim2023.quotes.exceptions.IllegalAccessRunTimeException;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>
 *  助手基类
 * </p>
 *
 * @author Lisim
 */
public abstract class AbstractHelper<DynamicO> {

    protected DynamicObjectAccessor<DynamicO> dynamicObjectAccessor;

    protected AbstractHelper(){}

    public AbstractHelper(DynamicObjectAccessor<DynamicO> dynamicObjectAccessor) {
        this.dynamicObjectAccessor = dynamicObjectAccessor;
    }

    // 抹消泛型, 定义Helper接口时不再需要泛型, 方便配置
    // 同时可以添加类型判断, 调用时可以不用判断
    public Object appendData(Object record, AbstractStructure abstractStructure){
        return appendData(record, abstractStructure, false);
    }

    public List<Object> appendData(Collection<?> records, AbstractStructure abstractStructure){
        if (records == null){
            return null;
        }
        List<Object> objectList = new ArrayList<>();
        for (Object record : records) {
            objectList.add(appendData(record, abstractStructure));
        }
        return objectList;
    }

    public <T> Map<T, Object> appendData(Map<T, ?> recordMap, AbstractStructure abstractStructure){
        return appendData(recordMap, abstractStructure, false);
    }







    protected DynamicO appendDataGeneric(Object record, AbstractStructure abstractStructure){
        if (record == null){
            return null;
        }

        DynamicO dynamicObject = dynamicObjectAccessor.javaObjectToDynamicObject(record);

        if (abstractStructure == null){
            return dynamicObject;
        }

        Map<String, Object> properties = abstractStructure.getDynamicProperties(record);

        dynamicObjectAccessor.addProperties(dynamicObject, properties);

        try {
            List<Field> recursionFields = abstractStructure.getClassRecursionFieldsMap().get(record.getClass());
            if (recursionFields != null && recursionFields.size() > 0){
                Map<String, Object> p = new HashMap<>();
                for (Field recursionField : recursionFields) {
                    recursionField.setAccessible(true);
                    Object subObject = recursionField.get(record);
                    if (subObject == null){
                        continue;
                    }
                    p.put(recursionField.getName(), appendData(subObject, abstractStructure, true));
                }
                dynamicObjectAccessor.updatePropertiesOnCopy(dynamicObject, p);
            }
        } catch (IllegalAccessException e) {
            throw new IllegalAccessRunTimeException(e.getMessage(), e);
        }

        return dynamicObject;
    }

    protected Object appendData(Object record, AbstractStructure abstractStructure, boolean inside){
        if (record == null){
            return null;
        }
        if (record instanceof Collection){
            if (inside){
                return appendArray((Collection<?>) record, abstractStructure);
            }else {
                return appendData((Collection<?>) record, abstractStructure);
            }
        }
        if (record instanceof Map){
            return appendData((Map<?, ?>) record, abstractStructure, inside);
        }

        return appendDataGeneric(record, abstractStructure);
    }

    protected Object appendArray(Collection<?> records, AbstractStructure abstractStructure){
        Object dynamicObjectArray = dynamicObjectAccessor.createDynamicObjectArray();
        for (Object record : records) {
            dynamicObjectAccessor.addMemberToDynamicObjectArray(dynamicObjectArray, appendData(record, abstractStructure, true));
        }
        return dynamicObjectArray;
    }

    protected <T> Map<T, Object> appendData(Map<T, ?> recordMap, AbstractStructure abstractStructure, boolean inside){
        if (recordMap == null){
            return null;
        }
        Map<T, Object> map = new HashMap<>();

        recordMap.forEach((key, value) -> {
            map.put(key, appendData(value, abstractStructure, inside));
        });

        return map;
    }







    public DynamicObjectAccessor<DynamicO> getDynamicObjectHandler() {
        return dynamicObjectAccessor;
    }

    public void setDynamicObjectHandler(DynamicObjectAccessor<DynamicO> dynamicObjectAccessor) {
        this.dynamicObjectAccessor = dynamicObjectAccessor;
    }
}
