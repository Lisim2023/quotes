package io.github.lisim2023.quotes.extend;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import io.github.lisim2023.quotes.common.CommonUtil;
import io.github.lisim2023.quotes.base.DynamicObjectAccessor;
import io.github.lisim2023.quotes.exceptions.DynamicObjectAccessException;
import io.github.lisim2023.quotes.exceptions.SerializationException;
import io.github.lisim2023.quotes.common.CacheSerializer;

import java.util.Map;

/**
 * <p>
 *  使用Gson完成缓存序列化和JSON对象操作
 * </p>
 *
 * @author Lisim
 */
public class GsonAccessor implements DynamicObjectAccessor<JsonObject>, CacheSerializer<String> {

    private Gson gson;


    public GsonAccessor(){
        this.gson = new Gson();
    }

    public GsonAccessor(Gson gson){
        this.gson = gson;
    }





    @Override
    public String serialize(Map<String, Object> dataToCache) throws SerializationException {
        try {
            return gson.toJson(dataToCache);
        }catch (Exception e){
            throw new SerializationException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> deSerialize(String cacheData) throws SerializationException {
        if (cacheData == null){
            return null;
        }
        try {
            return gson.fromJson(cacheData, new TypeToken<Map<String, Object>>(){}.getType());
        }catch (JsonSyntaxException e){
            throw new SerializationException(e.getMessage(), e);
        }
    }





    @Override
    public Object createDynamicObjectArray() throws DynamicObjectAccessException {
        return new JsonArray();
    }

    @Override
    public void addMemberToDynamicObjectArray(Object dynamicObjectArray, Object member) throws DynamicObjectAccessException {
        try {
            if (member instanceof JsonArray){
                ((JsonArray) dynamicObjectArray).add((JsonArray) member);
            }else if (member instanceof JsonObject){
                ((JsonArray) dynamicObjectArray).add((JsonObject) member);
            }else {
                ((JsonArray) dynamicObjectArray).add(javaObjectToDynamicObject(member));
            }
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }

    @Override
    public JsonObject javaObjectToDynamicObject(Object record) throws DynamicObjectAccessException {
        try {
            return gson.fromJson(gson.toJson(record), JsonObject.class);
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void addProperties(JsonObject jsonObject, Map<String, Object> properties) throws DynamicObjectAccessException {
        if (jsonObject == null || properties == null){
            return;
        }
        try{
            properties.forEach((k, v) -> jsonObject.addProperty(k, CommonUtil.toString(v)));
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void updatePropertiesOnCopy(JsonObject jsonObject, Map<String, Object> properties) throws DynamicObjectAccessException {
        if (jsonObject == null || properties == null){
            return;
        }
        try {
            properties.forEach((k, v) -> {
                if (v instanceof JsonArray){
                    jsonObject.add(k, (JsonArray) v);
                }else if (v instanceof JsonObject){
                    jsonObject.add(k, (JsonObject) v);
                }else {
                    jsonObject.add(k, javaObjectToDynamicObject(v));
                }
            });
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }





    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
