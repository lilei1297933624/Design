package cn.lei.test;

import cn.lei.annotation.Test2;

@Test2("验证注解本质")
public class AnnotionTest {

    public static void main(String[] args) {
        Test2 test2 = AnnotionTest.class.getDeclaredAnnotation(Test2.class);
        System.out.println(test2.value());
    }
}
