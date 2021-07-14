package org.javainaction.slidingwindow;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve.
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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
 * Explanation: In this case, no transaction is done, i.e., max profit = 0.
 * @see org.javainaction.array.BuyAndSellStock
 */
public class BuyAndSellStock2 {
    private static int maxProfit(int[] stockPrices) {
        int profit = 0;
        for (int i = 0; i < stockPrices.length - 1; i++) {
            //reset buy and sell at every iteration of buy sell cycle
            int buy = -1, sell=-1;
            //find best buy
            while(i < stockPrices.length - 1 && stockPrices[i + 1] <= stockPrices[i]) i++;
            buy = i;

            //find best sell
            while(i < stockPrices.length - 1 && stockPrices[i + 1] > stockPrices[i]) i++;
            sell = i;
            //get profit only if it is positive and add it to max profit
            profit += Math.max(0, stockPrices[sell] - stockPrices[buy]);
        }
        return profit;
    }

    public static void main(String[] args) {
        int profit = BuyAndSellStock2.maxProfit(new int[] {7,1,5,3,6,4});
        System.out.println("{7,1,5,3,6,4} : maximum profit for the stock is " + profit);
        profit = BuyAndSellStock2.maxProfit(new int[] {1,2,3,4,5});
        System.out.println("{1,2,3,4,5} : maximum profit for the stock is " + profit);
        profit = BuyAndSellStock2.maxProfit(new int[] {7,6,4,3,1});
        System.out.println("{7,6,4,3,1} : maximum profit for the stock is " + profit);
    }
}
