package com.lyx.batch.proxy.v02;


import com.lyx.batch.factory.MoveAble;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new TankLogProxy(new TankTimeProxy(new Tamk())).move();
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Movable o = (Movable) Proxy.newProxyInstance(Tamk.class.getClassLoader(), Tamk.class.getInterfaces(),
                new LogHandler(new Tamk()));
        o.move();

    }
}

class LogHandler implements InvocationHandler {
    Tamk tamk;
    public LogHandler(Tamk tamk){
        this.tamk = tamk;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method " + method.getName() + " start..");
        Object invoke = method.invoke(tamk, args);
        System.out.println("method " + method.getName() + " end..");
        return invoke;
    }
}

class TankTimeProxy implements Movable{
    Movable tamk;
    public TankTimeProxy(Movable tamk){
        this.tamk = tamk;
    }

    @Override
    public void move() {
        long l = System.currentTimeMillis();
        tamk.move();
        System.out.println(System.currentTimeMillis()-l);
    }
}

class TankLogProxy implements Movable{
    Movable tamk;
    public TankLogProxy(Movable tamk){
        this.tamk = tamk;
    }

    @Override
    public void move() {
        System.out.println("start");
        tamk.move();
        System.out.println("stop");
    }
}
