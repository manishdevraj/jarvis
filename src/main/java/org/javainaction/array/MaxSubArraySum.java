package org.javainaction.array;

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
