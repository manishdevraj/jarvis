package org.javainaction.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a function that will sort an array in wave format.
 *
 * a <= b >= c OR a >= b <= c
 *
 * Input: [1, 2, 3, 4, 5]
 * Output: [1, 3, 2, 5, 4] or [3, 1, 4, 2, 5]
 *
 * Input: [1, 1, 2, 3]
 * Output: [1, 1, 3, 2]
 *
 * This can be done in many way but try to find solution in O(n) time complexity
 */
public class WaveSort {

    //O(n) time | O(1) space
    private static int[] waveSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i += 2) {
            if (i > 0 && arr[i] < arr[i - 1]) {
                swap(arr, i - 1, i);
            }

            if (i < arr.length - 1 && arr[i] < arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //O(nlogn) time | O(n) space
    private static int[] waveSort2(int[] arr) {
        if (arr  == null || arr.length <= 2) return arr;
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        List<Integer> output = new ArrayList<>();
        while (left <= right) {
            if (arr[left] == arr[right]) output.add(arr[left++]);
            else {
                output.add(arr[left++]);
                output.add(arr[right--]);
            }
        }
        return output.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] arg) {
        System.out.println("[1, 2, 3, 4, 5] wave sorted " + Arrays.toString(waveSort(new int[]{1, 2, 3, 4, 5})));
        System.out.println("[1, 2, 3, 4, 5] wave sorted " + Arrays.toString(waveSort2(new int[]{1, 2, 3, 4, 5})));

        System.out.println("[1, 1, 2, 3] wave sorted " + Arrays.toString(waveSort(new int[]{1, 1, 2, 3})));
        System.out.println("[1, 2, 3, 4, 5] wave sorted " + Arrays.toString(waveSort2(new int[]{1, 2, 3, 4, 5})));
    }


}
