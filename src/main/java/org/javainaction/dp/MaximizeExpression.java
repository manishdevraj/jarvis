package org.javainaction.dp;

/**
 * We are given an array and we need to find maximum value of expression a - b + c - d where
 * a,b,c,d represents indices in array such that a < b < c < d
 *
 * For input {3, 6, 1, -3, 2, 7}
 * Output would be a=1,b=4,c=5,d=6
 * 6 - (-3) + 2 - 7 = 4
 *
 *
 */
public class MaximizeExpression {
    /**
     * Naive solution would be to try for each and every tuple and that will take us O(n^4) time complexity
     *
     */
    //O(n) time | O(n) space
    public int maximizeExpression(int[] array) {
        if (array == null || array.length < 4) return 0;

        int[] maxOfA = new int[array.length];

        maxOfA[0] = array[0];

        //max of a
        for (int i = 1; i < array.length; i++) {
            int max = Math.max(maxOfA[i - 1], array[i]);
            maxOfA[i] = max;
        }

        //max a - b
        int[] maxOfB = new int[array.length];
        maxOfB[0] = Integer.MIN_VALUE;

        for (int i = 1; i < array.length; i++) {
            int max = Math.max(maxOfB[i - 1], maxOfA[i - 1] - array[i]);
            maxOfB[i] = max;
        }

        //max a - b + c
        int[] maxOfC = new int[array.length];
        maxOfC[0] = maxOfC[1] = Integer.MIN_VALUE;

        for (int i = 2; i < array.length; i++) {
            int max = Math.max(maxOfC[i - 1], maxOfB[i - 1] + array[i]);
            maxOfC[i] = max;
        }

        //max a- b + c - d
        int[] maxOfD = new int[array.length];
        maxOfD[0] = maxOfD[1] = maxOfD[2] = Integer.MIN_VALUE;

        for (int i = 3; i < array.length; i++) {
            int max = Math.max(maxOfD[i - 1], maxOfC[i - 1] - array[i]);
            maxOfD[i] = max;
        }

        return maxOfD[array.length - 1];
    }

    public static void main(String[] arg) {
        int[] input = new int[] {3, 6, 1, -3, 2, 7};
        int expected = 4;
        var actual = new MaximizeExpression().maximizeExpression(input);
        System.out.println(actual);
    }
}
