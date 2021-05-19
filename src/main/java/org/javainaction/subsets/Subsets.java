package org.javainaction.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a set with distinct elements, find all of its distinct subsets.
 *
 * Example 1:
 *
 * Input: [1, 3]
 * Output: [], [1], [3], [1,3]
 * Example 2:
 *
 * Input: [1, 5, 3]
 * Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]
 */
public class Subsets {
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        for (int number : nums) {
            int n = subsets.size();
            for (int j = 0; j < n; j++) {
                List<Integer> newSubset = new ArrayList<>(subsets.get(j));
                newSubset.add(number);
                subsets.add(newSubset);
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Subsets.findSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = Subsets.findSubsets(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
