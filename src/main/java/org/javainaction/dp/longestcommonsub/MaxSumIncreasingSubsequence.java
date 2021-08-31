package org.javainaction.dp.longestcommonsub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a number sequence, find the increasing subsequence with the highest sum. Write a method that returns
 * the highest sum.
 *
 * Example 1:
 *
 * Input: {4,1,2,6,10,1,12}
 * Output: 32
 * Explanation: The increasing sequence is {4,6,10,12}.
 * Please note the difference, as the LIS is {1,2,6,10,12} which has a sum of '31'.
 * Example 2:
 *
 * Input: {-4,10,3,7,15}
 * Output: 25
 * Explanation: The increaseing sequences are {10, 15} and {3,7,15}.
 * @see LongestIncreasingSubsequence
 * @see LongestIncreasingSubseqArray
 *
 */
public class MaxSumIncreasingSubsequence {
    public static int getMaxSumIncreasingSubseq(int[] nums) {
        //clone the array because to mark sum at each index as value of themselves
        int[] sums = nums.clone();

        //idea is to capture the longest increasing subsequence where sum of their values is maximum
        //when we start max at start of index is value at start
        int max = sums[0];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // unlike LIS where we just compute total length by adding LIS at i as dp[j] + 1
                // we replace 1 with num[i] and sum array becomes memoized information
                // we need to check if sum[i] which is memo is smaller than sum[j] + num[i], when nums[i] > nums[j]
                //if so store that value as that will be maximum
                if (nums[i] > nums[j]) {
                    sums[i] = Math.max(sums[i], sums[j] + nums[i]);
                    //store maximum sum
                    max = Math.max(sums[i], max);
                }
            }

        }
        return max;
    }

    public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
        int[] sequences = new int[array.length];
        Arrays.fill(sequences, Integer.MIN_VALUE);
        int[] sums = array.clone();
        int maxSumIdx = 0;
        for (int i = 0; i < array.length; i++) {
            int currentNum = array[i];
            for (int j = 0; j < i; j++) {
                int otherNum = array[j];
                if (otherNum < currentNum && sums[j] + currentNum >= sums[i]) {
                    sums[i] = sums[j] + currentNum;
                    sequences[i] = j;
                }
            }
            if (sums[i] >= sums[maxSumIdx]) {
                maxSumIdx = i;
            }
        }
        return buildSequences(array, sequences, maxSumIdx, sums[maxSumIdx]);
    }

    public static List<List<Integer>> buildSequences(
            int[] array, int[] sequences, int currentIdx, int sums) {

        List<List<Integer>> sequence = new ArrayList<>();
        sequence.add(new ArrayList<>());
        sequence.add(new ArrayList<>());
        sequence.get(0).add(sums);
        while (currentIdx != Integer.MIN_VALUE) {
            sequence.get(1).add(0, array[currentIdx]);
            currentIdx = sequences[currentIdx];
        }
        return sequence;
    }

    public static void main(String[] args) {
        int[] nums = {4,1,2,6,10,1,12};
        System.out.println(getMaxSumIncreasingSubseq(nums));
        System.out.println(maxSumIncreasingSubsequence(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(getMaxSumIncreasingSubseq(nums));
        System.out.println(maxSumIncreasingSubsequence(nums));
        nums = new int[]{1,3,8,4,14,6,14,1,9,4,13,3,11,17,29};
        System.out.println(getMaxSumIncreasingSubseq(nums));
        System.out.println(maxSumIncreasingSubsequence(nums));
    }
}
