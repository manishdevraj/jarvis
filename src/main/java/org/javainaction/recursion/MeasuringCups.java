package org.javainaction.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given different measuring cups that cannot measure precisely but between a range :
 * low and high : which means it is guaranteed that the cup can measure between low and definitely
 *
 * You are also given two values, low and high and you need to find if you can measure low and high using
 * given range of cups.
 *
 * Examples : Cups {{200, 210}, {450, 465}, {800, 850}}
 * and low = 2100 and high = 2300
 *
 * output : true
 *
 * We can use cup [450, 465] with 4 volumes
 *
 * 1: low = 450 high = 465
 * 2: low 450 + 450 = 900 and high  465 + 465 = 930
 * 3: low 900 + 450 = 1350 and high 930 + 465 = 1395
 * 4: low 1350 + 450 = 1800 and high  1395 + 465 = 1860
 *
 * Then we can use [200, 210] to measure
 * 5: low 1800 + 200 = 2000 and high 1860 + 210 = 2070
 * 6: low 2000 + 200 = 2200 and high 2070 + 210 = 2280
 *
 * We have measure the volume [2200, 2280]
 *
 */
public class MeasuringCups {
    public boolean ambiguousMeasurements(int[][] measuringCups, int low, int high) {
        Map<String, Boolean> memoization = new HashMap<>();
        return canMeasureInRange(measuringCups, memoization, low, high);
    }

    public boolean canMeasureInRange(int[][] measuringCups, Map<String, Boolean> memoization,
                                     int low, int high) {
        //memoization is needed so we have all combinations of low and high cup measurements
        //as it is not compulsory that measurement will happen sequentially that matches the criteria
        String memoizeKey = String.valueOf(low) + ":" + String.valueOf(high);
        if (memoization.containsKey(memoizeKey)) {
            return memoization.get(memoizeKey);
        }

        //we could not accurately measure
        if (low <= 0 && high <= 0) return false;

        boolean canMeasure = false;

        for (int[] cup : measuringCups) {
            //we have measurements within range
            if (low <= cup[0] && cup[1] <= high) {
                canMeasure = true;
                break;
            }
            //we need to make sure we do not go into negative measurements
            int newLow = Math.max(0, low - cup[0]);
            int newHigh = Math.max(0, high - cup[1]);

            canMeasure = canMeasureInRange(measuringCups, memoization, newLow, newHigh);

            if (canMeasure) break;
        }

        memoization.put(memoizeKey, canMeasure);
        return canMeasure;
    }

    public static void main(String[] args) {
        int[][] cups = new int[][] {{200, 210}, {450, 465}, {800, 850}};
        int low = 2100;
        int high = 2300;
        boolean expected = true;
        var actual = new MeasuringCups().ambiguousMeasurements(cups, low, high);
        System.out.println(Arrays.deepToString(cups));
        System.out.println("Can we use? " + actual + " to measure {2100, 2300}");

        cups = new int[][] {{200, 210}};
        low = 10;
        high = 20;
        actual = new MeasuringCups().ambiguousMeasurements(cups, low, high);
        System.out.println(Arrays.deepToString(cups));
        System.out.println("Can we use? " + actual + " to measure {10, 20}");
    }


}
