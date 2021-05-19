package org.javainaction.array;

import java.util.Arrays;

/**
 * Given two integer non empty array find the smallest absolute difference between each element from both arrays making a pair.
 * For e.g. Absolute difference between -5 and 5 is 10 and abs difference between 5 and 4 is 1.
 */
public class SmallestDifference {
    public static void main(String[] args) {
        int[] arrayOne = new int[]{-1, 5, 10, 20, 28, 3};
        int[] arrayTwo = new int[]{26, 134, 135, 15, 17, 175};
        System.out.println("Absolute difference pair between " + Arrays.toString(arrayOne) + "" +
                "and " + Arrays.toString(arrayTwo) + " is " + Arrays.toString(SmallestDifference
                .smallestDifference(arrayOne, arrayTwo)));

    }
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        // O(nlong(n)) + O(mlog(m)) time | O(1) space.
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);
        int idxOne = 0;
        int idxTwo = 0;
        int smallest = Integer.MAX_VALUE;
        int diffSoFar = Integer.MAX_VALUE;
        int[] result = new int[2];
        while(idxOne < arrayOne.length && idxTwo < arrayTwo.length) {
            int left = arrayOne[idxOne];
            int right = arrayTwo[idxTwo];
            if(left < right) {
                diffSoFar = right - left;
                idxOne++;
            } else if(left > right) {
                diffSoFar = left - right;
                idxTwo++;
            } else {
                return new int[] {left, right};
            }
            if(diffSoFar < smallest) {
                smallest = diffSoFar;
                result = new int[] {left, right};
            }
        }
        return result;
    }
}
