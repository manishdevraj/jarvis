package org.javainaction.slidingwindow;

import java.util.Arrays;

/**
 * Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s, find the length of
 * the longest contiguous subarray having all 1s.
 *
 * Example 1:
 *
 * Input: Array=[0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], k=2
 * Output: 6
 * Explanation: Replace the '0' at index 5 and 8 to have the longest contiguous subarray of 1s having length 6.
 * Example 2:
 *
 * Input: Array=[0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1], k=3
 * Output: 9
 * Explanation: Replace the '0' at index 6, 9, and 10 to have the longest contiguous subarray of 1s having length 9.
 * @see MaxConsecutiveOnes3 for first solution
 * @see CharacterReplacement for second solution
 */
public class ReplacingOnes {
    public static int findLength(int[] arr, int K) {
        int i = 0, j = 0;
        //we keep on iterating right, we see every iteration left and right is added by 1
        //which makes the distance between right and left same.
        //The distance between right and left would change again if there is a longer subarray exist.
        for(; i < arr.length && j < arr.length; ++j){
            if (arr[j] == 0) K--;
            if (K < 0 && arr[i++] == 0) K++;
        }
        return j - i;
    }

    public static int replaceOneUsingCounterSlidingWindow(int[] arr, int k) {
        int windowStart = 0, maxLength = 0, maxOnesCount = 0;
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if (arr[windowEnd] == 1) maxOnesCount++;

            // current window size is from windowStart to windowEnd, overall we have a maximum of 1s
            // repeating a maximum of 'maxOnesCount' times, this means that we can have a window with
            // 'maxOnesCount' 1s and the remaining are 0s which should replace with 1s.
            // now, if the remaining 0s are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' Os
            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                if (arr[windowStart] == 1) maxOnesCount--;
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}) + " : "
                + findLength(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
        System.out.println(Arrays.toString(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}) + " : "
                + findLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));

        System.out.println(Arrays.toString(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}) + " : "
                + replaceOneUsingCounterSlidingWindow(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
        System.out.println(Arrays.toString(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}) + " : "
                + replaceOneUsingCounterSlidingWindow(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));
    }
}
