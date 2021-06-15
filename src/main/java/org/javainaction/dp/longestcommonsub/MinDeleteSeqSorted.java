package org.javainaction.dp.longestcommonsub;

import java.util.Arrays;

/**
 * Given a number sequence, find the minimum number of elements that should be deleted to make the
 * remaining sequence sorted.
 *
 * Example 1:
 *
 * Input: {4,2,3,6,10,1,12}
 * Output: 2
 * Explanation: We need to delete {4,1} to make the remaing sequence sorted {2,3,6,10,12}.
 * Example 2:
 *
 * Input: {-4,10,3,7,15}
 * Output: 1
 * Explanation: We need to delete {10} to make the remaing sequence sorted {-4,3,7,15}.
 * Example 3:
 *
 * Input: {3,2,1,0}
 * Output: 3
 * Explanation: Since the elements are in reverse order, we have to delete all except one to get a
 * sorted sequence. Sorted sequences are {3}, {2}, {1}, and {0}
 */
public class MinDeleteSeqSorted {
    private int findMinimumDeletions(int[] nums) {
        return nums.length - findLISLength(nums);
    }

    public int findLISLength(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //compute LIS in bottom up fashion
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        MinDeleteSeqSorted mdss = new MinDeleteSeqSorted();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(mdss.findMinimumDeletions(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(mdss.findMinimumDeletions(nums));
        nums = new int[]{3,2,1,0};
        System.out.println(mdss.findMinimumDeletions(nums));
    }
}
