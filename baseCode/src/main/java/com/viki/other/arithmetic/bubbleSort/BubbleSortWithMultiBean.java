package com.viki.other.arithmetic.bubbleSort;

import java.util.Arrays;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 17:34
 */
public class BubbleSortWithMultiBean {



    public static void main(String[] args) {
        SubClass[] arr = new SubClass[20];
        for (int x = 0; x < arr.length; x++) {
            arr[x] = new SubClass((int)(Math.random() * 100), "adf");
        }
        System.out.println("初始顺序:\t"+Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后:\t"+Arrays.toString(arr));
    }

    private static void sort(SubClass[] arr){
        SubClass temp;
        SubCompare subCompare = new SubCompare();
        for(int idx1 = 0; idx1 < arr.length; idx1++){
            for(int idx2 = idx1+1; idx2 < arr.length; idx2++){
                if(subCompare.compare(arr[idx1], arr[idx2]) > 0){
                    temp = arr[idx1];
                    arr[idx1] = arr[idx2];
                    arr[idx2] = temp;
                }
            }
        }
    }


}
