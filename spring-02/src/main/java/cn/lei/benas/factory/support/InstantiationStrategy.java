package cn.lei.benas.factory.support;

import cn.lei.benas.factory.BeansException;
import cn.lei.benas.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName,
                       Constructor constructor,Object[] args) throws BeansException;
}
