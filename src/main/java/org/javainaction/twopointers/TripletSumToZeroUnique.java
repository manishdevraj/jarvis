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
public class TripletSumToZeroUnique {
    private static List<List<Integer>> searchUniqueTriplet(int[] arr) {
        int target = 0;
        var result = new ArrayList<List<Integer>>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            //base duplicate check
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            while (left < right) {
                int potentialMatch = arr[i] + arr[left] + arr[right];
                if (potentialMatch == target) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    left++;
                    right--;
                    //skip duplicate numbers
                    while(left < right && arr[left] == arr[left - 1]) left++;
                    while(left < right && arr[right] == arr[right + 1]) right--;
                }
                else if (potentialMatch < target) left++;
                else right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(searchUniqueTriplet(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
        System.out.println(searchUniqueTriplet(new int[] { -5, 2, -1, -2, 3 }));
    }
}
