package com.lyx.batch.abstractfactory;

public class Main {
    public static void main(String[] args) {
        Bread bread = new Bread();
        bread.printName();
        Ak47 ak47 = new Ak47();
        ak47.shot();
    }
}
