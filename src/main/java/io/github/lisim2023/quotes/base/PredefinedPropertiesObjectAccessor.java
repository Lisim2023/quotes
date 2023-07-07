package io.github.lisim2023.quotes.base;

import io.github.lisim2023.quotes.exceptions.DynamicObjectAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通过在类中预定义属性的方式实现"动态"添加数据(默认方式)
 *
 * @author Lisim
 */
public class PredefinedPropertiesObjectAccessor implements DynamicObjectAccessor<Object> {

    @Override
    public Object javaObjectToDynamicObject(Object record) throws DynamicObjectAccessException {
        return record;
    }

    @Override
    public Object createDynamicObjectArray() throws DynamicObjectAccessException {
        return new ArrayList<>();
    }

    @Override
    public void addMemberToDynamicObjectArray(Object dynamicObjectArray, Object member) throws DynamicObjectAccessException {
        try {
            ((List<Object>)dynamicObjectArray).add(member);
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }

    @Override
    // 对预定义属性进行更新已经作为默认行为实现, 这里无需操作
    public void addProperties(Object dynamicObject, Map<String, Object> properties) throws DynamicObjectAccessException {
        // do nothing
    }

    @Override
    public void updatePropertiesOnCopy(Object dynamicObject, Map<String, Object> properties) throws DynamicObjectAccessException {
        // do nothing
    }
}
