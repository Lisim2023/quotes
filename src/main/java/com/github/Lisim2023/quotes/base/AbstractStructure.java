package com.github.Lisim2023.quotes.base;


import com.github.Lisim2023.quotes.exceptions.IllegalAccessRunTimeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  递归结构
 * </p>
 *
 * @author Lisim
 */
public abstract class AbstractStructure {

    protected Map<Class<?>, Map<String, Field>> classAllFieldsMap = new HashMap<>();
    protected Map<Class<?>, List<Field>> classRecursionFieldsMap = new HashMap<>();

    public AbstractStructure(){

    }



    public abstract Map<String, Object> getDynamicProperties(Object record);





    protected void analyse(Object record) {
        if (record == null) {
            return;
        }
        if (record instanceof Collection){
            analyse((Collection<?>) record);
            return;
        }
        if (record instanceof Map){
            analyse((Map<?, ?>) record);
            return;
        }

        Class<?> clazz = record.getClass();
        List<Field> recursionFields = this.classRecursionFieldsMap.get(clazz);
        if (recursionFields == null){
            Map<String, Field> fieldMap = getAllFieldsMap(clazz);
            recursionFields = fieldMap.values().stream()
                    .filter(field -> field.getAnnotation(AlsoQuote.class) != null)
                    .collect(Collectors.toList());
            this.classRecursionFieldsMap.put(clazz, recursionFields);
        }

        try {
            for (Field recursionField : recursionFields) {
                recursionField.setAccessible(true);
                Object subObject = recursionField.get(record);
                analyse(subObject);
            }
        }catch (IllegalAccessException e){
            throw new IllegalAccessRunTimeException(e.getMessage(), e);
        }
    }

    protected void analyse(Collection<?> records) {
        if (records == null){
            return;
        }
        for (Object record : records) {
            analyse(record);
        }
    }

    protected void analyse(Map<?, ?> recordMap){
        if (recordMap == null){
            return;
        }
        for (Object value : recordMap.values()) {
            analyse(value);
        }
    }

    protected Map<Class<?>, List<Field>> collectAnnotationFields(Class<? extends Annotation> annotationClass){
        Map<Class<?>, List<Field>> classAnnotationFieldsMap = new HashMap<>();
        classAllFieldsMap.forEach((clazz, fieldMap) -> {
            List<Field> annotationFields = fieldMap.values().stream()
                    .filter(field -> field.getAnnotation(annotationClass) != null)
                    .collect(Collectors.toList());
            classAnnotationFieldsMap.put(clazz, annotationFields);
        });
        return classAnnotationFieldsMap;
    }

    protected void updatePreDefinedProperties(Object record, Map<String, Object> properties){
        if (record == null) {
            return;
        }
        if (properties == null || properties.size() == 0){
            return;
        }

        Map<String, Field> allFieldsMap = getAllFieldsMap(record.getClass());

        properties.forEach((key, value) -> {
            try {
                Field field = allFieldsMap.get(key);
                if (field != null){
                    field.setAccessible(true);
                    field.set(record, value);
                }
            }catch (IllegalAccessException e){
                throw new IllegalAccessRunTimeException(e.getMessage(), e);
            }
        });
    }

    protected Map<String, Field> getAllFieldsMap(Class<?> clazz){
        Map<String, Field> allFieldsMap = classAllFieldsMap.get(clazz);
        if (allFieldsMap == null){
            allFieldsMap = getAllFields(clazz).stream()
                    .collect(Collectors.toMap(Field::getName, field -> field, (oldData, newData) -> oldData));
            classAllFieldsMap.put(clazz, allFieldsMap);
        }
        return allFieldsMap;
    }

    protected List<Field> getAllFields(Class<?> clazz) {
        List<Field> allFields = new ArrayList<>();
        while(clazz != null && clazz != Object.class){
            allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return allFields;
    }





    public Map<Class<?>, Map<String, Field>> getClassAllFieldsMap() {
        return classAllFieldsMap;
    }

    public void setClassAllFieldsMap(Map<Class<?>, Map<String, Field>> classAllFieldsMap) {
        this.classAllFieldsMap = classAllFieldsMap;
    }

    public Map<Class<?>, List<Field>> getClassRecursionFieldsMap() {
        return classRecursionFieldsMap;
    }

    public void setClassRecursionFieldsMap(Map<Class<?>, List<Field>> classRecursionFieldsMap) {
        this.classRecursionFieldsMap = classRecursionFieldsMap;
    }


}
