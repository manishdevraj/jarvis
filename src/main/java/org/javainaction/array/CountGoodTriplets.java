package org.javainaction.array;

/**
 * Given an array of integers arr, and three integers a, b and c. You need to find the number of good triplets.
 *
 * A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
 *
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * Where |x| denotes the absolute value of x.
 *
 * Return the number of good triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
 * Output: 4
 * Explanation: There are 4 good triplets: [(3,0,1), (3,0,1), (3,1,1), (0,1,1)].
 * Example 2:
 *
 * Input: arr = [1,1,2,2,3], a = 0, b = 0, c = 1
 * Output: 0
 * Explanation: No triplet satisfies all conditions.
 */
public class CountGoodTriplets {
    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        //idea is to minimize 3rd inner loop by limited our search to see if we have good first condition met
        //|arr[i] - arr[j]| <= a
        //then only find if we have other two conditions valid |arr[j] - arr[k]| <= b and |arr[i] - arr[k]| <= c
        int goodTripletCount = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    for (int k = j + 1; k < arr.length; k++) {
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                            goodTripletCount++;
                        }
                    }
                }
            }
        }
        return goodTripletCount;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{3,0,1,1,9,7};
        System.out.println("{3,0,1,1,9,7}, a = 7, b = 2, c = 3 good triplets exists "
                + countGoodTriplets(arr, 7, 2, 3));
    }
}
