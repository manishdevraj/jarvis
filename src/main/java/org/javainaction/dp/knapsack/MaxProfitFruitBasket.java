package org.javainaction.dp.knapsack;

/**
 * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack which has a capacity ‘C’.
 * The goal is to get the maximum profit from the items in the knapsack. Each item can only be selected once, as we d
 * on’t have multiple quantities of any item.
 *
 * Let’s take the example of Merry, who wants to carry some fruits in the knapsack to get maximum profit.
 * Here are the weights and profits of the fruits:
 *
 * Items: { Apple, Orange, Banana, Melon }
 * Weights: { 2, 3, 1, 4 }
 * Profits: { 4, 5, 3, 7 }
 * Knapsack capacity: 5
 *
 * Let’s try to put different combinations of fruits in the knapsack, such that their total weight is not more than 5:
 *
 * Apple + Orange (total weight 5) => 9 profit
 * Apple + Banana (total weight 3) => 7 profit
 * Orange + Banana (total weight 4) => 8 profit
 * Banana + Melon (total weight 5) => 10 profit
 *
 * This shows that Banana + Melon is the best combination, as it gives us the maximum profit and the total weight
 * does not exceed the capacity.
 *
 * Problem Statement #
 * Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset of these
 * items which will give us maximum profit such that their cumulative weight is not more than a given number ‘C’.
 * Each item can only be selected once,
 * which means either we put an item in the knapsack or we skip it.
 * @see RodCutting
 */
public class MaxProfitFruitBasket {

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return this.knapsackRecursive(dp, profits, weights, capacity, 0);
    }

    private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {
        //we have no more fruits to pick or capacity ran out
        if (currentIndex >= profits.length || capacity <= 0) {
            return 0;
        }

        //if we have computed this problem, then return result
        if (dp[currentIndex][capacity] != null)
            return dp[currentIndex][capacity];

        //if weight of fruit is under current capacity and
        int profitInc = 0;
        if (weights[currentIndex] <= capacity) {
            //profit including this fruit and any other fruit from the basket
            //since we added this fruit, reduce capacity
            //currentIndex + 1 because we need to pick distinct fruits
            profitInc = profits[currentIndex]
                    + knapsackRecursive(dp, profits, weights,
                    capacity - weights[currentIndex],currentIndex + 1);
        }

        //profit excluding previous fruit, so we do not deduct capacity
        int profitExcl = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profitInc, profitExcl);

        return dp[currentIndex][capacity];
    }

    public int solveKnapsackBottomup(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        // populate the capacity=0 columns, with '0' capacity we have '0' profit
        for (int i=0; i < n; i++)
            dp[i][0] = 0;

        // if we have only one weight, we will take it if it is not more than the capacity
        for (int c=0; c <= capacity; c++) {
            if (weights[0] <= c) {
                dp[0][c] = profits[0];
            }

        }

        // process all sub-arrays for all the capacities
        for(int i=1; i < n; i++) {
            for(int c=1; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i-1][c-weights[i]];
                // exclude the item
                profit2 = dp[i-1][c];
                // take maximum
                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        // maximum profit will be at the bottom-right corner.
        return dp[n-1][capacity];
    }

    public static void main(String[] args) {
        MaxProfitFruitBasket ks = new MaxProfitFruitBasket();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
