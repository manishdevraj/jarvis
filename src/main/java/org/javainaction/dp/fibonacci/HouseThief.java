package org.javainaction.dp.fibonacci;


import java.util.Arrays;

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
 * @see HouseRobber2
 */
public class HouseThief {
    //Recursive memo (top-down)
    public int findMaxStealTopDown(int[] wealth) {
        var memo = new int[wealth.length];
        Arrays.fill(memo, -1);
        return findMaxStealRecursive(memo, wealth, 0);
    }

    private int findMaxStealRecursive(int[] memo, int[] wealth, int currentIndex) {
        if( currentIndex >= wealth.length)
            return 0;

        if (memo[currentIndex] >= 0) return memo[currentIndex];

        // steal from current house and skip one to steal next
        int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(memo, wealth, currentIndex + 2);
        // skip current house to steel from the adjacent house
        int skipCurrent = findMaxStealRecursive(memo, wealth, currentIndex + 1);

        memo[currentIndex] = Math.max(stealCurrent, skipCurrent);
        return memo[currentIndex];
    }

    //Iterative + memo (bottom-up)
    //O(n) time | O(n) space
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

    // Iterative + 2 variables (bottom-up)
    //O(n) time | O(1) space
    private int findMaxStealOptimized(int[] wealth) {
        if (wealth.length == 0) return 0;
        else if (wealth.length == 1) return wealth[0];
        //previous stolen
        int p1 = wealth[0];
        //last max stolen
        int p2 = Math.max(wealth[1], p1);
        for (int i = 2; i < wealth.length; i++) {
            //we either steal current and (n - 2) stolen or we last stolen max
            int maxSteal = Math.max(wealth[i] + p1, p2);
            p1 = p2;
            p2 = maxSteal;
        }
        return p2;
    }

    // Iterative + 2 variables (bottom-up)
    //O(n) time | O(1) space
    private int findMaxStealIterativeBottomup(int[] wealth) {
        int start = 0; int end = wealth.length - 1;
        int preStolen = 0, curStolen = 0;
        //as we just need to exclude adjacent house
        //two pointers prev steal and current steal allows us to track which we need to pick
        //[2, 5, 1, 3, 6, 2, 4] with previous and current steal both pointing at 0
        //[n - 2] = prev stolen, [n - 1] = cur stolen, [n] is current steal
        //i=0 curStolen = 2 as there was nothing stolen previously and previous max is still 0
        //i=1 curStolen = 5, we either keep previous steal + curr wealth or pick last steal
        // [n - 2] steal + [n] steal or [n - 1] steal
        //i=2 current steal = 5
        //i=3 we can steal 5 + 3 = 8
        //i=4 we can steal previous and current 5 + 6 = 11
        //i=5 current steal = 11
        //i=6 current steal = 11 + 4 = 15
        while (start <= end) {
            int holdWealth = curStolen;
            curStolen = Math.max(wealth[start] + preStolen, curStolen);
            preStolen = holdWealth;
            start++;
        }
        return curStolen;
    }

    public static void main(String[] args) {
        HouseThief ht = new HouseThief();
        int[] wealth = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxStealTopDown(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxStealTopDown(wealth));

        wealth = new int[] {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxSteal(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxSteal(wealth));

        wealth = new int[] {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxStealOptimized(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxStealOptimized(wealth));

        wealth = new int[] {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxStealIterativeBottomup(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxStealIterativeBottomup(wealth));
    }

}
