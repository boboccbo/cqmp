package com.airchina.cqmp.core.utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;


/**
 * User: 梅海波
 * Date: 2016/11/9.
 * Time: 14:01
 * 说明：
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
    protected static final Log logger = LogFactory.getLog(BeanUtils.class);

    private BeanUtils() {
    }

    public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        return getDeclaredField(object.getClass(), propertyName);
    }

    public static Field getDeclaredField(Class clazz, String propertyName) throws NoSuchFieldException {
        Assert.notNull(clazz);
        Assert.hasText(propertyName);
        Class superClass = clazz;

        while(superClass != Object.class) {
            try {
                return superClass.getDeclaredField(propertyName);
            } catch (NoSuchFieldException var4) {
                superClass = superClass.getSuperclass();
            }
        }

        throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
    }

    public static Object forceGetProperty(Object object, String propertyName) throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        Field field = getDeclaredField(object, propertyName);
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        Object result = null;

        try {
            result = field.get(object);
        } catch (IllegalAccessException var6) {
            logger.info("error wont\' happen");
        }

        field.setAccessible(accessible);
        return result;
    }

    public static void forceSetProperty(Object object, String propertyName, Object newValue) throws NoSuchFieldException {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        Field field = getDeclaredField(object, propertyName);
        boolean accessible = field.isAccessible();
        field.setAccessible(true);

        try {
            field.set(object, newValue);
        } catch (IllegalAccessException var6) {
            logger.info("Error won\'t happen");
        }

        field.setAccessible(accessible);
    }

    public static Object invokePrivateMethod(Object object, String methodName, Object... params) throws NoSuchMethodException {
        Assert.notNull(object);
        Assert.hasText(methodName);
        Class[] types = new Class[params.length];

        for(int clazz = 0; clazz < params.length; ++clazz) {
            types[clazz] = params[clazz].getClass();
        }

        Class var11 = object.getClass();
        Method method = null;
        Class accessible = var11;

        while(accessible != Object.class) {
            try {
                method = accessible.getDeclaredMethod(methodName, types);
                break;
            } catch (NoSuchMethodException var10) {
                accessible = accessible.getSuperclass();
            }
        }

        if(method == null) {
            throw new NoSuchMethodException("No Such Method:" + var11.getSimpleName() + methodName);
        } else {
            boolean var12 = method.isAccessible();
            method.setAccessible(true);
            Object result = null;

            try {
                result = method.invoke(object, params);
            } catch (Exception var9) {
                ReflectionUtils.handleReflectionException(var9);
            }

            method.setAccessible(var12);
            return result;
        }
    }

    public static List<Field> getFieldsByType(Object object, Class type) {
        ArrayList list = new ArrayList();
        Field[] fields = object.getClass().getDeclaredFields();
        Field[] var7 = fields;
        int var6 = fields.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            Field field = var7[var5];
            if(field.getType().isAssignableFrom(type)) {
                list.add(field);
            }
        }

        return list;
    }

    public static Class getPropertyType(Class type, String name) throws NoSuchFieldException {
        return getDeclaredField(type, name).getType();
    }

    public static String getGetterName(Class type, String fieldName) {
        Assert.notNull(type, "Type required");
        Assert.hasText(fieldName, "FieldName required");
        return type.getName().equals("boolean")?"is" + StringUtils.capitalize(fieldName):"get" + StringUtils.capitalize(fieldName);
    }

    public static Method getGetterMethod(Class type, String fieldName) {
        try {
            return type.getMethod(getGetterName(type, fieldName), new Class[0]);
        } catch (NoSuchMethodException var3) {
            logger.error(var3.getMessage(), var3);
            return null;
        }
    }

    public static Object invoke(String className, String methodName, Class[] argsClass, Object[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = Class.forName(className);
        Method method = cl.getMethod(methodName, argsClass);
        return method.invoke(cl.newInstance(), args);
    }

    public static Object invoke(Object oldObject, String methodName, Class[] argsClass, Object[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Class cl = oldObject.getClass();
        Method method = cl.getMethod(methodName, argsClass);
        return method.invoke(oldObject, args);
    }

    public static String[] getFieldsName(Class cl) throws Exception {
        Field[] fields = cl.getDeclaredFields();
        String[] fieldNames = new String[fields.length];

        for(int i = 0; i < fields.length; ++i) {
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }

    public static List<String> getAllFieldName(Class cl) {
        ArrayList list = new ArrayList();
        Field[] fields = cl.getDeclaredFields();

        for(int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            if(!field.getName().equals("serialVersionUID")) {
                list.add(field.getName());
            }
        }

        while(true) {
            cl = cl.getSuperclass();
            if(cl == Object.class) {
                return list;
            }

            list.addAll(getAllFieldName(cl));
        }
    }

    public static List<Method> getSetter(Class cl) {
        ArrayList list = new ArrayList();
        Method[] methods = cl.getDeclaredMethods();

        for(int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            String methodName = method.getName();
            if(methodName.startsWith("set")) {
                list.add(method);
            }
        }

        while(true) {
            cl = cl.getSuperclass();
            if(cl == Object.class) {
                return list;
            }

            list.addAll(getSetter(cl));
        }
    }

    public static List<Method> getGetter(Class cl) {
        ArrayList list = new ArrayList();
        Method[] methods = cl.getDeclaredMethods();

        for(int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            String methodName = method.getName();
            if(methodName.startsWith("get") || methodName.startsWith("is")) {
                list.add(method);
            }
        }

        while(true) {
            cl = cl.getSuperclass();
            if(cl == Object.class) {
                return list;
            }

            list.addAll(getGetter(cl));
        }
    }

    public static String getClassNameWithoutPackage(Class cl) {
        String className = cl.getName();
        int pos = className.lastIndexOf(46) + 1;
        if(pos == -1) {
            pos = 0;
        }

        String name = className.substring(pos);
        return name;
    }

    public static String beanToString(Object obj) {
        return ToStringBuilder.reflectionToString(obj);
    }
}
