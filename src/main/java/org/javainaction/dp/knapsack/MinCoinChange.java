package org.javainaction.dp.knapsack;

import java.util.Arrays;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the minimum
 * number of coins needed to make up that amount.
 *
 * Example 1:
 *
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 2
 * Explanation: We need minimum of two coins {2,3} to make a total of '5'
 * Example 2:
 *
 * Denominations: {1,2,3}
 * Total amount: 11
 * Output: 4
 *
 * Explanation: We need minimum four coins {2,3,3,3} to make a total of '11'
 * Problem Statement #
 * Given a number array to represent different coin denominations and a total amount ‘T’, we need to find the minimum
 * number of coins needed to make change for ‘T’. We can assume an infinite supply of coins, therefore, each coin can
 * be chosen multiple times.
 *
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made
 * up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 * Example 4:
 *
 * Input: coins = [1], amount = 1
 * Output: 1
 * Example 5:
 *
 * Input: coins = [1], amount = 2
 * Output: 2
 *
 */
public class MinCoinChange {
    public int countChange(int[] denominations, int total) {
        Integer[][] dp = new Integer[denominations.length][total + 1];
        int result = this.countChangeRecursive(dp, denominations, total, 0);
        return (result == Integer.MAX_VALUE ? -1 : result);
    }

    private int countChangeRecursive(Integer[][] dp, int[] denominations, int total, int currentIndex) {
        // base check
        if (total == 0)
            return 0;

        if(denominations.length == 0 || currentIndex >= denominations.length)
            return Integer.MAX_VALUE;

        // check if we have not already processed a similar sub-problem
        if(dp[currentIndex][total] == null) {
            // recursive call after selecting the coin at the currentIndex
            // if the coin at currentIndex exceeds the total, we shouldn't process this
            int count1 = Integer.MAX_VALUE;
            if( denominations[currentIndex] <= total ) {
                int res = countChangeRecursive(dp, denominations, total - denominations[currentIndex], currentIndex);
                if(res != Integer.MAX_VALUE){
                    count1 = res + 1;
                }
            }

            // recursive call after excluding the coin at the currentIndex
            int count2 = countChangeRecursive(dp, denominations, total, currentIndex + 1);
            dp[currentIndex][total] = Math.min(count1, count2);
        }
        return dp[currentIndex][total];
    }

    public static int minNumberOfCoinsForChange(int[] denoms, int n) {
        // O(nd) time | O(n) time
        int[] coins = new int[n + 1];
        Arrays.fill(coins, Integer.MAX_VALUE);
        coins[0] = 0;
        int toCompare = 0;
        for(int denom : denoms) {
            for(int coinsIdx = 0; coinsIdx < coins.length; coinsIdx++) {
                if(denom <= coinsIdx) {
                    if(coins[coinsIdx - denom] == Integer.MAX_VALUE){
                        toCompare = coins[coinsIdx - denom];
                    } else {
                        toCompare = coins[coinsIdx - denom] + 1;
                    }
                    coins[coinsIdx] = Math.min(coins[coinsIdx], toCompare);
                }
            }
        }
        return coins[n] != Integer.MAX_VALUE ? coins[n] : -1;
    }

    public int countChangeBottomup(int[] denominations, int total)
    {
        int n = denominations.length;
        int[][] dp = new int[n][total + 1];

        for(int i=0; i < n; i++)
            for(int j=0; j <= total; j++)
                dp[i][j] = Integer.MAX_VALUE;

        // populate the total=0 columns, as we don't need any coin to make zero total
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        for(int i=0; i < n; i++) {
            for(int t=1; t <= total; t++) {
                if(i > 0)
                    dp[i][t] = dp[i-1][t]; //exclude the coin
                if(t >= denominations[i]) {
                    if(dp[i][t-denominations[i]] != Integer.MAX_VALUE)
                        dp[i][t] = Math.min(dp[i][t], dp[i][t-denominations[i]]+1); // include the coin
                }
            }
        }

        // total combinations will be at the bottom-right corner.
        return (dp[n-1][total] == Integer.MAX_VALUE ? -1 : dp[n-1][total]);
    }

    public static void main(String[] args) {
        MinCoinChange cc = new MinCoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
        System.out.println(cc.countChange(denominations, 11));
        System.out.println(cc.countChange(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(cc.countChange(denominations, 7));

        System.out.println("------------------------------------");
        denominations = new int[]{1, 2, 3};
        System.out.println(minNumberOfCoinsForChange(denominations, 5));
        System.out.println(minNumberOfCoinsForChange(denominations, 11));
        System.out.println(minNumberOfCoinsForChange(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(minNumberOfCoinsForChange(denominations, 7));


    }
}
