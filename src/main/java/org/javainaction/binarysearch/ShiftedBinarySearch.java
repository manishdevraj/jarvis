package org.javainaction.binarysearch;

/**
 * Different than Bitonic array where array is just shifted not Bitonic
 *
 * Find a given element in a sorted array but that is shifted by one or more positions
 *
 * Input [3, 4, 1, 2] and target = 1
 * @see SearchRotatedArray
 */
public class ShiftedBinarySearch {

    // O (log(n)) time | O(1) space
    public static int shiftedBinarySearch(int[] array, int target) {
        return shiftedBinarySearch(array, target, 0, array.length - 1);
    }

    public static int shiftedBinarySearch(int[] array, int target,
                                          int left, int right) {
        while (left <= right) {
            int middle = (left + right) / 2;
            int potentialMatch = array[middle];
            int leftNum = array[left];
            int rightNum = array[right];
            if (target == potentialMatch) {
                return middle;
            } else if (leftNum <= potentialMatch) {
                if (leftNum <= target && target < potentialMatch) { //in left sub array
                    right = middle - 1;
                } else {
                    left = middle + 1; //it is shifted
                }
            } else {
                if (target > potentialMatch && target <= rightNum) { //in right sub array
                    left = middle + 1;
                } else {
                    right = middle - 1; //it is shifted
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 45, 61, 71, 72, 73, 0, 1, 21, 33, 45};
        System.out.println(shiftedBinarySearch(arr, 33 ));
    }
}
