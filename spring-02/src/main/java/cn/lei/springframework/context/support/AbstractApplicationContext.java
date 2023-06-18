package cn.lei.springframework.context.support;

import cn.lei.benas.factory.BeansException;
import cn.lei.benas.factory.ConfigurableListableBeanFactory;
import cn.lei.benas.factory.config.BeanFactoryPostProcessor;
import cn.lei.benas.factory.config.BeanPostProcessor;
import cn.lei.springframework.context.ConfigurableApplicationContext;
import cn.lei.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader
implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException{
        //1、创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        //2、获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //3、在Bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //4、BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //5、提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public void registerShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));;
    }

    @Override
    public void close(){
        getBeanFactory().destroySingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =
                beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }

    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.
                getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException{
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames(){
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException{
        return getBeanFactory().getBean(name);
    }
    @Override
    public Object getBean(String name,Object... args) throws BeansException{
        return getBeanFactory().getBean(name,args);
    }
    @Override
    public <T> T getBean(String name,Class<T> requiredType) throws BeansException{
        return getBeanFactory().getBean(name,requiredType);
    }
}
