package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * Example 3:
 *
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 * Example 4:
 *
 * Input: k = 3, n = 2
 * Output: []
 * Explanation: There are no valid combinations.
 * Example 5:
 *
 * Input: k = 9, n = 45
 * Output: [[1,2,3,4,5,6,7,8,9]]
 * Explanation:
 * 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45
 * There are no other valid combinations.
 * @see Combinations
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        findAllCombinations(combinations, new ArrayList<>(), 1, k, n);
        return combinations;
    }

    public static void findAllCombinations(List<List<Integer>> combinations,
                                    List<Integer> combos, int start, int kCombosAllowed, int targetSum) {
        if (targetSum == 0 && combos.size() == kCombosAllowed) {
            combinations.add(new ArrayList<>(combos));
        } else {
            for (int i = start; i <= 9; i++) {
                combos.add(i);
                findAllCombinations(combinations, combos, i + 1, kCombosAllowed,targetSum - i);
                //remove element as we are done with its combination
                combos.remove(combos.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        var obj = new CombinationSum3();
        System.out.println("k = 3, n = 7 :" + obj.combinationSum3(3, 7));
        System.out.println("k = 3, n = 9 :" + obj.combinationSum3(3, 9));
    }
}
