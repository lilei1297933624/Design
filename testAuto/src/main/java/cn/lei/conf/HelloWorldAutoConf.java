package cn.lei.conf;

import cn.lei.annotation.EnableHelloWorld;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHelloWorld
@ConditionalOnProperty(name = "helloworld",havingValue = "true")
public class HelloWorldAutoConf {

    public static void main(String[] args) {
        String s = "我是预警，哈哈";
        System.out.println(s.contains("预警啊"));
    }
}
