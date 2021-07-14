package org.javainaction.dp.longestcommonsub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a number sequence, find the length of its Longest Increasing Subsequence (LIS).
 * In an increasing subsequence, all the elements are in increasing order (from lowest to highest).
 *
 * Sub sequence is sequence of elements that are in not necessarily adjacent but that are in same order as they appear
 * in the array. [1,3,4] form sub sequence of [1,2,3,4] and so do numbers [2,4]
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
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * @see LongestIncreasingSubseqArray
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

    public static int findLISLengthTopDown(int[] nums) {
        Integer[][] dp = new Integer[nums.length + 1][nums.length];
        return findLISLengthRecursive(dp, nums, 0, -1);
    }
    private static int findLISLengthRecursive(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex == nums.length)
            return 0;

        if(dp[currentIndex][previousIndex + 1] == null) {
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
    public static int longestIncreasingSubsequence(int[] array) {
        int[] lengths = new int[array.length];
        Arrays.fill(lengths, 1);
        int longestSequence = 0;
        for (int i = 0; i < array.length; i++) {
            int currentNum= array[i];
            for (int j = 0; j < i; j++) {
                int other = array[j];
                if (other < currentNum
                        && lengths[j] + 1 >= lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                }
            }
            if (lengths[i] >= lengths[longestSequence]) {
                longestSequence = i;
            }
        }
        return lengths[longestSequence];
    }

    // O(nlog(n)) time | O(n) space
    public static int longestIncreasingSubseqBinarySearch(int[] array) {
        //represents indices that forms subsequence
        int[] indices = new int[array.length + 1];
        Arrays.fill(indices, Integer.MIN_VALUE);
        int length = 0;
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            int newLength = binarySearch(1, length, indices, array, num);
            indices[newLength] = i;
            length = Math.max(length, newLength);
        }
        return length;
    }

    public static int binarySearch(int startIdx, int endIdx,
                                   int[] indices, int[] array, int target) {

        if (startIdx > endIdx) {
            return startIdx;
        }
        int middleIdx = (startIdx + endIdx) / 2;
        if (array[indices[middleIdx]] < target) {
            startIdx = middleIdx + 1;
        } else {
            endIdx = middleIdx - 1;
        }
        return binarySearch(startIdx, endIdx, indices, array, target);
    }

    public static void main(String[] args) {
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(findLISLength(nums));
        System.out.println(findLISLengthTopDown(nums));
        System.out.println(longestIncreasingSubsequence(nums));
        System.out.println(longestIncreasingSubseqBinarySearch(nums));

        nums = new int[]{-4,10,3,7,15};
        System.out.println(findLISLength(nums));
        System.out.println(findLISLengthTopDown(nums));
        System.out.println(longestIncreasingSubsequence(nums));
        System.out.println(longestIncreasingSubseqBinarySearch(nums));

        nums = new int[]{5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
        System.out.println(findLISLength(nums));
        System.out.println(findLISLengthTopDown(nums));
        System.out.println(longestIncreasingSubsequence(nums));
        System.out.println(longestIncreasingSubseqBinarySearch(nums));

    }
}
