package org.javainaction.bst;

import java.util.ArrayList;
import java.util.List;
import org.javainaction.array.KClosestElements;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 */
public class FindKClosestElements {
    public static List<Integer> findClosestElements(int[] arr, int K, int X) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - K;

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            //check elements absolute difference and accordingly shift binary search
            if (X - arr[mid] > arr[mid + K] - X) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // get all first smallest difference elements
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + K; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = findClosestElements(new int[] { 5, 6, 7, 8, 9 }, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = findClosestElements(new int[] { 1, 2, 3, 4, 5 }, 4, 3);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = findClosestElements(new int[] { 1, 2, 3, 4, 5 }, 4, -1);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    }
}
