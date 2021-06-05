package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 */
public class Combinations {

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        findAllCombinations(combinations, new ArrayList<>(), 1, n, k);
        return combinations;
    }

    public static void findAllCombinations(List<List<Integer>> combinations,
                                    List<Integer> combos, int start, int end, int k) {
        if (k == 0) {
            combinations.add(new ArrayList<>(combos));
        } else {
            for (int i = start; i <= end; i++) {
                combos.add(i);
                findAllCombinations(combinations, combos, i + 1, end, k - 1);
                //remove element as we are done with its combination
                combos.remove(combos.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("n = 4, k = 2" + combine(4, 2));
        System.out.println("n = 1, k = 1" + combine(1, 1));
    }
}
