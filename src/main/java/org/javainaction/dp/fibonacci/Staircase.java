package org.javainaction.dp.fibonacci;

/**
 * Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the
 * staircase, given that, at every step you can either take 1 step, 2 steps, or 3 steps.
 *
 * Example 1:
 *
 * Number of stairs (n) : 3
 * Number of ways = 4
 * Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3}
 * Example 2:
 *
 * Number of stairs (n) : 4
 * Number of ways = 7
 * Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1},
 * {2,2}, {1,3}, {3,1}
 *
 * @see org.javainaction.recursion.StaircaseTraversal
 */
public class Staircase {

    //O(n)
    private int countWaysMemoize(int n) {
        int dp[] = new int[n+1];
        return countWaysMemoizeRecursive(dp, n);
    }

    private int countWaysMemoizeRecursive(int[] dp, int n) {
        if (n < 2) return 1;
        if (n == 2) return 2;
        if (dp[n] == 0) {
            dp[n] = countWaysMemoizeRecursive(dp, n - 3)
                    + countWaysMemoizeRecursive(dp, n - 2)
                    + countWaysMemoizeRecursive(dp, n - 1);
        }
        return dp[n];
    }

    //O(n) time | O(1) space
    private int bottomUpCountWays(int n) {
        if (n < 2) return 1;
        if (n == 2) return 2;
        int n1 = 1, n2 = 1, n3 = 2, temp;
        for (int i = 3; i <= n; i++) {
            temp = n1 + n2 + n3;
            n1 = n2;
            n2 = n3;
            n3 = temp;
        }
        return n3;
    }

    public static void main(String[] args) {
        Staircase sc = new Staircase();
        System.out.println(sc.countWaysMemoize(3));
        System.out.println(sc.countWaysMemoize(4));
        System.out.println(sc.countWaysMemoize(5));

        System.out.println(sc.bottomUpCountWays(3));
        System.out.println(sc.bottomUpCountWays(4));
        System.out.println(sc.bottomUpCountWays(5));

    }
}
