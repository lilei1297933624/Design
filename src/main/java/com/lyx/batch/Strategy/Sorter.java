package com.lyx.batch.Strategy;

import java.util.Arrays;

public class Sorter {

    public static void main(String[] args) {
        Dog[] dogs = {new Dog(1),new Dog(3),new Dog(2)};
        sort(dogs);
        System.out.println(Arrays.toString(dogs));
    }

    public static void sort(Comparable[] arr){
        for(int i = 0 ; i < arr.length-1; i++){
            int minPos = i;
            for(int j = i+1 ; j < arr.length ; j++){
                minPos = arr[j].compareTo(arr[minPos]) > 0 ? minPos : j;
            }
            swap(arr,minPos,i);
        }
    }

    static void swap(Comparable[] arr, int i , int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
