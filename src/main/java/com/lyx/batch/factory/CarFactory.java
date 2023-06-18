package com.lyx.batch.factory;

public class CarFactory {
    public Car createCar(){
        return new Car();
    }

    public static void main(String[] args) {
       MoveAble  car = new CarFactory().createCar();
       car.go();
       Car car1 = (Car) car;
       car1.b();
    }
}
