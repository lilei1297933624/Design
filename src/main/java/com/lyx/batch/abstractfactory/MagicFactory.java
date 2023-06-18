package com.lyx.batch.abstractfactory;

public class MagicFactory extends AbstractFactory {
    @Override
    Wuqi createWuqi() {
        return new  MagicStick();
    }

    @Override
    Food createFood() {
        return new Rice();
    }
}
