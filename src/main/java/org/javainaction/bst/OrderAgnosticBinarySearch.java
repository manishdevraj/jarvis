package org.javainaction.bst;

/**
 * Given a sorted array of numbers, find if a given number ‘key’ is present in the array.
 * Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
 * You should assume that the array can have duplicates.
 *
 * Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.
 *
 * Example 1:
 *
 * Input: [4, 6, 10], key = 10
 * Output: 2
 * Example 2:
 *
 * Input: [1, 2, 3, 4, 5, 6, 7], key = 5
 * Output: 4
 * Example 3:
 *
 * Input: [10, 6, 4], key = 10
 * Output: 0
 * Example 4:
 *
 * Input: [10, 6, 4], key = 4
 * Output: 2
 */
public class OrderAgnosticBinarySearch {
    public static int search(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        return binarySearch(arr, key);
    }

    public static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        boolean isAscending = arr[left] < arr[right];
        while (left <= right) {
            if (left > right) return -1;
            int middle = (left + right) / 2;
            if (arr[middle] == key) return middle;
            else {
                if (isAscending && key < arr[middle]) {
                    right = middle - 1;
                } else if (isAscending && key > arr[middle]) {
                    left = middle + 1;
                } else if (!isAscending && key > arr[middle]) {
                    right = middle - 1;
                } else if (!isAscending && key < arr[middle]) {
                    left = middle + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[] { 4, 6, 10 }, 10));
        System.out.println(search(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        System.out.println(search(new int[] { 10, 6, 4 }, 10));
        System.out.println(search(new int[] { 10, 6, 4 }, 4));
    }
}
