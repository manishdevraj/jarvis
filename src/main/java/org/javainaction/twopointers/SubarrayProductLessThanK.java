package org.javainaction.twopointers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array with positive numbers and a target number, find all of its contiguous subarrays whose product is
 * less than the target number.
 *
 * Example 1:
 *
 * Input: [2, 5, 3, 10], target=30
 * Output: [2], [5], [2, 5], [3], [5, 3], [10]
 * Explanation: There are six contiguous subarrays whose product is less than the target.
 * Example 2:
 *
 * Input: [8, 2, 6, 5], target=50
 * Output: [8], [2], [8, 2], [6], [2, 6], [5], [6, 5]
 * Explanation: There are seven contiguous subarrays whose product is less than the target.
 */
public class SubarrayProductLessThanK {
    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int product = 1, left = 0;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            //slide window when we have product more than target
            while (product >= target && left <= right) // left <= right is faster than left < arr.length
                product /= arr[left++];
            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from lef to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only arr[right] and then extend it
            /**
             * If we are not finding actual subarray but just count of them then instead of looping from right to left
             * total += right - left + 1;
             */
            //this makes sure that we get both individual element from right and its sibling whose product <= target
            //when right = 1, left = 0
            // we add [5] and we also use same templist to add 2 to make it as [5, 2]
            List<Integer> tempList = new LinkedList<>();
            for (int i = right; i >= left; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[] { 8, 2, 6, 5 }, 50));
    }
}
