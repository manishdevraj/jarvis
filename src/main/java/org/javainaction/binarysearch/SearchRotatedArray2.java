package org.javainaction.binarysearch;

/**
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * @see SearchRotatedArray
 */
public class SearchRotatedArray2 {
    public static boolean search(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        int n = arr.length;
        int left = 0; int right = n - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int potentialMatch = arr[middle];
            int leftValue = arr[left];
            int rightValue = arr[right];

            if (target == potentialMatch) return true;
            else if (leftValue <= potentialMatch) {
                if (leftValue <= target && target < potentialMatch) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (target <= rightValue && potentialMatch < target) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[] { 2,5,6,0,0,1,2 }, 0));
        System.out.println(search(new int[] { 2,5,6,0,0,1,2 }, 3));
        System.out.println(search(new int[] { 1, 0, 1, 1, 1 }, 0));
    }
}
