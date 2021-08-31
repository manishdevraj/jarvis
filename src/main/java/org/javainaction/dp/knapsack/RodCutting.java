package org.javainaction.dp.knapsack;


/**
 * Given a rod of length ‘n’, we are asked to cut the rod and sell the pieces in a way that will maximize the profit.
 * We are also given the price of every piece of length ‘i’ where ‘1 <= i <= n’.
 *
 * Example:
 *
 * Lengths: [1, 2, 3, 4, 5]
 * Prices: [2, 6, 7, 10, 13]
 * Rod Length: 5
 *
 * Let’s try different combinations of cutting the rod:
 *
 * Five pieces of length 1 => 10 price
 * Two pieces of length 2 and one piece of length 1 => 14 price
 * One piece of length 3 and two pieces of length 1 => 11 price
 * One piece of length 3 and one piece of length 2 => 13 price
 * One piece of length 4 and one piece of length 1 => 12 price
 * One piece of length 5 => 13 price
 *
 * This shows that we get the maximum price (14) by cutting the rod into two pieces of length ‘2’
 * and one piece of length ‘1’.
 *
 * @see CoinChange Where denominations are weights and value is capacity
 * @see KnapsackProblem where when we include or exclude items we do not worry about uniqueness
 * @see MaxProfitFruitBasket where when we include or exclude items we need worry about uniqueness
 */
public class RodCutting {
    public int solveRodCuttingTopDown(int[] lengths, int[] prices, int rodLen) {
        Integer[][] dp = new Integer[lengths.length][rodLen + 1];
        return solveRodCuttingRecursive(lengths, prices, rodLen, dp, 0);
    }

    private int solveRodCuttingRecursive(int[] lengths, int[] prices, int rodLen, Integer[][] dp, int cutAt) {
        if (rodLen == 0) return 0;

        //we cannot cut any more
        if (rodLen <= 0 || prices.length == 0 || lengths.length == 0 || cutAt >= lengths.length) return 0;

        //we already know the cost to cut then return that cost
        if (dp[cutAt][rodLen] != null)
            return dp[cutAt][rodLen];

        //we can only cut if cutting length is less that rod length
        int costToCutIncl = 0;
        //if rod is smaller than min cutting length then we cannot take this price
        if (lengths[cutAt] <= rodLen) {
            costToCutIncl = prices[cutAt] + solveRodCuttingRecursive(lengths, prices, rodLen - lengths[cutAt], dp, cutAt);
        }

        int costToCutExcl = solveRodCuttingRecursive(lengths, prices, rodLen, dp, cutAt + 1);

        dp[cutAt][rodLen] = Math.max(costToCutIncl, costToCutExcl);
        return dp[cutAt][rodLen];
    }

    public int solveRodCuttingBottomup(int[] lengths, int[] prices, int n) {
        // base checks
        if (n <= 0 || prices.length == 0 || prices.length != lengths.length)
            return 0;

        int lengthCount = lengths.length;
        int[][] dp = new int[lengthCount][n + 1];

        // process all rod lengths for all prices
        for(int i = 0; i < lengthCount; i++) {
            for (int len = 1; len <= n; len++) {
                int cost1 = 0, cost2 = 0;
                if(lengths[i] <= len)
                    cost1 = prices[i] + dp[i][len - lengths[i]];
                if( i > 0 )
                    cost2 = dp[i - 1][len];
                dp[i][len] = Math.max(cost1, cost2);
            }
        }
        /**
         * [
         * [0, 2, 4, 6, 8, 10],
         * [0, 2, 6, 8, 12, 14],
         * [0, 2, 6, 8, 12, 14],
         * [0, 2, 6, 8, 12, 14],
         * [0, 2, 6, 8, 12, 14]]
         */
        // maximum price will be at the bottom-right corner.
        return dp[lengthCount-1][n];
    }

    public static void main(String[] args) {
        RodCutting rc = new RodCutting();
        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 6, 7, 10, 13};
        int maxProfit = rc.solveRodCuttingBottomup(lengths, prices, 5);
        System.out.println(maxProfit);

        maxProfit = rc.solveRodCuttingTopDown(lengths, prices, 5);
        System.out.println(maxProfit);
    }
}
