package cn.lei.test.common;

import cn.lei.benas.PropertyValue;
import cn.lei.benas.PropertyValues;
import cn.lei.benas.factory.BeansException;
import cn.lei.benas.factory.ConfigurableListableBeanFactory;
import cn.lei.benas.factory.config.BeanDefinition;
import cn.lei.benas.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","" +
                "改为： 字节跳动"));
    }
}
