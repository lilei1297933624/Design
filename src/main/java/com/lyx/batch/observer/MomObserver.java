package com.lyx.batch.observer;

public class MomObserver implements Observer {
    @Override
    public void doObserver(boolean cry) {
        System.out.println("妈妈看到小孩在哭。。。"+cry);
    }
}
