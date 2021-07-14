package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a set of distinct numbers, find all of its permutations.
 *
 * Permutation is defined as the re-arranging of the elements of the set. For example, {1, 2, 3} has the
 * following six permutations:
 *
 * {1, 2, 3}
 * {1, 3, 2}
 * {2, 1, 3}
 * {2, 3, 1}
 * {3, 1, 2}
 * {3, 2, 1}
 * If a set has ‘n’ distinct elements it will have
 *
 * n! permutations.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: [1,3,5], [1,5,3], [3,1,5], [3,5,1], [5,1,3], [5,3,1]
 */
public class Permutations {
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        findAllPermutations(0, Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList()), permutations);
        return permutations;
    }

    //O(n*n!) time | O(n*n!) space
    public static void findAllPermutations (int start, List<Integer> array, List<List<Integer>> permutations) {
        if (array.size() - 1 == start) {
            permutations.add(new ArrayList<>(array));
        } else {
            for(int j = start; j < array.size(); j++){
                swap(array, start, j);
                findAllPermutations(start + 1, array, permutations);
                swap(array, start, j);
            }
        }
    }

    public static void swap(List<Integer> array, int i, int j) {
        Integer temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    // Upper bound O(n^2 *n!) time | O(n*n!) space
    // Roughly O(n*n!) time | O(n*n!) space
    public static List<List<Integer>> getPermutations(List<Integer> array) {
        List<List<Integer>> permutations = new ArrayList<>();
        getPermutation(array, new ArrayList<Integer>(), permutations);
        return permutations;
    }

    public static void getPermutation(List<Integer> array,
                                      List<Integer> currentPermutations,
                                      List<List<Integer>> permutations){
        if (array.size() == 0 && currentPermutations.size() > 0) {
            permutations.add(currentPermutations);
        } else {
            for(int i = 0; i < array.size(); i++){
                List<Integer> cloneArray = new ArrayList<>(array);
                cloneArray.remove(i);
                List<Integer> newPermutation = new ArrayList<>(currentPermutations);
                newPermutation.add(array.get(i));
                getPermutation(cloneArray, newPermutation, permutations);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("{ 1, 3, 5 } are all the permutations: " + findPermutations(new int[] { 1, 3, 5 }));
        System.out.println("{ 1, 3, 5 } are all the permutations: " + getPermutations(Arrays.asList( 1, 3, 5 )));
    }
}
