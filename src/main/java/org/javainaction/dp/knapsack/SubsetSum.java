package org.javainaction.dp.knapsack;

/**
 * Given a set of positive numbers, determine if there exists a subset whose sum is equal to a given number āSā.
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
 * @see CountSubsetSum
 * @see CoinChange
 * @see TargetSum
 */
public class SubsetSum {
    public boolean canPartitionTopDown(int[] nums, int target) {
        Boolean[][] dp = new Boolean[nums.length][target + 1];
        return canPartitionRecursive(dp, nums, target, 0);
    }

    private boolean canPartitionRecursive(Boolean[][] dp, int[] nums, int target, int current) {
        //base cases
        if (target == 0) return true;
        if (nums.length <= current || target < 0) return false;

        //if we have already process this sub problem
        if (dp[current][target] != null) return dp[current][target];

        boolean partitionOne = false;

        //if amount being considered is less than or equal to target
        //unlike rod cutting or coin chance we need to change pointer to next as we want to consider distinct
        //occurrences and not ways to make a sum like {1, 1, 1} can make target of 3
        //but if 1 and 2 cane make target of 3
        if (nums[current] <= target)
            partitionOne = canPartitionRecursive(dp, nums, target - nums[current], current + 1);

        //if we could not consider partition one then use next number from given range
        boolean partitionTwo = canPartitionRecursive(dp, nums, target, current + 1);

        dp[current][target] = partitionOne || partitionTwo;
        return dp[current][target];
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
        /**
         * For first input
         * [
         * [T, T, F, F, F, F, F],
         * [T, T, T, T, T, T, F],
         * [T, T, T, T, T, T, T],
         * [T, T, T, T, T, T, T]]
         */
        // the bottom-right corner will have our answer.
        return dp[num.length - 1][sum];
    }

    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int[] num = { 1, 2, 3, 7 };
        System.out.println(ss.canPartition(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(ss.canPartition(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(ss.canPartition(num, 6));

        num = new int[]{ 1, 2, 3, 7 };
        System.out.println(ss.canPartitionTopDown(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(ss.canPartitionTopDown(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(ss.canPartitionTopDown(num, 6));
    }
}
