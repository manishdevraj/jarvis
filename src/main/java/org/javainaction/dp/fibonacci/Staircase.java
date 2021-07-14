package org.javainaction.dp.fibonacci;

import java.util.HashMap;
import java.util.Map;

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
 * @see org.javainaction.recursion.StaircaseTraversal where we are given maxsteps that we can climb
 * vs here we know we have 1, 2, 3 steps that can be taken we can convert this problem into more generic way where
 * max steps becomes = 3
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

    //O(n * k) time | O(n) space where n is height and k is max steps
    private int straicaseTraversalMemo(int stairs) {
        int maxSteps = 3;
        Map<Integer, Integer> memoize = new HashMap<>();
        memoize.put(0, 1);
        memoize.put(1, 1);
        return numberOfWaysToTop(stairs, maxSteps, memoize);
    }

    private int numberOfWaysToTop(int stairs, int maxSteps, Map<Integer, Integer> memoize) {
        if (memoize.containsKey(stairs)) return memoize.get(stairs);
        int numberOfWays = 0;
        //we can climb either up to max steps or up to height
        for (int steps = 1; steps < Math.min(maxSteps, stairs) + 1; steps++) {
            numberOfWays += numberOfWaysToTop(stairs - steps, maxSteps, memoize);
        }
        memoize.put(stairs, numberOfWays);
        return memoize.get(stairs);
    }

    public static void main(String[] args) {
        Staircase sc = new Staircase();
        System.out.println(sc.countWaysMemoize(3));
        System.out.println(sc.countWaysMemoize(4));
        System.out.println(sc.countWaysMemoize(5));

        System.out.println(sc.bottomUpCountWays(3));
        System.out.println(sc.bottomUpCountWays(4));
        System.out.println(sc.bottomUpCountWays(5));

        System.out.println(sc.straicaseTraversalMemo(3));
        System.out.println(sc.straicaseTraversalMemo(4));
        System.out.println(sc.straicaseTraversalMemo(5));

    }
}
