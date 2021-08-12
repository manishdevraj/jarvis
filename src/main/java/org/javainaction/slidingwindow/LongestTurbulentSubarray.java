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
    public static int maxTurbulenceSize(int[] arr) {
        if (arr  == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int prev_comparison = 0;
        int max = 1;
        int count = 0;
        // 9 > 4 > 2 < 10 > 7 < 8 = 8 > 1 < 9
        //   +   +   -    +   -   =   +   -
        //     ------------------
        // this is our flipping sequence
        // at start when we found + we take count as 2
        // then at 4 > 2 also we need reset as they are not flipping but reset is still 2, because 4 > 2
        // up to 8 we found flipping sequence and we could count up to 5
        // at second 8 we need to reset to 1 and then we never found maximum turbulent subarray length
        for(int i = 1; i < arr.length; i++) {
            //this compares with prev comparison results
            //if they are not below 0 it means it is either matching = 0
            //or are not flipping comparisons -1 * -1 or 1 * 1
            //else flipping comparison so increment the turbulent array by 1
            int current_comparison = Integer.compare(arr[i - 1], arr[i]);
            if (prev_comparison * current_comparison < 0) {
                count++;
            } else {
                //we came here because we did either are at starting or we lost flipping sequence and need to reset
                //reset based on result of two integers at current index
                //either they are same so reset to 1 or increasing
                //or decreasing sequence so reset to to 2
                count = current_comparison != 0 ? 2 : 1;
            }
            prev_comparison = current_comparison;
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        int maxTurbulenceSize = LongestTurbulentSubarray.maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9});
        System.out.println("{9,4,2,10,7,8,8,1,9} max turbulent window size in array : " + maxTurbulenceSize);
        maxTurbulenceSize = LongestTurbulentSubarray.maxTurbulenceSize(new int[]{4,8,12,16});
        System.out.println("{4,8,12,16} max turbulent window size in array : " + maxTurbulenceSize);
    }
}
