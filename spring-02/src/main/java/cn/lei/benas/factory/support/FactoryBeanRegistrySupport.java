package cn.lei.benas.factory.support;

import cn.lei.benas.factory.BeansException;
import cn.lei.benas.factory.FactoryBean;
import cn.lei.benas.factory.config.DefaultSingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory,String beanName){
        if(factory.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if(object == null){
                object = doGetObjectFromFactoryBean(factory,beanName);
                this.factoryBeanObjectCache.put(beanName,(object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        }else {
            return doGetObjectFromFactoryBean(factory,beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory,final String beanNmae){

        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("创建Bean失败，beanName： "+ beanNmae,e);
        }
    }
}
