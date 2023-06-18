package cn.lei.springframework.context;

import cn.lei.benas.factory.Aware;
import cn.lei.benas.factory.BeansException;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException;
}
