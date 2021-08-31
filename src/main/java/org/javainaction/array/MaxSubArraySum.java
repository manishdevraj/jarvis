package org.javainaction.array;

/**
 * Find the maximum sub array sum for a given int array. Array might contain negative values.
 *
 * Input: [1, -3, 2 , 1, -1]
 * Output: 3, Explanation 2 + 1 as we cannot include -3 and -1 because it will reduce the sum
 */
public class MaxSubArraySum {
    //O(n) time | O(1) space
    private static int findMaxSubArraySum(int[] array) {
        int prevSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : array) {
            //add previous value with current value or reset at current value
            int sum = Math.max(num, num + prevSum);
            //store for running sum
            prevSum = sum;
            //take max of sum or current sum
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] arg) {
        int[] array = new int[] {1, -3, 2 , 1, -1};
        System.out.println(findMaxSubArraySum(array));
        System.out.println(findMaxSubArraySum(new int[] {-1, -1, -1}));
        System.out.println(findMaxSubArraySum(new int[] {-1, -3, -2}));
        System.out.println(findMaxSubArraySum(new int[] {1, 3, 2}));
    }
}
