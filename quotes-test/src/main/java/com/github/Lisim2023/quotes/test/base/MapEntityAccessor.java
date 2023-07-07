package com.github.Lisim2023.quotes.test.base;



import com.github.Lisim2023.quotes.base.DynamicObjectAccessor;
import com.github.Lisim2023.quotes.exceptions.DynamicObjectAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapEntityAccessor implements DynamicObjectAccessor<MapEntity> {

    @Override
    public MapEntity javaObjectToDynamicObject(Object record) throws DynamicObjectAccessException {
        if (record == null){
            return null;
        }
        // 只处理MapEntity类型对象
        if (record instanceof MapEntity){
            // 返回对象本身即可
            return (MapEntity) record;
        }
        throw new DynamicObjectAccessException("type error : " + record.getClass() + " is not support with MapEntityAccessor");
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
    public void addProperties(MapEntity dynamicObject, Map<String, Object> properties) throws DynamicObjectAccessException {
        dynamicObject.getDataMap().putAll(properties);
    }

    @Override
    public void updatePropertiesOnCopy(MapEntity dynamicObject, Map<String, Object> properties) throws DynamicObjectAccessException {
        // do nothing
    }
}
