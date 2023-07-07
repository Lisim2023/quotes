package io.github.lisim2023.quotes.extend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import io.github.lisim2023.quotes.exceptions.DynamicObjectAccessException;
import io.github.lisim2023.quotes.exceptions.SerializationException;
import io.github.lisim2023.quotes.common.CacheSerializer;
import io.github.lisim2023.quotes.base.DynamicObjectAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  使用FastJson完成缓存序列化和JSON对象操作
 * </p>
 *
 * @author Lisim
 */
public class FastJsonAccessor implements DynamicObjectAccessor<JSONObject>, CacheSerializer<String> {

    private Feature[] features;
    private SerializerFeature[] serializerFeatures;


    public FastJsonAccessor() {
        this.features = new Feature[]{};
        this.serializerFeatures = new SerializerFeature[]{};
    }

    public FastJsonAccessor(Feature[] features) {
        this.features = features;
        this.serializerFeatures = new SerializerFeature[]{};
    }

    public FastJsonAccessor(SerializerFeature[] serializerFeatures) {
        this.features = new Feature[]{};
        this.serializerFeatures = serializerFeatures;
    }

    public FastJsonAccessor(Feature[] features, SerializerFeature[] serializerFeatures) {
        this.features = features;
        this.serializerFeatures = serializerFeatures;
    }





    @Override
    public String serialize(Map<String, Object> dataToCache) {
        try {
            return JSON.toJSONString(dataToCache, serializerFeatures);
        }catch (Exception e){
            throw new SerializationException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> deSerialize(String cacheData) {
        if (cacheData == null){
            return null;
        }
        try {
            return JSON.parseObject(cacheData, features);
        }catch (Exception e){
            throw new SerializationException(e.getMessage(), e);
        }
    }





    @Override
    public Object createDynamicObjectArray() throws DynamicObjectAccessException {
//        return new JSONArray();
        return new ArrayList<>();
    }

    @Override
    public void addMemberToDynamicObjectArray(Object dynamicObjectArray, Object member) throws DynamicObjectAccessException {
        try {
            ((List<Object>) dynamicObjectArray).add(member);
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }

    @Override
    public JSONObject javaObjectToDynamicObject(Object record) throws DynamicObjectAccessException {
        try{
            return JSON.parseObject(JSON.toJSONString(record, serializerFeatures), features);
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }


    @Override
    public void addProperties(JSONObject jsonObject, Map<String, Object> properties) {
        if (jsonObject != null && properties != null){
            jsonObject.putAll(properties);
        }
    }

    @Override
    public void updatePropertiesOnCopy(JSONObject jsonObject, Map<String, Object> properties) {
        if (jsonObject != null && properties != null){
            jsonObject.putAll(properties);
        }
    }






    public SerializerFeature[] getSerializerFeatures() {
        return serializerFeatures;
    }

    public void setSerializerFeatures(SerializerFeature[] serializerFeatures) {
        this.serializerFeatures = serializerFeatures;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature[] features) {
        this.features = features;
    }
}
