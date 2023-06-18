package com.lyx.batch.Strategy;

public class DogComparator2 implements Comparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        return o2.food - o1.food;
    }
}
