package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaircaseTraversal {
    //O(n * k) time | O(n) space where n is height and k is max steps
    public static int staircaseTraversalMemoize(int height, int maxSteps) {
        Map<Integer, Integer> memoize = new HashMap<>();
        memoize.put(0, 1);
        memoize.put(1, 1);
        return numberOfWaysToTop(height, maxSteps,memoize);
    }

    public static int numberOfWaysToTop(int height, int maxSteps,
                                 Map<Integer, Integer> memoize) {
        if (memoize.containsKey(height)) return memoize.get(height);
        int numberOfWays = 0;
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
    public static int staircaseTraversal(int height, int maxSteps) {
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
        int actual = staircaseTraversalMemoize(stairs, maxSteps);
        System.out.println("Staircase with 4 stairs nad maxSteps of 2 : " + actual);
        System.out.println("Staircase with 4 stairs nad maxSteps of 2 DP solution : "
                + staircaseTraversalDp(stairs, maxSteps));
    }
}
