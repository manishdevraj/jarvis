package org.javainaction.dp.knapsack;

/**
 * Given a set of positive numbers, determine if there exists a subset whose sum is equal to a given number ‘S’.
 *
 * Example 1: #
 *
 * Input: {1, 2, 3, 7}, S=6
 * Output: True
 * The given set has a subset whose sum is '6': {1, 2, 3}
 * Example 2: #
 *
 * Input: {1, 2, 7, 1, 5}, S=10
 * Output: True
 * The given set has a subset whose sum is '10': {1, 2, 7}
 * Example 3: #
 *
 * Input: {1, 3, 4, 8}, S=6
 * Output: False
 * The given set does not have any subset whose sum is equal to '6'.
 */
public class SubsetSum {
    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int[] num = { 1, 2, 3, 7 };
        System.out.println(ss.canPartition(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(ss.canPartition(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(ss.canPartition(num, 6));
    }

    private boolean canPartition(int[] num, int sum) {
        boolean[][] dp = new boolean[num.length][sum + 1];

        for (int i = 0; i < num.length; i++) {
            dp[i][0] = true;
        }

        for (int s = 1; s <= sum; s++) {
            dp[0][s] = num[0] == s;
        }

        for (int i = 1; i < num.length; i++) {
            for (int s = 1; s <= sum; s++) {
                if (dp[i - 1][s]) {
                    dp[i][s] = dp[i - 1][s];
                } else if (s >= num[i]){
                    dp[i][s] = dp[i - 1][s - num[i]];
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[num.length - 1][sum];
    }
}
