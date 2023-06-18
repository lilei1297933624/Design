package com.lyx.batch.design;

public class ThreadLocalDemo {

    private static ThreadLocal<String> local = new ThreadLocal<>();

    static void print(String str){
        System.out.println(str + " :"+local.get());
        local.remove();
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            ThreadLocalDemo.local.set("localA");
            print("A");
            System.out.println("after remove : " + local.get());
        },"A").start();

        Thread.sleep(1000);

        new Thread(()->{
            ThreadLocalDemo.local.set("localB");
            print("B");
            System.out.println("after remove : " + local.get());
        },"B").start();

    }
}
