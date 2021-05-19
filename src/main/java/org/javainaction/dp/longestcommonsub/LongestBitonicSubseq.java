package org.javainaction.dp.longestcommonsub;

import java.util.Arrays;

/**
 * Given a number sequence, find the length of its Longest Bitonic Subsequence (LBS).
 * A subsequence is considered bitonic if it is monotonically increasing and then monotonically decreasing.
 *
 * Example 1:
 *
 * Input: {4,2,3,6,10,1,12}
 * Output: 5
 * Explanation: The LBS is {2,3,6,10,1}.
 * Example 2:
 *
 * Input: {4,2,5,9,7,6,10,3,1}
 * Output: 7
 * Explanation: The LBS is {4,5,9,7,6,3,1}.
 *
 * Similar problem longest peak
 */
public class LongestBitonicSubseq {
    public static void main(String[] args) {
        LongestBitonicSubseq lbs = new LongestBitonicSubseq();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(lbs.findLBSLength(nums));
        nums = new int[]{4,2,5,9,7,6,10,3,1};
        System.out.println(lbs.findLBSLength(nums));
    }

    private int findLBSLength(int[] nums) {
        int[] lds = new int[nums.length];
        int[] ldsReverse = new int[nums.length];

        Arrays.fill(lds, 1);
        Arrays.fill(ldsReverse, 1);
        // find LDS for every index up to the beginning of the array
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        // find LDS for every index up to the end of the array
        for (int i = nums.length - 1; i >=0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    ldsReverse[i] = Math.max(ldsReverse[i], ldsReverse[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            maxLength = Math.max(maxLength, lds[i] + ldsReverse[i] - 1);
        }

        return maxLength;
    }


}
