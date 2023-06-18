package com.lyx.batch.config;

import java.util.Arrays;
import java.util.Comparator;

public class MyHeap<E> {

    private Object[] data;
    private int size;
    private Comparator<? super E> comparator;

    public MyHeap(int initialCapacity,Comparator<? super E> comparator){
        if(initialCapacity < 1){
            throw new IllegalArgumentException("堆的大小必须大于0");
        }
        this.data = new Object[initialCapacity];
        this.comparator = comparator;
    }

    public MyHeap(int initialCapacity){
        this(initialCapacity,null);
    }


    private void siftUp(E e){
        int s = size;
        while (s > 0){
            int parent = (s-1) >>> 1;
            Object pData = data[parent];
            if(comparator != null){
                if(comparator.compare(e,(E)pData) >= 0){
                    break;
                }
            }else {
                if(((Comparable<? super E>)e).compareTo((E)pData) >= 0){
                    break;
                }
            }
            data[s] = pData;
            s = parent;
        }
        data[s] = e;
    }

    public void siftDown(E e){
        int half = size >>> 1;
        int index = 0;
        while (index < half){
            int min = (index << 1) + 1;
            Object minChild = data[min];
            int right = min + 1;
            if(right < size){
                if(comparator != null){
                    if(comparator.compare((E)minChild,(E)data[right]) > 0){
                        minChild = data[min = right];
                    }
                }
            }
            if(comparator != null){
                if(comparator.compare(e,(E)minChild) <= 0){
                    break;
                }
            }
            data[index] = minChild;
            index = min;
        }
        data[index] = e;
    }

    public boolean add(E e){
        if(e == null){
            throw new NullPointerException();
        }
        if(size >= data.length){
            data = Arrays.copyOf(data,data.length << 1);
        }
        if(size == 0)
            data[0] = e;
        else
            siftUp(e);
        size++;
        return true;
    }

    public int getSize(){
        return size;
    }

    public E remove(){
        if(size == 0)
            return null;
        size--;
        E result = (E) data[0];
        E x = (E)data[size];
        data[size] = null;
        if(size != 0){
            siftDown(x);
        }
        return result;
    }

    public E peek(){
        return (size == 0) ? null : (E)data[0];
    }
}
