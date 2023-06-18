package cn.lei.annotation;

import cn.lei.conf.HelloWorldAutoConf;
import cn.lei.selector.HelloWorldSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldSelector.class)
public @interface EnableHelloWorld {
}
