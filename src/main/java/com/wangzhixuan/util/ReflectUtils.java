package com.wangzhixuan.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author yunan
 * @date 2017/10/24
 */
public class ReflectUtils {
    private static final String CLASS_NAME = "className";

    /**
     * 反射取值，暂时只提供两层
     */
    private static Object getFieldValue(Class clazz, Object instance, String fieldName) throws ReflectiveOperationException {
        if(CLASS_NAME.equals(fieldName)){
            return clazz.getName();
        } else {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        }
    }
    private static Object getFieldValue(boolean parent, Object instance, String fieldName) throws ReflectiveOperationException {
        Class<?> clazz = instance.getClass();
        try {
            return getFieldValue(clazz, instance, fieldName);
        } catch (NoSuchFieldException e) {
            if(parent){
                return getFieldValue(clazz.getSuperclass(), instance, fieldName);
            } else {
                throw new NoSuchMethodException("can not find field named [" + fieldName + "] in either instance and its parent" );
            }
        }
    }
    public static Object getFieldValue(Object instance, String fieldName) throws ReflectiveOperationException {
        return getFieldValue(false, instance, fieldName);
    }
    public static Object getParentFieldValue(Object instance, String fieldName) throws ReflectiveOperationException {
        return getFieldValue(true, instance, fieldName);
    }


    /**
     * 反射赋值，暂时只提供两层
     */
    private static void setFieldValue(Class clazz, Object instance, String fieldName, Object fieldValue) throws ReflectiveOperationException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, fieldValue);
    }
    private static void setFieldValue(boolean parent, Object instance, String fieldName, Object fieldValue) throws ReflectiveOperationException {
        Class<?> clazz = instance.getClass();
        try {
            setFieldValue(clazz, instance, fieldName, fieldValue);
        } catch (NoSuchFieldException e) {
            if(parent){
                setFieldValue(clazz.getSuperclass(), instance, fieldName, fieldValue);
            } else {
                throw new NoSuchMethodException("can not find field named [" + fieldName + "] in either instance and its parent" );
            }
        }
    }
    public static void setFieldValue(Object instance, String fieldName, Object fieldValue) throws ReflectiveOperationException {
        setFieldValue(false, instance, fieldName, fieldValue);
    }
    public static void setParentFieldValue(Object instance, String fieldName, Object fieldValue) throws ReflectiveOperationException {
        setFieldValue(true, instance, fieldName, fieldValue);
    }


    /**
     * 判断字段是否存在
     */
    private static boolean containsField(boolean parent, Object instance, String fieldName) {
        return containsSuperField(parent ? 2 : 1, instance, fieldName);
    }
    public static boolean containsField(Object instance, String fieldName) {
        return containsField(false, instance, fieldName);
    }
    public static boolean containsParentField(Object instance, String fieldName) {
        return containsField(true, instance, fieldName);
    }
    private static boolean containsSuperField(int levelCount, Object instance, String fieldName) {
        Class<?> clazz = instance.getClass();
        for(int i=0; i<levelCount; i++){
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if(fieldName.equals(field.getName())){
                    return true;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }

    /**
     * 反射取字段，例如需要执行加密后赋值的逻辑
     */
    private static Field getField(boolean parent, Object instance, String fieldName) {
        return getSuperField(parent ? 2 : 1, instance, fieldName);
    }
    public static Field getField(Object instance, String fieldName) {
        return getField(false, instance, fieldName);
    }
    public static Field getParentField(Object instance, String fieldName) {
        return getField(true, instance, fieldName);
    }
    private static Field getSuperField(int levelCount, Object instance, String fieldName) {
        Class<?> clazz = instance.getClass();
        for(int i=0; i<levelCount; i++){
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if(fieldName.equals(field.getName())){
                    return field;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    /**
     * 执行方法
     */
    private static Object invokeMethod(boolean parent, Object instance, String methodName, Object... args) throws ReflectiveOperationException {
        Class<?> clazz = parent ? instance.getClass().getSuperclass() : instance.getClass();
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for(int i=0; i<args.length; i++){
            parameterTypes[i] = args[i].getClass();
        }
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(instance, args);
    }
    public static Object invokeMethod(Object instance, String methodName, Object... args) throws ReflectiveOperationException {
        return invokeMethod(false, instance, methodName, args);
    }
    public static Object invokeParentMethod(Object instance, String methodName, Object... args) throws ReflectiveOperationException {
        return invokeMethod(true, instance, methodName, args);
    }
    public static Object invokeSuperMethod(int levelCount, Object instance, String methodName, Object... args) throws ReflectiveOperationException {
        Method method = null;
        Class<?> clazz = instance.getClass();
        for(int i=0; i<levelCount; i++){
            Method[] methods = clazz.getDeclaredMethods();
            for(Method m : methods){
                if(methodName.equals(m.getName())){
                    method = m;
                    break;
                }
            }
            if(method != null){
                break;
            } else {
                clazz = clazz.getSuperclass();
            }
        }
        if(method == null){
            throw new NoSuchMethodException("can not find method named [" + methodName + "] in " + levelCount + "level of instance" );
        }

        method.setAccessible(true);
        return method.invoke(instance, args);
    }


    /**
     * 获取注解
     */
    public static Class getGenericType(Object instance) {
        ParameterizedType parameterizedType = (ParameterizedType) instance.getClass().getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        if(types.length > 0){
            return (Class) types[0];
        }
        return null;
    }

}
