package cn.lei.benas.factory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();
}
