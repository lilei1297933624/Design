package com.lyx.batch.factory;

public class VehicleFactory {

    public Car createCar(){
        return new Car();
    }

    public Plane createPlane(){
        return new Plane();
    }
}
