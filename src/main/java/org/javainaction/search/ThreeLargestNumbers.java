package org.javainaction.search;

import java.util.Arrays;

/**
 * Given an array find 3 largest number in that array
 *
 */
public class ThreeLargestNumbers {
    public static int[] findThreeLargestNumbers(int[] array) {
        // O(n) time and O(1) space
        return searchThreeLargestNumbers(array);
    }

    public static int[] searchThreeLargestNumbers(int[] array) {
        int thirdLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int firstLargest = Integer.MIN_VALUE;
        for(int i=0; i < array.length; i++)	{
            if(array[i] >= thirdLargest) {
                firstLargest = secondLargest;
                secondLargest = thirdLargest;
                thirdLargest = array[i];
            } else if(array[i] >= secondLargest) {
                firstLargest = secondLargest;
                secondLargest = array[i];
            } else if(array[i] >= firstLargest) {
                firstLargest = array[i];
            }
        }
        return new int[] {firstLargest, secondLargest, thirdLargest};
    }

    public static void main(String[] args){
        int[] expected = {18, 141, 541};
        System.out.println(
                Arrays.toString(ThreeLargestNumbers
                        .findThreeLargestNumbers(new int[]{141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7})));
    }
}
