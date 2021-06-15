package org.javainaction.dp;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * Example 4:
 *
 * Input: prices = [1]
 * Output: 0
 * @see MaxProfitWithKTransactions
 */
public class BuyAndSellStock3 {
    public int maxProfit(int[] prices) {
        return maxProfitWithKTransactions(prices, 2);
    }

    //O(n * k) time and O(n * k) space
    public int maxProfitWithKTransactions(int[] prices, int k) {
        if (prices.length == 0) return 0;
        int[][] profit = new int[k + 1][prices.length];

        for (int i = 1; i < k + 1; i++) { //transactions
            int maxProfit = Integer.MIN_VALUE;
            for (int j = 1; j < prices.length; j++) { //no of days, we need to only worry about profit on second day
                //earlier day profit is profit of previous day minus cost to buy on day before
                maxProfit = Math.max(maxProfit, profit[i - 1][j - 1] - prices[j - 1]);
                profit[i][j] = Math.max(profit[i][j - 1], //previous day profit as we have max profit on previous day
                        maxProfit + prices[j]); //max profit so far plus price for while selling
            }

        }

        return profit[k][prices.length - 1];
    }

    public static void main(String[] args) {
        var obj = new BuyAndSellStock3();

        System.out.println("{3,3,5,0,0,3,1,4} : maximum profit for the stock is "
                + obj.maxProfit(new int[] {3,3,5,0,0,3,1,4}));
        System.out.println("{1,2,3,4,5} : maximum profit for the stock is "
                + obj.maxProfit(new int[] {1,2,3,4,5}));
        System.out.println("{3,3,5,0,0,3,1,4} : maximum profit for the stock is "
                + obj.maxProfit(new int[] {7,6,4,3,1}));
    }
}
