package com.lyx.batch.observer;

public class DadObserver implements Observer {
    @Override
    public void doObserver(boolean cry) {
        System.out.println("爸爸观察到了宝宝在哭。。。" + cry);
    }
}
