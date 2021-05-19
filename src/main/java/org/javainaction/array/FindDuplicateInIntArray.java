package org.javainaction.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Find duplicate value in an array where integers are between 1...N
 * You can manipulate original array if needed
 */
public class FindDuplicateInIntArray {
    public static void main(String[] args) {
        int firstDuplicate = FindDuplicateInIntArray.firstDuplicateValue(new int[]{2, 1, 5, 2, 2, 3, 4, 3});
        System.out.println("{2, 1, 5, 2, 2, 3, 4, 3} first duplicate in array : " + firstDuplicate);
        firstDuplicate = FindDuplicateInIntArray.firstDuplicateValue(new int[]{2, 1, 5, 3, 7, 6, 4, 8});
        System.out.println("{2, 1, 5, 3, 7, 6, 4, 8} first duplicate in array : " + firstDuplicate);
    }

    // O(n) time | O(1) space
    public static int firstDuplicateValue(int[] array) {
        for (int value : array) {
            int absValue = Math.abs(value);
            if (array[absValue -1] < 0) return absValue;
            array[absValue - 1] *= -1;
        }
        return -1;
    }

    // O(n) time | O(n) space
    public static int _firstDuplicateValue(int[] array) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int value : array) {
            if (seen.contains(value)) return value;
            seen.add(value);
        }
        return -1;
    }


}
