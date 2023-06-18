package cn.lei.benas.factory;

import cn.lei.benas.factory.config.AutowireCapableBeanFactory;
import cn.lei.benas.factory.config.BeanDefinition;
import cn.lei.benas.factory.config.BeanPostProcessor;
import cn.lei.benas.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
