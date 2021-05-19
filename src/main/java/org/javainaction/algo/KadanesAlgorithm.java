package org.javainaction.algo;

/**
 * Max sub array sum of adj elements
 */
public class KadanesAlgorithm {
    public static int kadanesAlgorithm(int[] array) {
        // O(n) time | O(1) space
        int maxSumHere = array[0];
        int maxSoFar = array[0];
        for(int i = 1; i < array.length; i++){
            maxSumHere = Math.max(array[i], maxSumHere + array[i]);
            maxSoFar= Math.max(maxSoFar, maxSumHere);
        }
        return maxSoFar;
    }
}
