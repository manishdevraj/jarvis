package org.javainaction.dp.fibonacci;

/**
 * Given a number ‘n’, implement a method to count how many possible ways there are to express ‘n’ as the
 * sum of 1, 3, or 4.
 *
 * Example 1:
 *
 * n : 4
 * Number of ways = 4
 * Explanation: Following are the four ways we can express 'n' : {1,1,1,1}, {1,3}, {3,1}, {4}
 * Example 2:
 *
 * n : 5
 * Number of ways = 6
 * Explanation: Following are the six ways we can express 'n' : {1,1,1,1,1}, {1,1,3}, {1,3,1}, {3,1,1},
 * {1,4}, {4,1}
 */
public class ExpressNumber {

    //O(n) time | O(n) space
    private int countWaysMemoize(int n) {
        int[] dp = new int[n + 1];
        return countWaysMemoizeRecursive(dp, n);
    }

    private int countWaysMemoizeRecursive(int[] dp, int n) {
        if (n < 3) return 1;
        if (n == 3) return 2;
        if (dp[n] == 0) {
            dp[n] = countWaysMemoizeRecursive(dp, n - 1)
                    + countWaysMemoizeRecursive(dp, n - 3)
                    + countWaysMemoizeRecursive(dp, n - 4);
        }
        return dp[n];
    }

    //O(n) time | O(n)
    private int bottomUpCountWays(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        ExpressNumber en = new ExpressNumber();
        System.out.println(en.countWaysMemoize(4));
        System.out.println(en.countWaysMemoize(5));
        System.out.println(en.countWaysMemoize(6));

        System.out.println(en.bottomUpCountWays(4));
        System.out.println(en.bottomUpCountWays(5));
        System.out.println(en.bottomUpCountWays(6));
    }
}
