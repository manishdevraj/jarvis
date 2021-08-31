package org.javainaction.array;

/**
 * Given an array of positive numbers and a positive number ‘S’, find the length of the smallest contiguous subarray
 * whose sum is greater than or equal to ‘S’. Return 0, if no such subarray exists.
 *
 * Example 1:
 *
 * Input: [2, 1, 5, 2, 3, 2], S=7
 * Output: 2
 * Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].
 * @see MinSizeSubArraySum
 */
public class MinSizeSubArraySum {
    public static int findMinSubArray(int S, int[] arr) {
        //if (arr == null || arr.length == 0) return -1;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for(int right = 0; right < arr.length; right++) {
            //slide window until we have sum <= S
            sum += arr[right];
            //if we hit our desired sum value then shrink window
            //while because we might still have smaller window
            //[1, 2, 2, 1, 2...] with S = 7
            // at right = 4 we have sum of value 8 when we shrink left we still have sum == S
            // so we shrink more and get new min window of 4
            while (sum >= S) {
                //calculate min window length
                minLength = Math.min(minLength, right - left + 1);
                sum -= arr[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int result = MinSizeSubArraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 });
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 8 });
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(8, new int[] { 3, 4, 1, 1, 6 });
        System.out.println("Smallest subarray length: " + result);
    }
}
