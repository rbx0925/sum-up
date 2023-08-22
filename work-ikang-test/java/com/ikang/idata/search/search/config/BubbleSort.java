package com.ikang.idata.search.search.config;

/**
 * 冒泡排序
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2023年05月08日 下午 6:34
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 3, 9, 1};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}

