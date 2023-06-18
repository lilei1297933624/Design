package cn.lei;

import cn.lei.conf.Demo;
import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableCaching
public class App
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        ConfigurableApplicationContext run =
                SpringApplication.run(App.class,args);
        Demo bean = run.getBean(Demo.class);
        while (true){
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNext()){
                String next = scanner.next();
                if("1".equals(next)){
                    bean.test1("demo");
                }
                if("2".equals(next)){
                    bean.test2("demo");
                }
                if("3".equals(next)){
                    bean.test3("demo");
                }
            }
        }
    }
}
