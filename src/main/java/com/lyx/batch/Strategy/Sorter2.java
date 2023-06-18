package com.lyx.batch.Strategy;

import java.util.Arrays;

public class Sorter2<T> {

    public static void main(String[] args) {
        Dog[] dogs = {new Dog(1),new Dog(3),new Dog(2),new Dog(-1)};
        Sorter2<Dog> sorter2 = new Sorter2<>();
        // 策略模式，传比较器，自定义排序规则。Comparable只能有一种排序规则
        sorter2.sort(dogs,new DogComparator2());
        System.out.println(Arrays.toString(dogs));
    }

    public void sort(T[] arr, Comparator<T> comparator){
        for(int i = 0 ; i < arr.length-1; i++){
            int minPos = i;
            for(int j = i+1 ; j < arr.length ; j++){
                minPos = comparator.compare(arr[j],arr[minPos]) > 0 ? minPos : j;
            }
            swap(arr,minPos,i);
        }
    }

    void swap(T[] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
