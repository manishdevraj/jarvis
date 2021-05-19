package org.javainaction.array;

public class MaxSubArraySum {


    public static void main(String[] arg) {
        int[] array = new int[] {1, -3, 2 , 1, -1};
        System.out.println(findMaxSubArraySum(array));
        System.out.println(findMaxSubArraySum(new int[] {-1, -1, -1}));
        System.out.println(findMaxSubArraySum(new int[] {-1, -3, -2}));
        System.out.println(findMaxSubArraySum(new int[] {1, 3, 2}));
    }

    //O(n) time | O(1) space
    private static int findMaxSubArraySum(int[] array) {
        int prevSum = array[0];
        int maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            int sum = Math.max(array[i], array[i] + prevSum);
            prevSum = sum;
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
}
