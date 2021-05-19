package org.javainaction.subsets;

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
 * n
 * !
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

    public static void findAllPermutations (int i, List<Integer> array, List<List<Integer>> permutations) {
        if (array.size() - 1 == i) {
            permutations.add(new ArrayList<>(array));
        } else {
            for(int j = i; j < array.size(); j++){
                swap(array, i, j);
                findAllPermutations(i + 1, array, permutations);
                swap(array, i, j);
            }
        }
    }

    public static void swap(List<Integer> array, int i, int j) {
        Integer temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Permutations.findPermutations(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }
}
