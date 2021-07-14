package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 *
 * Example 1:
 *
 * Input: [1, 3, 3]
 * Output: [], [1], [3], [1,3], [3,3], [1,3,3]
 * Example 2:
 *
 * Input: [1, 5, 3, 3]
 * Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3], [3,3], [1,3,3], [3,3,5], [1,5,3,3]
 * @see PowerSet2 In this case we skipped duplicate by making size = 0
 *
 */
public class SubsetWithDuplicates {
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        // start by adding the empty subset
        subsets.add(new ArrayList<>());
        int startIdx = 0, endIdx = 0;
        for (int j = 0; j < nums.length; j++) {
            startIdx = 0;
            // we will take all existing subsets and insert the current number in them to create new subsets
            if (j > 0 && nums[j] == nums[j - 1]) {
                // In this case we just need to slide window such that duplicate permutation is skipped
                // but rest are added
                // [] [1] [3] [1,3] when second 3 comes, we do not add to empty set making it [] [1] [3] [3] [1,3]
                // We slide start such that it is added to 1 and 3 [], [1], [3], [1,3], [3,3], [1,3,3]
                startIdx = endIdx + 1;
            }
            endIdx = subsets.size() - 1;
            for (int i = startIdx; i <= endIdx; i++) {
                // create a new subset from the existing subset and insert the current element to it
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(nums[j]);
                subsets.add(set);
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetWithDuplicates.findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
