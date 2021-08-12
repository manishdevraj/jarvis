package org.javainaction.twopointers;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers and an integer target, return indices of the two numbers such that they add up to
 * target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * @see PairSumsWithDuplicates where we might have duplicate elements
 * @see PairWithTargetSum give indices
 */
public class TwoSum {
    // O(n) with Map space
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        var map = new HashMap<Integer, Integer>();
        for(int i=0; i < nums.length; i++) {
            int find = target - nums[i];
            if (map.containsKey(find)) {
                result[0] = map.get(find);
                result[1] = i;
            }
            map.put(nums[i], i);
        }
        return result;

    }

    //O(nlog(n)) time and O(1) space
    //This can provide tuple but not indices
    public static int[] twoSumByDivideAndConcur(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) return new int[]{ nums[left] , nums[right] };
            else if(sum < target) left++;
            else right--;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] result = twoSum(new int[] { 2,7,11,15 }, 9);
        System.out.println("{2,7,11,15} with target sum of 9 at: " + Arrays.toString(result));
        result = twoSum(new int[] { 3,2,4 }, 6);
        System.out.println("{3,2,4} with target sum of 6 at: " + Arrays.toString(result));
        result = twoSum(new int[] { 3,2,0,3 }, 6);
        System.out.println("{3,2,0,3} with target sum of 6 at: " + Arrays.toString(result));

        result = twoSumByDivideAndConcur(new int[] { 2,7,11,15 }, 9);
        System.out.println(" twoSumByDivdeAndConcur {2,7,11,15} with target sum of 9 at: " + Arrays.toString(result));
        result = twoSumByDivideAndConcur(new int[] { 3,2,4 }, 6);
        System.out.println(" twoSumByDivdeAndConcur {3,2,4} with target sum of 6 at: " + Arrays.toString(result));
        result = twoSumByDivideAndConcur(new int[] { 3,2,0,3 }, 6);
        System.out.println(" twoSumByDivdeAndConcur {3,2,0,3} with target sum of 6 at: " + Arrays.toString(result));

    }

}
