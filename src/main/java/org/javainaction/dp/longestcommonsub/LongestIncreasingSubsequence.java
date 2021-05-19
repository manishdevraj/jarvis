package org.javainaction.dp.longestcommonsub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a number sequence, find the length of its Longest Increasing Subsequence (LIS).
 * In an increasing subsequence, all the elements are in increasing order (from lowest to highest).
 *
 * Example 1:
 *
 * Input: {4,2,3,6,10,1,12}
 * Output: 5
 * Explanation: The LIS is {2,3,6,10,12}.
 * Example 1:
 *
 * Input: {-4,10,3,7,15}
 * Output: 4
 * Explanation: The LIS is {-4,3,7,15}.
 */
public class LongestIncreasingSubsequence {

    public static int findLISLength(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }
        return maxLength;
    }

    private int findLISLengthRecursive(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex == nums.length)
            return 0;

        if(dp[currentIndex][previousIndex+1] == null) {
            // include nums[currentIndex] if it is larger than the last included number
            int c1 = 0;
            if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex])
                c1 = 1 + findLISLengthRecursive(dp, nums, currentIndex+1, currentIndex);

            int c2 = findLISLengthRecursive(dp, nums, currentIndex+1, previousIndex);
            dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
        }

        return dp[currentIndex][previousIndex+1];
    }

    // O(n^2) time | O(n) space
    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        int[] lengths = new int[array.length];
        int[] sequences = new int[array.length];
        Arrays.fill(lengths, 1);
        Arrays.fill(sequences, Integer.MIN_VALUE);
        int longestSequence = 0;
        for (int i = 0; i < array.length; i++) {
            int currentNum= array[i];
            for (int j = 0; j < i; j++) {
                int other = array[j];
                if (other < currentNum
                        && lengths[j] + 1 >= lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                    sequences[i] = j;
                }
            }
            if (lengths[i] >= lengths[longestSequence]) {
                longestSequence = i;
            }
        }
        return buildSequence(longestSequence, sequences, array);
    }

    public static List<Integer> buildSequence(int index,
                                              int[] sequences, int[] array) {
        List<Integer> result = new ArrayList<>();
        while (index != Integer.MIN_VALUE) {
            result.add(0, array[index]);
            index = sequences[index];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(findLISLength(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(findLISLength(nums));

        nums = new int[]{5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
        System.out.println(findLISLength(nums));
        System.out.println(longestIncreasingSubsequence(nums));
    }
}
