package org.javainaction.array;

import java.util.Arrays;
import java.util.List;

/**
 * Find valid subsquence between two array {1, 3, 2, 4} where subsequence is set of numbers that are not necessarily
 * adjacent for exampple {1, 3, 4} and {2, 4} for are valid subsequence
 */
public class ValidateSubsequence {
    public static void main(String[] args) {
        System.out.println(ValidateSubsequence.isValidSubsequence(Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10),
                Arrays.asList(1, 6, -1, 10)));
        System.out.println(ValidateSubsequence.isValidSubsequence(Arrays.asList(5, 1, 22, -2, -5),
                Arrays.asList(-5, -2)));
        System.out.println(ValidateSubsequence.isValidSubsequence(Arrays.asList(11),
                Arrays.asList(11)));
    }
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        int arrayIndex = 0;
        int seqIndex = 0;
        int sequenceSize = sequence.size();
        while(arrayIndex < array.size() && seqIndex < sequenceSize) {
            if (array.get(arrayIndex) == sequence.get(seqIndex)) {
                seqIndex++;
            }
            arrayIndex++;
        }
        return seqIndex == sequenceSize;
    }
}
