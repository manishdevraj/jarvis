package org.javainaction.dp.longestcommonsub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a number sequence, find its Longest Increasing Subsequence (LIS).
 * In an increasing subsequence, all the elements are in increasing order (from lowest to highest).
 *
 * Sub sequence is sequence of elements that are in not necessarily adjacent but that are in same order as they appear
 * in the array. [1,3,4] form sub sequence of [1,2,3,4] and so do numbers [2,4]
 * Example 1:
 *
 * Input: {5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35}
 * Output: [-24, 2, 3, 5, 6, 35]
 */
public class LongestIncreasingSubseqArray {
    // O(n^2) time | O(n) space
    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        int[] dp = new int[array.length];
        int[] sequences = new int[array.length];
        Arrays.fill(dp, 1);
        Arrays.fill(sequences, Integer.MIN_VALUE);
        int longestSequence = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && dp[j] + 1 >= dp[i]) {
                    dp[i] = dp[j] + 1;
                    sequences[i] = j;
                }
            }
            //longest sequence is last sequence that allows us to build numbers by looking at their sequence index
            if (dp[i] >= dp[longestSequence]) {
                longestSequence = i;
            }
        }
        return buildSequence(longestSequence, sequences, array);
    }

    public static List<Integer> buildSequence(int index, int[] sequences, int[] array) {
        List<Integer> result = new ArrayList<>();
        while (index != Integer.MIN_VALUE) {
            result.add(0, array[index]);
            index = sequences[index];
        }
        return result;
    }

    // O(nlog(n)) time | O(n) space
    public static List<Integer> longestIncreasingSubseqBinarySearch(int[] array) {
        int[] indices = new int[array.length + 1];
        int[] sequences = new int[array.length];
        Arrays.fill(indices, Integer.MIN_VALUE);
        int length = 0;
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            int newLength = binarySearch(1, length, indices, array, num);
            sequences[i] = indices[newLength - 1];
            indices[newLength] = i;
            length = Math.max(length, newLength);
        }//
        return buildSequence(indices[length], sequences, array);
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
        System.out.println(longestIncreasingSubsequence(nums));
        System.out.println(longestIncreasingSubseqBinarySearch(nums));

        nums = new int[]{-4,10,3,7,15};
        System.out.println(longestIncreasingSubsequence(nums));
        System.out.println(longestIncreasingSubseqBinarySearch(nums));

        nums = new int[]{5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
        System.out.println(longestIncreasingSubsequence(nums));
        System.out.println(longestIncreasingSubseqBinarySearch(nums));

    }
}
