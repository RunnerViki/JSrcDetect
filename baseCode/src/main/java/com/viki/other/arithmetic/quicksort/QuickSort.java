package com.viki.other.arithmetic.quicksort;

import java.util.Arrays;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 17:51
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] arr = new Integer[20];
        for (int x = 0; x < arr.length; x++) {
            arr[x] = (int)(Math.random() * 100);
        }
        System.out.println("快速排序前:\t"+ Arrays.toString(arr));
        sort(arr);
        System.out.println("快速排序后:\t"+Arrays.toString(arr));
    }

    private static void sort(Integer[] arr){
        int point1 = 0, point2 = arr.length - 1;
        int temp;
        while (point1 < point2){
            if(arr[point1] > arr[point2]){
                temp = arr[point2];
                arr[point2] = arr[point1];
                arr[point1] = temp;
                point1++;
            }else{
                point2--;
            }
        }
    }
}
