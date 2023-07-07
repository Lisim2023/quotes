package com.github.Lisim2023.quotes.extend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.github.Lisim2023.quotes.base.DynamicObjectAccessor;
import com.github.Lisim2023.quotes.common.CacheSerializer;
import com.github.Lisim2023.quotes.exceptions.DynamicObjectAccessException;
import com.github.Lisim2023.quotes.exceptions.SerializationException;
import com.github.Lisim2023.quotes.common.CommonUtil;

import java.util.Map;

/**
 * <p>
 *  使用JackSon完成缓存序列化和JSON对象操作
 * </p>
 *
 * @author Lisim
 */
public class JacksonAccessor implements DynamicObjectAccessor<ObjectNode>, CacheSerializer<String> {

    private ObjectMapper objectMapper;


    public JacksonAccessor(){
        this.objectMapper = new ObjectMapper();
    }

    public JacksonAccessor(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }


    @Override
    public String serialize(Map<String, Object> dataToCache) {
        try {
            return objectMapper.writeValueAsString(dataToCache);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> deSerialize(String cacheData) {
        if (cacheData == null){
            return null;
        }
        try {
            return objectMapper.readValue(cacheData, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e){
            throw new SerializationException(e.getMessage(), e);
        }
    }





    @Override
    public Object createDynamicObjectArray() throws DynamicObjectAccessException {
        return objectMapper.createArrayNode();
    }

    @Override
    public void addMemberToDynamicObjectArray(Object dynamicObjectArray, Object member) throws DynamicObjectAccessException {
        try {
            if (member instanceof ArrayNode){
                ((ArrayNode)dynamicObjectArray).add((ArrayNode)member);
            }else if (member instanceof ObjectNode){
                ((ArrayNode)dynamicObjectArray).add((ObjectNode)member);
            }else {
                ((ArrayNode)dynamicObjectArray).add(javaObjectToDynamicObject(member));
            }
        }catch (Exception e){
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }

    @Override
    public ObjectNode javaObjectToDynamicObject(Object record) throws DynamicObjectAccessException {
        try {
            String json = objectMapper.writeValueAsString(record);
            return objectMapper.readValue(json, ObjectNode.class);
        } catch (JsonProcessingException e) {
            throw new DynamicObjectAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void addProperties(ObjectNode objectNode, Map<String, Object> properties) {
        if (objectNode != null && properties != null){
            properties.forEach((k, v) -> objectNode.put(k, CommonUtil.toString(v)));
        }
    }

    @Override
    public void updatePropertiesOnCopy(ObjectNode objectNode, Map<String, Object> properties) {
        if (objectNode != null && properties != null){
            properties.forEach((k, v) -> {
                if (v instanceof ArrayNode) {
                    objectNode.set(k, (ArrayNode) v);
                }else if (v instanceof ObjectNode){
                    objectNode.set(k, (ObjectNode) v);
                }else {
                    objectNode.set(k, javaObjectToDynamicObject(v));
                }
            });
        }
    }




    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
