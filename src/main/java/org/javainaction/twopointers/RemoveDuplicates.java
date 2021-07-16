package org.javainaction.twopointers;

/**
 * Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; after
 * removing the duplicates in-place return the new length of the array.
 *
 * Problem 1: Given an unsorted array of numbers and a target ‘key’, remove all instances of ‘key’ in-place and
 * return the new length of the array.
 *
 * Example: [2, 3, 3, 3, 6, 9, 9 ]
 * Output: 4
 * @see org.javainaction.array.MoveElemToRight
 * @see org.javainaction.array.MoveZeroToLeft
 */
public class RemoveDuplicates {

    public static int remove(int[] arr) {
        int uniqueIndex = 1;
        for(int right = 1; right < arr.length; right++) {
            if (arr[uniqueIndex - 1] != arr[right]) {
                arr[uniqueIndex] = arr[right];
                uniqueIndex++;
            }
        }
        return uniqueIndex;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(remove(arr));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(remove(arr));
    }
}
