package com.lyx.batch.config;

public class MyQueue<E> {
    private final Object[] data;
    private final int maxSize;
    private int size;
    private int front = 0;
    private int tail = 0;
    public MyQueue(int maxSize){
        if(maxSize <= 0){
            throw new IllegalArgumentException("queue size must bigger than 0");
        }
        this.maxSize = maxSize;
        data = new Object[this.maxSize];
    }

    public void add(E e){
        if(isFull()){
            throw new IllegalArgumentException("队列已满---");
        }
        data[tail++] = e;
        size++;
    }

    public E remove(){
        if(isEmpty()){
            throw new IllegalArgumentException("队列是空的，无法移除--");
        }
        E t = (E) data[front];
        data[front++] = null;
        size--;
        return t;
    }

    public boolean isEmpty(){
        return front == tail && !isFull();
    }

    public boolean isFull(){
        return tail == maxSize - 1;
    }

    public int getSize(){
        return size;
    }
}
