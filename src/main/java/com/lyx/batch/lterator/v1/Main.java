package com.lyx.batch.lterator.v1;

import jdk.nashorn.internal.ir.CallNode;

public class Main {
    public static void main(String[] args) {
        Collection_ arrayList_ = new ArrayList_();
        for(int i = 0 ; i < 15 ; i++){
            String s = new String("a" + i);
            arrayList_.add(s);
        }
        System.out.println(arrayList_.size());
    }
}

interface Collection_ {
    void add(Object o);
    int size();
}

class ArrayList_ implements Collection_{
    Object[] objects = new Object[10];
    private int index = 0;
    public void add(Object o){
        if(index == objects.length){
            Object[] newObjects = new Object[this.objects.length * 2];
            System.arraycopy(objects,0,newObjects,0,objects.length);
            objects = newObjects;
        }
        objects[index++] = o;
    }
    public int size(){
        return objects.length;
    }
}

class LinkedList_ implements Collection_{
    Node head = null;
    Node tail = null;
    private int size = 0;

    public void add(Object o){
        Node n = new Node(o);
        n.next = null;

        if(head == null){
            head = n;
            tail = n;
        }
        tail.next = n;
        tail = n;
        size++;
    }

    public int size(){
        return size;
    }





    private class Node {
        private Object o;
        Node next;

        public Node(Object o) {
            this.o = o;
        }
    }



}


