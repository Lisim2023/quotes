package io.github.Lisim2023.quotes.test.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectUtil {


    public static String toString(Object object){
        return object == null ? "" : object.toString();
    }

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        return ( null == object || "".equals(object) || "null".equals(object) );
    }
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean allNotEmpty(Object... values) {
        if (values == null) {
            return false;
        }
        for (Object object : values) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 获取类的所有属性 包括父类的
     * @param clazz
     * @return
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        while(clazz != Object.class && clazz != null){
            list.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        };
        return list;
    }


    public static List<Field> getAnnotationFieldList(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return getAllFields(clazz).stream()
                .filter(field -> field.getAnnotation(annotationClass) != null)
                .collect(Collectors.toList());
    }
}
