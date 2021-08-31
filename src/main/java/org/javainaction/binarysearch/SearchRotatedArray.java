package org.javainaction.binarysearch;

/**
 * Search in Rotated Array (medium) #
 * Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number,
 * find if a given ‘key’ is present in it.
 *
 * Write a function to return the index of the ‘key’ in the rotated array. If the ‘key’ is not present, return -1.
 * You can assume that the given array does not have any duplicates.
 *
 * Example 1:
 *
 * Input: [10, 15, 1, 3, 8], key = 15
 * Output: 1
 * Explanation: '15' is present in the array at index '1'.
 *
 *  Original array: [1, 3, 8, 10, 15]
 *  Array after 2 rotations: [10, 15, 1, 3, 8]
 *
 * Example 2:
 *
 * Input: [4, 5, 7, 9, 10, -1, 2], key = 10
 * Output: 4
 * Explanation: '10' is present in the array at index '4'.
 * @see ShiftedBinarySearch
 */
public class SearchRotatedArray {
    public static int search(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        int n = arr.length;
        int left = 0; int right = n - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            int potentialMatch = arr[middle];
            int leftValue = arr[left];
            int rightValue = arr[right];

            if (key == arr[middle]) {
                return middle;
            //if we are in ascending array
            } else if (leftValue <= potentialMatch) {
                //check bounds and use standard binary search
                //move right if key is withing ascending array
                if (leftValue <= key  && key < potentialMatch) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                //we are in descending array
                //check bounds and move left if key is within descending array or else right
                if (potentialMatch < key  && key <= rightValue) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[] { 10, 15, 1, 3, 8 }, 15));
        System.out.println(search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
    }
}
