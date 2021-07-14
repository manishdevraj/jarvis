package org.javainaction.slidingwindow;

import java.util.Arrays;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you
 * can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * @see LongestSubarrayof1WithOneDel
 */
public class MaxConsecutiveOnes3 {
    public static int longestOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = 0;
        //we keep on iterating right, we see every iteration left and right is added by 1
        //which makes the distance between right and left same.
        //The distance between right and left would change again if there is a longer subarray exist.
        for(;right < nums.length; ++right) {
            if (nums[right] == 0) k--;
            if (k < 0 && nums[left++] == 0) k++;
        }
        return right - left;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new int[]{1,1,1,0,0,0,1,1,1,1,0}) + " : "
                + longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        System.out.println(Arrays.toString(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}) + " : "
                + longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
    }
}
