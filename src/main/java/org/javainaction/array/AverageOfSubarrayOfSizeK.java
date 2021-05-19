package org.javainaction.array;

import java.util.Arrays;

/**
 * Given an array, find the average of all contiguous subarrays of size ‘K’ in it.
 * Let’s understand this problem with a real input:
 *
 * Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
 * Output: [2.2, 2.8, 2.4, 3.6, 2.8]
 *
 */
public class AverageOfSubarrayOfSizeK {
    public static void main(String[] args) {
        double[] result = AverageOfSubarrayOfSizeK.findAverages(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }

    private static double[] findAverages(int K, int[] array) {
        double[] result = new double[array.length - K + 1];
        double windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < array.length; windowEnd++){
            windowSum += array[windowEnd];
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (windowEnd >= K - 1) {
                double average = windowSum / K;
                result[windowStart] = average;
                windowSum -= array[windowStart];
                windowStart++;
            }
        }
        return result;
    }
}
