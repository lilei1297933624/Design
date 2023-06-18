package cn.lei.benas.factory.config;

import cn.lei.benas.factory.BeanFactory;
import cn.lei.benas.factory.BeansException;

public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行BeanPostProcessors接口实现类的postProcessBeforeInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessors接口实现类的postProcessorsAfterInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
   Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;
}
