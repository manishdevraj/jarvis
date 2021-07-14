package org.javainaction.dp.knapsack;

/**
 * Given a set of positive numbers, partition the set into two subsets with a minimum difference between
 * their subset sums.
 *
 * Example 1: #
 *
 * Input: {1, 2, 3, 9}
 * Output: 3
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '3'. Following are the two subsets: {1, 2, 3} & {9}.
 * Example 2: #
 *
 * Input: {1, 2, 7, 1, 5}
 * Output: 0
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of number is '0'. Following are the two subsets: {1, 2, 5} & {7, 1}.
 * Example 3: #
 *
 * Input: {1, 3, 100, 4}
 * Output: 92
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '92'. Here are the two subsets: {1, 3, 4} & {100}.
 * @see PartitionEqualSubsetSum where we find two equal partition, here we are finding close to equal partitions
 */
public class MinSubsetSumDiff {
    public int canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        Integer[][] dp = new Integer[nums.length][sum + 1];
        //process two sums separately
        return this.canPartitionRecursive(dp, nums, 0, 0, 0);
    }

    private int canPartitionRecursive(Integer[][] dp, int[] num, int currentIndex, int sum1, int sum2) {
        // when we reach at end of numbers then return absolute difference between two sum
        if(currentIndex == num.length)
            return Math.abs(sum1 - sum2);

        // check if we have not already processed similar problem
        if(dp[currentIndex][sum1] != null) return dp[currentIndex][sum1];

        // recursive call after including the number at the currentIndex in the first set
        int diff1 = canPartitionRecursive(dp, num, currentIndex + 1,
                sum1 + num[currentIndex], sum2);

        // recursive call after including the number at the currentIndex in the second set
        int diff2 = canPartitionRecursive(dp, num, currentIndex + 1,
                sum1, sum2 + num[currentIndex]);

        //get the minimum difference as we are only interesting in that
        dp[currentIndex][sum1] = Math.min(diff1, diff2);

        return dp[currentIndex][sum1];
    }

    public static void main(String[] args) {
        MinSubsetSumDiff ps = new MinSubsetSumDiff();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.canPartition(num));
    }
}
