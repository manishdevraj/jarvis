package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class PowerSet2 {
    // O(n*2^n) time | O(n*2^n) space
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> powerset = new ArrayList<>();
        powerset.add(Collections.emptyList());
        //[], [1], [2], [1, 2] after this all we are doing is adding 3 to each existing subsets
        //[3], [1, 3], [2, 3], [1, 2, 3]
        int size = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            //make sure we do not have similar numbers
            index = i >= 1 && nums[i] == nums[i - 1] ? size : 0;
            size = powerset.size();
            for (int j = index; j < size; j++) {
                List<Integer> newSubset = new ArrayList<>(powerset.get(j));
                newSubset.add(nums[i]);
                powerset.add(newSubset);
            }
        }
        return powerset;
    }

    public static void main(String[] args) {
        System.out.println("{1, 2, 2} powerset : " + subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println("{0} powerset : " + subsetsWithDup(new int[]{0}));
    }
}
