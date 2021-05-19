package org.javainaction.slidingwindow;

/**
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 *
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 *
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
 *
 * For i <= k < j:
 * arr[k] > arr[k + 1] when k is odd, and
 * arr[k] < arr[k + 1] when k is even.
 * Or, for i <= k < j:
 * arr[k] > arr[k + 1] when k is even, and
 * arr[k] < arr[k + 1] when k is odd.
 *
 *
 * Example 1:
 *
 * Input: arr = [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 * Example 2:
 *
 * Input: arr = [4,8,12,16]
 * Output: 2
 * Example 3:
 *
 * Input: arr = [100]
 * Output: 1
 */
public class LongestTurbulentSubarray {
    public static void main(String[] args) {
        int maxTurbulenceSize = LongestTurbulentSubarray.maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9});
        System.out.println("{9,4,2,10,7,8,8,1,9} max turbulent window size in array : " + maxTurbulenceSize);
        maxTurbulenceSize = LongestTurbulentSubarray.maxTurbulenceSize(new int[]{4,8,12,16});
        System.out.println("{4,8,12,16} max turbulent window size in array : " + maxTurbulenceSize);
    }
    public static int maxTurbulenceSize(int[] arr) {
        if (arr  == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int prev_comparison = 0;
        int max = 1;
        int count = 0;
        for(int i = 1; i < arr.length; i++) {
            //this compares with prev comparison results
            //if they are not below 0 it means it is either matching = 0
            //or are not flipping comparisons -1 * -1 or 1 * 1
            //else increment the turbulent array by 1
            if (prev_comparison * (Integer.compare(arr[i-1], arr[i])) < 0) {
                count++;
            } else {
                //reset based on result of two integers at current index
                //either they are same so reset to 1 or increasing
                //or decreasing sequence so reset to to 2
                count = Integer.compare(arr[i-1], arr[i]) != 0 ? 2 : 1;
            }
            prev_comparison = Integer.compare(arr[i-1], arr[i]);
            max = Math.max(max, count);
        }
        return max;
    }
}
