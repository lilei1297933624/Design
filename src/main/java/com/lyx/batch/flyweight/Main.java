package com.lyx.batch.flyweight;

/**
 * 享元模式
 */
public class Main {

    public static void main(String[] args) {
        //池化思想
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);
        System.out.println(s3.intern() == s1);
    }
}
