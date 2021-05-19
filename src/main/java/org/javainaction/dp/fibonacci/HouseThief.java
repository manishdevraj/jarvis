package org.javainaction.dp.fibonacci;


/**
 * There are ‘n’ houses built in a line. A thief wants to steal maximum possible money from these houses.
 * The only restriction the thief has is that he can’t steal from two consecutive houses, as that would alert the
 * security system. How should the thief maximize his stealing?
 *
 * Problem Statement #
 * Given a number array representing the wealth of ‘n’ houses, determine the maximum amount of money the thief
 * can steal without alerting the security system.
 *
 * Example 1:
 *
 * Input: {2, 5, 1, 3, 6, 2, 4}
 * Output: 15
 * Explanation: The thief should steal from houses 5 + 6 + 4
 * Example 2:
 *
 * Input: {2, 10, 14, 8, 1}
 * Output: 18
 * Explanation: The thief should steal from houses 10 + 8
 */
public class HouseThief {
    public static void main(String[] args) {
        HouseThief ht = new HouseThief();
        int[] wealth = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxSteal(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxSteal(wealth));

        wealth = new int[] {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxStealOptimized(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxStealOptimized(wealth));
    }

    public int findMaxStealTopDown(int[] wealth) {
        int dp[] = new int[wealth.length];
        return findMaxStealRecursive(dp, wealth, 0);
    }

    private int findMaxStealRecursive(int[] dp, int[] wealth, int currentIndex) {
        if( currentIndex >= wealth.length)
            return 0;

        if(dp[currentIndex] == 0) {
            // steal from current house and skip one to steal next
            int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(dp, wealth, currentIndex + 2);
            // skip current house to steel from the adjacent house
            int skipCurrent = findMaxStealRecursive(dp, wealth, currentIndex + 1);

            dp[currentIndex] = Math.max(stealCurrent, skipCurrent);
        }
        return dp[currentIndex];
    }

    private int findMaxSteal(int[] wealth) {
        if (wealth.length == 0) return 0;
        int dp[] = new int[wealth.length + 1]; // '+1' to handle the zero house
        dp[0] = 0;
        dp[1] = wealth[0];
        for (int i = 1; i < wealth.length; i++) {
            dp[i + 1] = Math.max(wealth[i] + dp[i - 1], dp[i]);
        }
        return dp[wealth.length];
    }

    private int findMaxStealOptimized(int[] wealth) {
        if (wealth.length == 0) return 0;
        else if (wealth.length == 1) return wealth[0];
        int p1 = wealth[0];
        int p2 = Math.max(wealth[1], p1);
        for (int i = 2; i < wealth.length; i++) {
            int maxSteal = Math.max(wealth[i] + p1, p2);
            p1 = p2;
            p2 = maxSteal;
        }
        return p2;
    }

}
