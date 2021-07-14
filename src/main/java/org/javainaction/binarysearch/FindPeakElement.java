package org.javainaction.binarysearch;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
 * return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5
 * where the peak element is 6.
 */
public class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            //if middle > middle + 1 the we are descending side of the peak so move right to middle as we might still
            //have peak at middle - 1
            //or else move left = middle + 1
            //right inclusive as we might still have peak at middle
            if (nums[middle] > nums[middle + 1]) right = middle;
            else left = middle + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[] {1,2,3,1}));
        System.out.println(findPeakElement(new int[] { 1,2,1,3,5,6,4}));
        System.out.println(findPeakElement(new int[] { 7, 9, 8 }));
        System.out.println(findPeakElement(new int[] { 1, 5, 2 }));
    }
}
