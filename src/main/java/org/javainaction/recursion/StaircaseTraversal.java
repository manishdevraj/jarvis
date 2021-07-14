package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can take max steps. In how many distinct ways can you climb to the top?
 *
 *
 * Example 1:
 *
 * Input: height = 2, maxSteps = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: height = 5, maxSteps = 2
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step + 1 step + 1 step
 * 2. 2 steps + 1 step + 1 step  + 1 step
 * 3. 1 step + 2 steps + 1 step  + 1 step
 * 4. 1 step + 1 step + 2 steps  + 1 step
 * 5. 1 step + 1 step + 1 step + 2 steps
 * 6. 1 step + 2 steps + 2 steps
 * 7. 2 steps + 2 steps + 1 step
 * 8. 2 steps + 1 step + 2 steps
 * @see org.javainaction.dp.fibonacci.Staircase
 * @see org.javainaction.dp.fibonacci.StaircaseFee
 */
public class StaircaseTraversal {
    //O(n * k) time | O(n) space where n is height and k is max steps
    public static int staircaseTraversalMemoize(int height, int maxSteps) {
        Map<Integer, Integer> memoize = new HashMap<>();
        memoize.put(0, 1);
        memoize.put(1, 1);
        return numberOfWaysToTop(height, maxSteps,memoize);
    }

    public static int numberOfWaysToTop(int height, int maxSteps, Map<Integer, Integer> memoize) {
        if (memoize.containsKey(height)) return memoize.get(height);
        int numberOfWays = 0;
        //we can climb either up to max steps or up to height
        for(int steps = 1; steps < Math.min(maxSteps, height) + 1; steps++) {
            numberOfWays += numberOfWaysToTop(height - steps, maxSteps, memoize);
        }
        memoize.put(height, numberOfWays);
        return numberOfWays;
    }

    //O(n * k) time | O(n) space - where n is the height and k is max steps
    public static int staircaseTraversalDp(int height, int maxSteps) {
        int[] waysToTop = new int[height + 1];
        waysToTop[0] = 1;
        waysToTop[1] = 1;
        for(int currentHeight = 2; currentHeight < height + 1; currentHeight++) {
            int step = 1;
            while (step <= maxSteps && step <= currentHeight) {
                waysToTop[currentHeight] += waysToTop[currentHeight - step] ;
                step++;
            }
        }
        return waysToTop[height];
    }

    //O(n) time | O(n) space using sliding window
    public static int staircaseTraversalSidingWindow(int height, int maxSteps) {
        int numberOfWays = 0;
        List<Integer> waysToTop = new ArrayList<>();
        waysToTop.add(1);

        for (int left = 1; left < height + 1; left++) {
            int startOfWindow = left - maxSteps - 1;
            int endOfWindow = left - 1;

            if (startOfWindow >= 0) {
                numberOfWays -= waysToTop.get(startOfWindow);
            }

            numberOfWays += waysToTop.get(endOfWindow);
            waysToTop.add(numberOfWays);

        }
        return waysToTop.get(height);
    }

    public static void main(String[] args){
        int stairs = 4;
        int maxSteps = 2;
        int expected = 5;
        System.out.println("Staircase with 4 stairs nad maxSteps of 2 : "
                + staircaseTraversalMemoize(stairs, maxSteps));

        System.out.println("Staircase with 4 stairs nad maxSteps of 2 DP solution : "
                + staircaseTraversalDp(stairs, maxSteps));

        System.out.println("Staircase with 4 stairs nad maxSteps of 2 sliding window : "
                + staircaseTraversalSidingWindow(stairs, maxSteps));

        System.out.println("Staircase with 5 stairs nad maxSteps of 2 : "
                + staircaseTraversalMemoize(5, maxSteps));

        System.out.println("Staircase with 5 stairs nad maxSteps of 2 DP solution : "
                + staircaseTraversalDp(5, maxSteps));

        System.out.println("Staircase with 5 stairs nad maxSteps of 2 sliding window : "
                + staircaseTraversalSidingWindow(5, maxSteps));
    }
}
