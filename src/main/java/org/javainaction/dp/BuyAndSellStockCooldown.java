package org.javainaction.dp;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 */
public class BuyAndSellStockCooldown {

    //O(n) time | O(n) space
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        /**
         * To represent the decision at index i:
         *
         * buy[i]: Max profit till index i. The series of transaction is ending with a buy.
         * sell[i]: Max profit till index i. The series of transaction is ending with a sell.
         * To clarify:
         *
         * Till index i, the buy / sell action must happen and must be the last action.
         * It may not happen at index i. It may happen at i - 1, i - 2, ... 0.
         * In the end n - 1, return sell[n - 1]. Apparently we cannot finally end up with a buy.
         * In that case, we would rather take a rest at n - 1.
         * For special case no transaction at all, classify it as sell[i], so that in the end,
         * we can still return sell[n - 1].
         */
        int[] sell = new int[prices.length];
        int[] buy  = new int[prices.length];

        //We can buy. The max profit at i = 0 ending with a buy is -prices[0].
        buy[0]  = -prices[0];
        buy[1]  = -Math.min(prices[0], prices[1]);
        //We cannot sell. The max profit at i = 0 ending with a sell is 0
        sell[1] = Math.max(0, buy[0] + prices[1]);

        for (int i = 2; i < prices.length; i++) {
            // buy[i]: To make a decision whether to buy at i
            // we either take a rest, by just using the old decision at i - 1, or
            // sell at/before i - 2, then buy at i.
            // We cannot sell at i - 1, then buy at i, because of cooldown.
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            // sell[i]: To make a decision whether to sell at i
            // we either take a rest, by just using the old decision at i - 1, or
            // buy at/before i - 1, then sell at i.
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        return sell[prices.length - 1];
    }

    public static void main(String[] args) {
        var obj = new BuyAndSellStockCooldown();
        System.out.println("{1,2,3,0,2} : maximum profit for the stock with cooldown "
                + obj.maxProfit(new int[] {1,2,3,0,2}));
        System.out.println("{1} : maximum profit for the stock with cooldown "
                + obj.maxProfit(new int[] {1}));
    }
}
