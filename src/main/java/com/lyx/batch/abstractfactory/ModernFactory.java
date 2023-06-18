package com.lyx.batch.abstractfactory;

public class ModernFactory extends AbstractFactory {

    @Override
    Wuqi createWuqi() {
        return new Ak47();
    }

    @Override
    Food createFood() {
        return new Bread();
    }
}
