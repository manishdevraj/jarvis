package org.javainaction.dp;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day. And you are allowed
 * to do K transactions meaning each buy and sell is one transaction
 *
 * Example :  {5, 11, 3, 50, 60, 90}
 * K = 2
 *
 * Output would be 93 // Buy at 5 and and sell at 11 : after we sell at 11 we have 11 - 5 = 6 as total profit,
 * //buy at 3 and sell at 90 : after we buy at 3 profit is 6-3= 3 again we sell at 90 means we get 90 + 3 = total profit
 *
 * Find the maximum profit you can achieve.
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 * @see BuyAndSellStock3
 */
public class MaxProfitWithKTransactions {
    //O(n * k) time and O(n * k) space
    public static int maxProfitWithKTransactions(int[] prices, int k) {
        if (prices.length == 0) return 0;
        int[][] profit = new int[k + 1][prices.length];

        for (int i = 1; i < k + 1; i++) { //transactions
            int maxProfit = Integer.MIN_VALUE;
            //no of days, we need to only worry about profit on second day
            for (int j = 1; j < prices.length; j++) {
                //earlier day profit is profit of previous day minus cost to buy on day before
                maxProfit = Math.max(maxProfit, profit[i - 1][j - 1] - prices[j - 1]);
                //previous day profit as we have max profit on previous day
                //max profit so far plus price for while selling
                profit[i][j] = Math.max(profit[i][j - 1], maxProfit + prices[j]);
            }
        }

        return profit[k][prices.length - 1];
    }

    public static void main(String[] args) {
        int[] input = {5, 11, 3, 50, 60, 90};
        System.out.println("{5, 11, 3, 50, 60, 90} max profit with K=2 transactions : "
                + maxProfitWithKTransactions(input, 2));
    }
}
