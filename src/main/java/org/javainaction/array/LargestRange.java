package org.javainaction.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Find a largest range possible in a given array.
 *
 * Return value wih two inputs {x, y} where x being start of the range and y being end of the range.
 *
 * It is not needed for a range to be sorted in an array.
 *
 * {2, 6} for a range of {2, 3, 4, 5, 6} but not necessarily in a sorted way
 *
 * Input: [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
 * Output: [0, 7]
 *
 */
public class LargestRange {
    // O(n) time | O(n) space
    public static int[] largestRange(int[] array) {
        int[] bestRange = new int[2];
        int longest = 0;
        Map<Integer, Boolean> rangeMap = new HashMap<>();
        //use frequency map to store expected range in an give array
        for (int num : array) {
            rangeMap.put(num, true);
        }

        for (int num : array) {
            //avoid visited elements
            if (!rangeMap.get(num)) {
                continue;
            }
            rangeMap.put(num, false);

            int current = 1;
            int left = num - 1;
            int right = num + 1;

            //search range to the left
            while (rangeMap.containsKey(left)) {
                rangeMap.put(left, false);
                left--;
                current++;
            }

            //search range to the right
            while (rangeMap.containsKey(right)) {
                rangeMap.put(right, false);
                right++;
                current++;
            }

            if (current > longest) {
                longest = current;
                bestRange = new int[] {left + 1, right - 1};
            }
        }
        return bestRange;
    }

    public static void main(String[] args) {
        int[] expected = {0, 7};
        System.out.println(Arrays.toString(largestRange(new int[] {1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6})));
    }
}
