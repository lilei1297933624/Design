package com.lyx.batch.observer;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private boolean cry = false;
    List<Observer> observers;

    public static void main(String[] args) {
        Child child = new Child();
        child.addObserver(new MomObserver());
        child.addObserver(new DadObserver());
        child.setCry(true);
        child.setCry(false);
    }

    public Child(){
        this.cry = false;
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer){
        this.observers.add(observer);
    }

    public void setCry(boolean cry){
        this.cry = cry;
        doObserver(cry);
    }

    public void doObserver(boolean cry){
        for(Observer observer : observers){
            observer.doObserver(cry);
        }
    }
}
