package cn.lei.benas.factory.support;

import cn.lei.benas.factory.BeanFactory;
import cn.lei.benas.factory.BeansException;
import cn.lei.benas.factory.FactoryBean;
import cn.lei.benas.factory.config.BeanDefinition;
import cn.lei.benas.factory.config.BeanPostProcessor;
import cn.lei.benas.factory.config.ConfigurableBeanFactory;
import cn.lei.benas.factory.config.DefaultSingletonBeanRegistry;
import cn.lei.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements
        ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name,Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null){
            return (T) getObjectForBeanInstance(bean,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean2 = createBean(name,beanDefinition,args);
        return (T) getObjectForBeanInstance(bean2,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance,String beanName){
        if(!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);

        if(object == null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean,beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader(){
        return this.beanClassLoader;
    }
}
