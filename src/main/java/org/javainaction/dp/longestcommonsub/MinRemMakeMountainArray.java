package org.javainaction.dp.longestcommonsub;

import java.util.Arrays;

/**
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1]
 * Output: 0
 * Explanation: The array itself is a mountain array so we do not need to remove any elements.
 * Example 2:
 *
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 * Example 3:
 *
 * Input: nums = [4,3,2,1,1,2,3,1]
 * Output: 4
 * Example 4:
 *
 * Input: nums = [1,2,3,4,4,3,2,1]
 * Output: 1
 * @see LongestBitonicSubseq
 */
public class MinRemMakeMountainArray {
    public int minimumMountainRemovals(int[] nums) {
        int[] longIncSubseq = new int[nums.length];
        int[] longDecSubseq = new int[nums.length];

        //every index is longest bitonic subsequence of length 1
        Arrays.fill(longIncSubseq, 1);
        Arrays.fill(longDecSubseq, 1);
        // find LDS for every index up to the beginning of the array
        //step:1 find the longest increasing subsequences till i
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    longIncSubseq[i] = Math.max(longIncSubseq[i], longIncSubseq[j] + 1);
                }
            }
        }

        // find LDS for every index up to the end of the array
        //step : 2 find longest decreasing subsequence starting from i
        for (int i = nums.length - 1; i >=0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    longDecSubseq[i] = Math.max(longDecSubseq[i], longDecSubseq[j] + 1);
                }
            }
        }

        /*
        step 3: now find longest bitonic subsequence
		anything length - longest bitonic sequence is to be removed
		*/
        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (longIncSubseq[i] > 1 && longDecSubseq[i] > 1)
                maxLength = Math.max(maxLength, longIncSubseq[i] + longDecSubseq[i] - 1);
        }

        //find maximum Bitonic subsequence and remove from length of the array to find duplications
        return nums.length - maxLength;
    }

    public static void main(String[] args) {
        var obj = new MinRemMakeMountainArray();
        System.out.println("{1, 3, 1} min removals to make mountain : "
                + obj.minimumMountainRemovals(new int[]{1,3,1}));
        System.out.println("{2,1,1,5,6,2,3,1} min removals to make mountain : "
                + obj.minimumMountainRemovals(new int[]{2,1,1,5,6,2,3,1}));
    }
}
