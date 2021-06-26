package org.javainaction.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a input array find the number of inversions in it
 * An inversion occurs for i when i < j and arr[i] > arr[j]
 * Input: {2, 3, 3, 1, 9, 5, 6}
 *
 * Output 5
 * Inversion occurred at [0,3], [1,3], [2,3], [4,5], [4,6]
 */
public class CountInversion {
    //O(nlogn) time | O(n) space
    public static int countInversions(int[] array) {
        return countSubArrayInversions(array, 0, array.length);
    }

    public static int countSubArrayInversions(int[] array, int start, int end) {
        if (end - start <= 1) {
            //we reached end of the array
            return 0;
        }

        int middle = start + (end - start) / 2;
        int leftInversions = countSubArrayInversions(array, start, middle);
        int rightInversions = countSubArrayInversions(array, middle, end);
        int mergedArrayInversions = mergeSortCountInversions(array, start, middle, end);
        return leftInversions + rightInversions + mergedArrayInversions;
    }

    public static int mergeSortCountInversions(int[] array, int start, int middle, int end) {
        List<Integer> sortedArray = new ArrayList<>();
        int left = start;
        int right = middle;
        int inversions = 0;

        while (left < middle && right < end) {
            if (array[left] <= array[right]) {
                sortedArray.add(array[left]);
                left++;
            } else {
                inversions += middle - left; //inversion
                sortedArray.add(array[right]);
                right++;
            }
        }

        for (int i = left; i < middle; i++) {
            sortedArray.add(array[i]);
        }

        for (int i = right; i < end; i++) {
            sortedArray.add(array[i]);
        }

        for (int i = 0; i < sortedArray.size(); i++) {
            int num = sortedArray.get(i);
            array[start + i] = num;
        }

        return inversions;
    }

    //Best: O(n) time | O(1) space
    //Average: O(n^2) time | O(1) space;
    //Worse: O(n^2) time | O(1) space;
    public static int countInversionsInsertionsort(int[] arr) {
        int k = 0;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i - 1; j >=0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];
                k++;//number of inversions
            }
            arr[j + 1] = temp;
        }
        return k;
    }

    public static void main(String[] arg) {
        System.out.println(CountInversion.countInversions(new int[]{2, 3, 3, 1, 9, 5, 6}));
        System.out.println(CountInversion.countInversionsInsertionsort(new int[]{2, 3, 3, 1, 9, 5, 6}));
    }
}
