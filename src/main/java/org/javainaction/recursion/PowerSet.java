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
 */
public class PowerSet {
    // O(n*2^n) time | O(n*2^n) space


    public static List<List<Integer>> powerset(List<Integer> array) {
        List<List<Integer>> powerset = new ArrayList<>();
        powerset.add(Collections.emptyList());
        //[], [1], [2], [1, 2] after this all we are doing is adding 3 to each existing subsets
        //[3], [1, 3], [2, 3], [1, 2, 3]
        for (int number  : array) {
            int length = powerset.size();
            for (int i = 0; i < length; i++) {
                List<Integer> newSubset = new ArrayList<>(powerset.get(i));
                newSubset.add(number);
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
