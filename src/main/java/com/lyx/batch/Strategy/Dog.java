package com.lyx.batch.Strategy;

public class Dog implements Comparable<Dog>{
    int food;
    public Dog(int food){
        this.food = food;
    }
    @Override
    public int compareTo(Dog dog) {
        return this.food > dog.food ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
