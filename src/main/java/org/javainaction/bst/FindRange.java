package org.javainaction.bst;

/**
 * Given an array of numbers sorted in ascending order, find the range of a given number ‘key’.
 * The range of the ‘key’ will be the first and last position of the ‘key’ in the array.
 *
 * Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1].
 *
 * Example 1:
 *
 * Input: [4, 6, 6, 6, 9], key = 6
 * Output: [1, 3]
 * Example 2:
 *
 * Input: [1, 3, 8, 10, 15], key = 10
 * Output: [3, 3]
 * Example 3:
 *
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: [-1, -1]
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a
 * given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class FindRange {
    public static int[] findRange(int[] arr, int key) {
        int[] result = new int[] { -1, -1 };
        if (arr == null || arr.length == 0) return result;
        result[0] = binarySearchRange(arr, key, true);
        result[1] = binarySearchRange(arr, key, false);
        return result;
    }

    public static int binarySearchRange(int[] arr, int key, boolean isLeft) {
        int left = 0;
        int right = arr.length - 1;
        int keyIndex = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (key < arr[middle]) {
                right = middle - 1;
            } else if (key > arr[middle]) {
                left = middle + 1;
            } else {
                keyIndex = middle;
                if (isLeft) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }
        return keyIndex;
    }

    public static void main(String[] args) {
        int[] result = FindRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }

}
