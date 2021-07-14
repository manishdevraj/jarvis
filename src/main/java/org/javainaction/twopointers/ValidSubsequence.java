package org.javainaction.twopointers;

import java.util.Arrays;
import java.util.List;

/**
 * Given two non empty integer array, write a function that determines if second array is sequence of first array
 * [1, 3, 4] form a subsequence [1, 2, 3, 4] and so does [2, 4]
 *
 * Example array [5, 1, 22, 25, 6, -1, 8, 10] and sequence [1, 6, -1, 10]
 * Output : true
 */
public class ValidSubsequence {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        //O(n) time | O(1) space
        int arrLeft = 0;
        int arrRight = array.size();
        int seqLeft = 0;
        int seqRight = sequence.size();
        while (arrLeft < arrRight && seqLeft < seqRight) {
            if (array.get(arrLeft).equals(sequence.get(seqLeft))) {
                seqLeft++;
            }
            arrLeft++;
        }
        return seqLeft == seqRight;
    }

    public static void main(String[] args) {
        var array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        var sequence = Arrays.asList(1, 6, -1, 10);
        System.out.println(array + " and " + sequence + " is valid subsequence : "
                + isValidSubsequence(array, sequence));
    }
}
