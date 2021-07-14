package org.javainaction.dp.fibonacci;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a staircase with ‘n’ steps and an array of ‘n’ numbers representing the fee that you have to pay if you take
 * the step. Implement a method to calculate the minimum fee required to reach the top of the staircase
 * (beyond the top-most step). At every step, you have an option to take either 1 step, 2 steps, or 3 steps.
 * You should assume that you are standing at the first step.
 *
 * Example 1:
 *
 * Number of stairs (n) : 6
 * Fee: {1,2,5,2,1,2}
 * Output: 3
 * Explanation: Starting from index '0', we can reach the top through: 0->3->top
 * The total fee we have to pay will be (1+2).
 * Example 2:
 *
 * Number of stairs (n): 4
 * Fee: {2,3,4,5}
 * Output: 5
 * Explanation: Starting from index '0', we can reach the top through: 0->1->top
 * The total fee we have to pay will be (2+3).
 * @see Staircase
 * @see org.javainaction.recursion.StaircaseTraversal
 */
public class StaircaseFee {
    public static  int findMinFee(int[] fee) {
        int dp[] = new int[fee.length];
        //we start at 0th step
        return findMinFeeRecursive(dp, fee, 0);
    }

    private static int findMinFeeRecursive(int[] dp, int[] fee, int index) {
        if (index > fee.length - 1) return 0;

        if (dp[index] == 0) {
            int step1 = findMinFeeRecursive(dp, fee, index + 1);
            int step2 = findMinFeeRecursive(dp, fee, index + 2);
            int step3 = findMinFeeRecursive(dp, fee, index + 3);
            dp[index] = fee[index] + Math.min(step1, Math.min(step2, step3));
        }
        return dp[index];
    }

    private static int findMinFeedBottomup(int[] fee) {
        int dp[] = new int[fee.length + 1]; // +1 to handle the 0th step
        //we start at 0th step
        dp[0] = 0; // if there are no steps, we dont have to pay any fee
        dp[1] = fee[0]; // only one step, so we have to pay its fee
        // for 2 or 3 steps staircase, since we start from the first step so we have to pay its fee
        // and from the first step we can reach the top by taking two or three steps, so we don't
        // have to pay any other fee.
        dp[2] = dp[3] = fee[0];
        for (int i = 3; i < fee.length; i++) {
            dp[i + 1] = Math.min(fee[i] + dp[i], Math.min(fee[i - 1] + dp[i - 1], fee[i - 2] + dp[i - 2]));
        }
        return dp[fee.length];
    }

    //O(n * k) time | O(n) space where n is height and k is max steps
    public static int staircaseFeeMemoize(int[] fee) {
        Map<Integer, Integer> memoize = new HashMap<>();
        //we start at 0th step
        // if there are no steps, we don't have to pay any fee
        memoize.put(0, 0);
        // only one step, so we have to pay its fee
        memoize.put(1, fee[0]);
        // only one step, so we have to pay its fee
        // for 2 or 3 steps staircase, since we start from the first step so we have to pay its fee
        // and from the first step we can reach the top by taking two or three steps, so we don't
        // have to pay any other fee.
        memoize.put(2, fee[0]);
        memoize.put(3, fee[0]);
        return minFeeToTop(memoize, fee, fee.length);
    }

    public static int minFeeToTop(Map<Integer, Integer> memoize, int[] fee, int height) {
        if (memoize.containsKey(height)) return memoize.get(height);
        int numberOfWays = Integer.MAX_VALUE;
        int maxSteps = 3;
        //we can climb either up to max steps or up to height
        for(int steps = 1; steps < Math.min(maxSteps, fee.length) + 1; steps++) {
            numberOfWays = Math.min(numberOfWays, fee[steps] + minFeeToTop(memoize, fee, height - steps));
        }
        memoize.put(height, numberOfWays);
        return numberOfWays;
    }

    public static void main(String[] args) {
        Staircase sc = new Staircase();
        int[] fee = {1,2,5,2,1,2};
        System.out.println(findMinFee(fee));
        fee = new int[]{2,3,4,5};
        System.out.println(findMinFee(fee));

        fee = new int[]{1,2,5,2,1,2};
        System.out.println(findMinFeedBottomup(fee));
        fee = new int[]{2,3,4,5};
        System.out.println(findMinFeedBottomup(fee));

        fee = new int[]{1,2,5,2,1,2};
        System.out.println(staircaseFeeMemoize(fee));
        fee = new int[]{2,3,4,5};
        System.out.println(staircaseFeeMemoize(fee));
    }
}
