package org.javainaction.dp.knapsack;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the total
 * number of distinct ways to make up that amount.
 *
 * Example:
 *
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 5
 * Explanation: There are five ways to make the change for '5', here are those ways:
 *   1. {1,1,1,1,1}
 *   2. {1,1,1,2}
 *   3. {1,2,2}
 *   4. {1,1,3}
 *   5. {2,3}
 *
 * @see CountSubsetSum
 * @see SubsetSum
 */
public class CoinChange {
    public int countChange(int[] denominations, int total) {
        Integer[][] dp = new Integer[denominations.length][total + 1];
        return this.countChangeRecursive(dp, denominations, total, 0);
    }

    private int countChangeRecursive(Integer[][] dp, int[] denominations, int total, int currentIndex)
    {
        // base checks
        if (total == 0)
            return 1;

        //if we ran out of coins
        if(denominations.length == 0 || currentIndex >= denominations.length)
            return 0;

        // if we have already processed a similar sub-problem, return the result from memory
        if(dp[currentIndex][total] != null)
            return dp[currentIndex][total];

        // recursive call after selecting the coin at the currentIndex
        // if the number at currentIndex exceeds the total, we shouldn't process this we need to make sure we can make
        // whole amount and not partial
        int waysToMakeIncl = 0;
        if( denominations[currentIndex] <= total ) {
            waysToMakeIncl = countChangeRecursive(dp, denominations, total - denominations[currentIndex], currentIndex);
        }


        // recursive call after excluding the number at the currentIndex
        int waysToMameExclude = countChangeRecursive(dp, denominations, total, currentIndex + 1);

        dp[currentIndex][total] = waysToMakeIncl + waysToMameExclude;
        return dp[currentIndex][total];
    }

    //O (n*d) time | O(d) space
    public static int numberOfWaysToMakeChange(int n, int[] denoms) {
        int[] ways = new int[n + 1];
        ways[0] = 1;
        for(int denom : denoms) {
            for(int amount = 1; amount < n + 1; amount++) {
                if(denom <= amount) {
                    ways[amount] += ways[amount - denom];
                }
            }
        }
        return ways[n];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
        System.out.println(numberOfWaysToMakeChange(5, denominations));

        int[] input = {1, 5};
        System.out.println(cc.countChange(input, 6));
        System.out.println(numberOfWaysToMakeChange(6, input));
    }
}
