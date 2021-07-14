package org.javainaction.array;

/**
 * Find valid subsequence between two array {1, 3, 2, 4} where subsequence is set of numbers that are not necessarily
 * adjacent for example {1, 3, 4} and {2, 4} for are valid subsequence
 */
public class ValidateSubsequence {
    public static boolean isValidSubsequence(int[] array, int[] sequence) {
        int arrayIndex = 0;
        int seqIndex = 0;
        int sequenceSize = sequence.length;
        while(arrayIndex < array.length && seqIndex < sequenceSize) {
            if (array[arrayIndex++] == sequence[seqIndex]) {
                seqIndex++;
            }
        }
        return seqIndex == sequenceSize;
    }

    public static void main(String[] args) {
        System.out.println(ValidateSubsequence.isValidSubsequence(new int[]{5, 1, 22, 25, 6, -1, 8, 10},
                new int[]{1, 6, -1, 10}));
        System.out.println(ValidateSubsequence.isValidSubsequence(new int[]{5, 1, 22, -2, -5},
                new int[]{-5, -2}));
        System.out.println(ValidateSubsequence.isValidSubsequence(new int[]{11},
                new int[]{11}));
    }
}
