package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Write a function that accepts an integer and returns its power set
 * Powerset P(X) of set X is set of all subsets of x.
 *
 * For example powerset [1,2] would be
 * [[], [1], [2], [1, 2]]
 *
 * [1, 2 , 3] would be
 * [[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]
 * @see LetterCaseStringPermutation where we used previous permutation, changed case and added to existing permutation
 */
public class PowerSet {
    // O(n*2^n) time | O(n*2^n) space


    public static List<List<Integer>> powerset(List<Integer> array) {
        List<List<Integer>> powerset = new ArrayList<>();
        powerset.add(Collections.emptyList());
        //[], [1], [2], [1, 2] after this all we are doing is adding 3 to each existing subsets
        //[3], [1, 3], [2, 3], [1, 2, 3]
        for (int i = 0; i < array.size(); i++) {
            int length = powerset.size();
            for (int j = 0; j < length; j++) {
                //get all previous permutations and keep adding numbers form array
                //empty list becomes : [1]
                // then it becomes [1], [2] and [1, 2]
                // then it becomes [1], [2], [3] and [1, 2], [1, 3] and [1, 2, 3]
                List<Integer> newSubset = new ArrayList<>(powerset.get(j));
                newSubset.add(array.get(i)); //
                powerset.add(newSubset);
            }
        }
        return powerset;
    }

    public static void main(String[] args) {
        System.out.println("{1, 2} powerset : " + powerset(Arrays.asList(1, 2)));
        System.out.println("{1, 2, 3} powerset : " + powerset(Arrays.asList(1, 2, 3)));
    }
}
