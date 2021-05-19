package org.javainaction.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 *
 * Example 1:
 *
 * Input: [-3, 0, 1, 2, -1, 1, -2]
 * Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
 * Explanation: There are four unique triplets whose sum is equal to zero.
 * Example 2:
 *
 * Input: [-5, 2, -1, -2, 3]
 * Output: [[-5, 2, 3], [-2, -1, 3]]
 * Explanation: There are two unique triplets whose sum is equal to zero.
 */
public class TripletSumToZero {

    public static List<List<Integer>> searchTriplets(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<>();

        if (arr == null || arr.length < 3) return triplets;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            //if (arr[i] == arr[left]) continue;
            // if first number is bigger than 0, break
            if(arr[i] > 0) break;

            //check duplicate here too
            if(i > 0 && arr[i] == arr[i-1]) continue;

            searchTriplets(arr, i, left, right, triplets, 0);
        }
        return triplets;
    }

    private static void searchTriplets(int[] arr, int i, int left, int right, List<List<Integer>> triplets,
                                       int targetSum) {
        while (left < right) {
            int currentSum = arr[i] + arr[left] + arr[right];
            if (currentSum == targetSum) {
                triplets.add(Arrays.asList(arr[i], arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1]) {
                    left++;
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            }
            else if (currentSum < targetSum) left++;
            else right--;
        }
    }

    public static void main(String[] args) {
        System.out.println(searchTriplets(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
        System.out.println(searchTriplets(new int[] { -5, 2, -1, -2, 3 }));
    }
}
