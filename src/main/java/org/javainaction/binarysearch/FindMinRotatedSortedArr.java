package org.javainaction.binarysearch;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example,
 * the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 *
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 */
public class FindMinRotatedSortedArr {
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return arr[0];
        int left = 0; int right = arr.length - 1;

        //we have sorted array without any rotation
        //also because of this base check we do not need to worry about
        // middle + 1 or middle - 1 going beyond bounds
        if (arr[left] < arr[right]) return arr[left];

        while (left <= right) {
            int middle = left + (right - left) / 2;
            //we have two rotation scenarios either middle itself is rotation [4, 5, 1, 2, 3]
            if (arr[middle] > arr[middle + 1]) {
                return arr[middle + 1];
            }
            //or middle - 1 is rotation [ 6, 5, 1, 2, 3, 4 ]
            if (arr[middle - 1] > arr[middle]) {
                return arr[middle];
            }

            //we need to check which side we need to shift
            if (arr[left] < arr[middle]) left = middle + 1;
            else right = middle - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] { 3, 4, 5, 1, 2 }));
        System.out.println(findMin(new int[] { 11, 13, 15, 17}));
        System.out.println(findMin(new int[] { 4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[] { 3, 4, 5, 6, 1 }));
        System.out.println(findMin(new int[] { 6, 5, 1, 2, 3, 4 }));
    }
}
