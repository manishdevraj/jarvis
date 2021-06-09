package org.javainaction.binarysearch;

/**
 * Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference
 * with the given ‘key’.
 *
 * Example 1:
 *
 * Input: [4, 6, 10], key = 7
 * Output: 6
 * Explanation: The difference between the key '7' and '6' is minimum than any other number in the array
 * Example 2:
 *
 * Input: [4, 6, 10], key = 4
 * Output: 4
 * Example 3:
 *
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 10
 * Example 4:
 *
 * Input: [4, 6, 10], key = 17
 * Output: 10
 */
public class MinimumDifference {
    public static int searchMinDiffElement(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        int n = arr.length;
        if (key < arr[0]) return arr[0];
        if (key > arr[n - 1]) return arr[n - 1];

        int left = 0; int right = n - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (key == arr[middle]) return arr[middle];
            else if (key < arr[middle]) right = middle - 1;
            else if (key > arr[middle]) left = middle + 1;
        }
        if ((arr[left] - key) < (arr[right] - key)) return arr[left];
        return arr[right];
    }

    public static void main(String[] args) {
        System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 7));
        System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 4));
        System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 17));
    }
}
