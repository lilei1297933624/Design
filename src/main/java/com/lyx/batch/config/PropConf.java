package com.lyx.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource("classpath:my.properties")
@ConfigurationProperties(prefix = "aaa")
public class PropConf {

    private String a;
    private String b;
    private String c;
    private Map<String,Person> persons;

    public String getA() {return a;}
    public void setA(String a) {this.a = a;}
    public String getB() {return b;}
    public void setB(String b) {this.b = b;}
    public String getC() {return c;}
    public void setC(String c) {this.c = c;}

    public Map<String, Person> getPersons() {
        return persons;
    }

    public void setPersons(Map<String, Person> persons) {
        this.persons = persons;
    }

    public void show(){
        System.out.println("a --- > " + a);
        System.out.println("b --- > " + b);
        System.out.println("c --- > " + c);
        System.out.println(persons);
    }
}
