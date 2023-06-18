package com.lyx.batch.lterator.v1;

public class Main {
    public static void main(String[] args) {
        Collection_<String> arrayList_ = new ArrayList_<String>();
        for (int i = 0; i < 15; i++) {
            String s = "a"+i;
            arrayList_.add(s);
        }
        Iterator_<String> iterator = arrayList_.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(arrayList_.size());
    }
}

interface Collection_<E> {
    void add(E o);

    int size();

    Iterator_ iterator();
}

class ArrayList_<E> implements Collection_<E> {
    E[] objects = (E[]) new Object[10];
    private int index = 0;

    public void add(E o) {
        if (index == objects.length) {
            E[] newObjects = (E[]) new Object[this.objects.length * 2];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            objects = newObjects;
        }
        objects[index] = o;
        index++;
    }

    public int size() {
        return objects.length;
    }

    @Override
    public Iterator_<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator<E> implements Iterator_<E> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return !(currentIndex >= index);
        }

        @Override
        public E next() {
            return (E) objects[currentIndex++];
        }
    }
}

class LinkedList_<E> implements Collection_<E> {
    Node head = null;
    Node tail = null;
    private int size = 0;

    public void add(E o) {
        Node n = new Node(o);
        n.next = null;

        if (head == null) {
            head = n;
            tail = n;
        }
        tail.next = n;
        tail = n;
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator_ iterator() {
        return null;
    }

    private class LinkedListIter<E> implements Iterator_<E>{
        Node curr = head;
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            return (E) (curr = curr.next);
        }
    }

    private class Node<E> {
        private E o;
        Node next;

        public Node(E o) {
            this.o = o;
        }
    }

}
interface Iterator_<E> {
    boolean hasNext();

    E next();
}


