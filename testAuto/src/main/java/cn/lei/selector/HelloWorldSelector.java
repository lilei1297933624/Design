package cn.lei.selector;

import cn.lei.conf.HelloWorldAutoConf;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloWorldSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldAutoConf.class.getName()};
    }
}
