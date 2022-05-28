package org.javainaction.binarysearch;

/**
 * Let's call an array arr a mountain if the following properties hold:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * Given an integer array arr that is guaranteed to be a mountain, return any i such
 * that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [0,1,0]
 * Output: 1
 * Example 2:
 *
 * Input: arr = [0,2,1,0]
 * Output: 1
 * Example 3:
 *
 * Input: arr = [0,10,5,2]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * arr is guaranteed to be a mountain array.
 *
 *
 * Follow up: Finding the O(n) is straightforward, could you find an O(log(n)) solution?
 */
public class PeakIndexMountainArray {

    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int middle = (left + right) / 2;
            if (arr[middle] < arr[middle + 1]) left = middle + 1; //this is always the peak
            else right = middle;
        }
        return left;
    }

    public static void main(String[] args){
        System.out.println("[0,2,1,0] peak is at " + peakIndexInMountainArray(new int[]{0,2,1,0}));
        System.out.println("[0,1,0] peak is at " + peakIndexInMountainArray(new int[]{0,1,0}));
        System.out.println("[0,10,5,2] peak is at " + peakIndexInMountainArray(new int[]{0,10,5,2}));
    }
}
