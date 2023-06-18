package com.lyx.batch.observer.v2;

import java.util.ArrayList;
import java.util.List;


/**
 * 观察者模式
 * 1、Observer
 * 2、Listener
 * 3、Hook
 * 4、Callback
 */
public class Test {

}

class Button{
    private List<ActionListener> listeners = new ArrayList<>();

    void buttonPressed(){
        ActionEvent e = new ActionEvent(System.currentTimeMillis(),this);
        for(int i = 0; i < listeners.size(); i++){
            listeners.get(i).actionPerformed(e);
        }
    }

    public static void main(String[] args) {
        Button button = new Button();
        button.addActionListener(new ActionListener1());
        button.addActionListener(new ActionListener1());
        button.buttonPressed();
    }

    void addActionListener(ActionListener l){
        listeners.add(l);
    }
}

class ActionEvent {
    long when;
    Object source;

    public ActionEvent(long when, Object source) {
        this.when = when;
        this.source = source;
    }
}

interface ActionListener {
    void actionPerformed(ActionEvent event);
}

class ActionListener1 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("button pressed");
    }
}
