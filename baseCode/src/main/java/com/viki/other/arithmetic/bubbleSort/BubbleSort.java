package com.viki.other.arithmetic.bubbleSort;

import java.util.Arrays;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 17:34
 */
public class BubbleSort {



    public static void main(String[] args) {
        Integer[] arr = new Integer[20];
        for (int x = 0; x < arr.length; x++) {
            arr[x] = (int)(Math.random() * 100);
        }
        System.out.println("初始顺序:\t"+Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后:\t"+Arrays.toString(arr));
    }

    private static void sort(Integer[] arr){
        int temp;
        for(int idx1 = 0; idx1 < arr.length; idx1++){
            for(int idx2 = idx1+1; idx2 < arr.length; idx2++){
                if(arr[idx1] < arr[idx2]){
                    temp = arr[idx1];
                    arr[idx1] = arr[idx2];
                    arr[idx2] = temp;
                }
            }
        }
    }
}
