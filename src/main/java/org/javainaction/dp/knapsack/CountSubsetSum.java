package org.javainaction.dp.knapsack;

/**
 * Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.
 *
 * Example 1: #
 *
 * Input: {1, 1, 2, 3}, S=4
 * Output: 3
 * The given set has '3' subsets whose sum is '4': {1, 1, 2}, {1, 3}, {1, 3}
 * Note that we have two similar sets {1, 3}, because we have two '1' in our input.
 * Example 2: #
 *
 * Input: {1, 2, 7, 1, 5}, S=9
 * Output: 3
 * The given set has '3' subsets whose sum is '9': {2, 7}, {1, 7, 1}, {1, 2, 1, 5}
 * @see SubsetSum
 */
public class CountSubsetSum {
    public int countSubsets(int[] num, int sum) {
        Integer[][] dp = new Integer[num.length][sum + 1];
        return this.countSubsetsRecursive(dp, num, sum, 0);
    }

    private int countSubsetsRecursive(Integer[][] dp, int[] num, int sum, int currentIndex) {
        // base checks
        if (sum == 0)
            return 1;

        if(num.length == 0 || currentIndex >= num.length)
            return 0;

        // check if we have not already processed a similar problem
        if(dp[currentIndex][sum] != null) return dp[currentIndex][sum];

        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        int sum1 = 0;
        if( num[currentIndex] <= sum )
            sum1 = countSubsetsRecursive(dp, num, sum - num[currentIndex], currentIndex + 1);

        // recursive call after excluding the number at the currentIndex
        int sum2 = countSubsetsRecursive(dp, num, sum, currentIndex + 1);

        dp[currentIndex][sum] = sum1 + sum2;

        return dp[currentIndex][sum];
    }

    public static void main(String[] args) {
        CountSubsetSum ss = new CountSubsetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(ss.countSubsets(num, 4));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ss.countSubsets(num, 9));
    }
}
