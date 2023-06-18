package com.lyx.batch.config;

public class MyStack<E> {
    private Object[] data;
    private int size;
    public MyStack(int capacity){
        if(capacity <= 0){

        }
        data = new Object[capacity];
    }

    public void push(E item){
        if(isFull()){

        }
        data[size++] = item;
    }

    public E pop(){
        if(isEmpty()){

        }
        E temp = (E) data[--size];
        data[size] = null;
        return temp;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean isFull(){
        return size() == data.length;
    }

    public int size(){
        return size;
    }
}
