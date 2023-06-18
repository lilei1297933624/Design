package com.lyx.batch.observer.v2;

import com.lyx.batch.observer.MomObserver;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class Child {
    boolean cry = false;
    List<Observer> observers = new ArrayList<>();
    {
        observers.add(new DadObserver());
    }

    public static void main(String[] args) {

        Child child = new Child();
        EventObject eventObject = new EventObject(child);

        child.wakeUp();
    }

    public void wakeUp(){
        WakeUpEvent 床上 = new WakeUpEvent(System.currentTimeMillis(), "床上");
        for(Observer observer : observers){
            observer.doAction(床上);
        }
    }
}

class WakeUpEvent{
    long timestamp;
    String loc;

    public WakeUpEvent(long timestamp, String loc) {
        this.timestamp = timestamp;
        this.loc = loc;
    }
}

interface Observer{
    void doAction(WakeUpEvent event);
}

class DadObserver implements Observer{

    @Override
    public void doAction(WakeUpEvent event) {
        System.out.println("dad guancha "+ event.loc);
    }
}
