package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Permutations2 {
    // Upper bound O(n^2 *n!) time | O(n*n!) space
    // Roughly O(n*n!) time | O(n*n!) space
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        findAllUniquePermutation(nums, new ArrayList<>(), permutations, visited);
        return permutations;
    }

    public static void findAllUniquePermutation(int[] array, List<Integer> currentPermutations,
                                      List<List<Integer>> permutations, boolean[] visited){
        if (array.length == currentPermutations.size()) {
            permutations.add(new ArrayList<>(currentPermutations));
        } else {
            for(int i = 0; i < array.length; i++){
                if (visited[i]) continue;
                if (i > 0 && array[i] == array[i - 1] && !visited[i - 1]) continue;
                visited[i] = true;
                currentPermutations.add(array[i]);
                findAllUniquePermutation(array, currentPermutations, permutations, visited);
                currentPermutations.remove(currentPermutations.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        //[[1,1,2], [1,2,1], [2,1,1]]
        System.out.println("{ 1, 1, 2 } are all the permutations: " + permuteUnique(new int[] { 1, 1, 2 }));
        //[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        System.out.println("{ 1, 2, 3 } are all the permutations: " + permuteUnique(new int[] { 1, 2, 3 }));
    }
}
