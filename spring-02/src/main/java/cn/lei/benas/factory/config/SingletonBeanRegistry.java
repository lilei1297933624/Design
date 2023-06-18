package cn.lei.benas.factory.config;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
