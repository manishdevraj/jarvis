package org.javainaction.twopointers;

import java.util.List;

/**
 * Given two non empty integer array, write a function that determines if second array is sequence of first array
 * [1, 3, 4] form a subsequence [1, 2, 3, 4] and so does [2, 4]
 */
public class ValidSubsequence {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        //O(n) time | O(1) space
        int arrayIdx = 0;
        int right = array.size();
        int seqIdx = 0;
        int j = sequence.size();
        while (arrayIdx < right && seqIdx < j) {
            if (array.get(arrayIdx) == sequence.get(seqIdx)) {
                seqIdx++;
            }
            arrayIdx++;
        }
        return seqIdx == j;
    }
}
