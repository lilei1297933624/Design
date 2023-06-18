package cn.lei.benas.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.lei.benas.factory.BeansException;
import cn.lei.benas.factory.DisposableBean;
import cn.lei.benas.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition){
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        if(StrUtil.isNotEmpty(destroyMethodName) &&!(bean instanceof DisposableBean
        && "destroy".equals(this.destroyMethodName))){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(null == destroyMethod){
                throw new BeansException("couldnt find a destroy method named '" +
                        destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
