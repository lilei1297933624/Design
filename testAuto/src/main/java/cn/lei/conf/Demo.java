package cn.lei.conf;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Demo {

    @Cacheable(cacheNames = "test")
    public String test1(String test){
        System.out.println("l am run");
        return "test1";
    }

    @CacheEvict("test")

    public String test2(String test){
        System.out.println("l am run2");
        return "test2";
    }

    @CachePut(cacheNames = "test",key = "#test = 3")
    public String test3(String test){
        System.out.println("l am run3");
        return "test3";
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.getClass().getMethod("add",Object.class).invoke(list,"a");
        for(int i = 0 ; i < list.size() ; i++){
            System.out.println(list.get(i));
        }

        List list1 = new ArrayList<String>();
        list1.add(1);
        list1.add("1");
    }
}
