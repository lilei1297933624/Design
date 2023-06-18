package cn.lei.benas.factory.config;

import cn.lei.benas.factory.BeansException;

public interface BeanPostProcessor {
    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName)
        throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName)
        throws BeansException;
}
