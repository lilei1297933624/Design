package com.lyx.batch.proxy.v01;


import java.util.Random;

interface Movable {
    void move();
}

/**
 * 代理模式
 * 1、如何记录方法的执行时间
 * 用继承的方式，会有多种继承类
 * 2、用代理模式
 */
public class Tamk implements Movable {

    @Override
    public void move()   {
        long l = System.currentTimeMillis();
        System.out.println("Tank moving claclacla...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-l);
    }

    public static void main(String[] args) {
        new Tamk().move();
        new TankTimeProxy(new Tamk()).move();
    }
}

class TankTimeProxy implements Movable{
    Tamk tamk;
    public TankTimeProxy(Tamk tamk){
        this.tamk = tamk;
    }

    @Override
    public void move() {
        long l = System.currentTimeMillis();
        tamk.move();
        System.out.println(System.currentTimeMillis()-l);
    }
}
