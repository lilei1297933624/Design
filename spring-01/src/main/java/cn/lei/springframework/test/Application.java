package cn.lei.springframework.test;

import java.math.BigDecimal;

public class Application {

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(100);
        BigDecimal b2 = new BigDecimal(56);
       // System.out.println(b1.divide(b2));
        System.out.println(b1.multiply(new BigDecimal(100)).divide(b2,2,BigDecimal.ROUND_HALF_UP));
    }
}
