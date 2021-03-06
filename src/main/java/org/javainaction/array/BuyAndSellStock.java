package org.javainaction.array;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the
 * future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 * @see org.javainaction.slidingwindow.BuyAndSellStock2
 */
public class BuyAndSellStock {
    //O(n) time | O(1) space
    public int maxProfit(int[] prices) {
        int buyPrice = Integer.MAX_VALUE;
        int sellProfit = 0;
        //idea is to iterate in single run to find minimum buying price
        //using if else ensure we either bought or sold stock
        //change buy price only if it is smaller than previous
        //change sell stock only if it is greater that previous
        for (int price : prices) {
            if (price < buyPrice)
                buyPrice = price;
            else if (price - buyPrice > sellProfit)
                sellProfit = price - buyPrice;
        }
        return sellProfit;
    }

    public static void main(String[] args) {
        var obj = new BuyAndSellStock();

        System.out.println("{7,1,5,3,6,4} : maximum profit for the stock is "
                + obj.maxProfit(new int[] {7,1,5,3,6,4}));
        System.out.println("{7,6,4,3,1} : maximum profit for the stock is "
                + obj.maxProfit(new int[] {7,6,4,3,1}));
        System.out.println("{1} : maximum profit for the stock is "
                + obj.maxProfit(new int[] {1}));
    }
}
