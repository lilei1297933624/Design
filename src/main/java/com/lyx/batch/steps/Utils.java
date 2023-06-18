package com.lyx.batch.steps;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Utils {

    public static <T> T newByCglib(Class<T> tClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            Object result = methodProxy.invokeSuper(o, objects);
            return result;
        });
        Object o = enhancer.create();
        return (T) o;
    }

    public static <T> T newByReflect(Class<T> tClass) {
        if (null == tClass) {
            return null;
        }
        T t = null;
        try {
            Constructor<T> declaredConstructor = tClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            t = declaredConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

//    public static <V> V copy(Object o) {
//        Class<?> oClass = o.getClass();
//        Type[] types = ((ParameterizedType) oClass.getGenericSuperclass()).getActualTypeArguments();
//        Class<V> vClass = null;
//        for (Type type : types) {
//            if (type != oClass) {
//                vClass = (Class<V>) type;
//            }
//        }
//        if (null == vClass) {
//            System.out.println("请添加目标类泛型泛型！");
//            return null;
//        }
//        BeanCopier beanCopier = BeanCopier.create(oClass, vClass, false);
//        V v = BeanUtil.newByCglib(vClass);
//        beanCopier.copy(o, v, null);
//
//        return v;
//    }
}
