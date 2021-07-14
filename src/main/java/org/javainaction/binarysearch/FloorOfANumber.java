package org.javainaction.binarysearch;

/**
 * Given an array of numbers sorted in ascending order, find the floor of a given number ‘key’. The floor of the ‘key’
 * will be the biggest element in the given array smaller than or equal to the ‘key’
 *
 * Write a function to return the index of the floor of the ‘key’. If there isn’t a floor, return -1.
 *
 * Example 1:
 *
 * Input: [4, 6, 10], key = 6
 * Output: 1
 * Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
 * Example 2:
 *
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 3
 * Explanation: The smallest number greater than or equal to '12' is '15' having index '4'.
 * Example 3:
 *
 * Input: [4, 6, 10], key = 17
 * Output: -1
 * Explanation: There is no number greater than or equal to '17' in the given array.
 * Example 4:
 *
 * Input: [4, 6, 10], key = -1
 * Output: 0
 * Explanation: The smallest number greater than or equal to '-1' is '4' having index '0'.
 * @see CeilingOfANumber
 */
public class FloorOfANumber {
    public static int searchFloorOfANumber(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        return binarySearchFloor(arr, key);
    }

    public static int binarySearchFloor(int[] arr, int key) {
        // if the 'key' is bigger than the biggest element
        if (key > arr[arr.length - 1])
            return -1;

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (key == arr[middle]) {
                return middle;
            } else if (key < arr[middle]) {
                right = middle - 1;
            } else if (key > arr[middle]) {
                left = middle + 1;
            }
        }
        // if the 'key' is smaller than the smallest element
        if (key < arr[0])
            return -1;
        //if there is no direct match, left will always point to floor of the number
        return right;
    }

    public static void main(String[] args) {
        System.out.println(searchFloorOfANumber(new int[] { 4, 6, 10 }, 6));
        System.out.println(searchFloorOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(searchFloorOfANumber(new int[] { 4, 6, 10 }, 17));
        System.out.println(searchFloorOfANumber(new int[] { 4, 6, 10 }, -1));
        System.out.println(searchFloorOfANumber(new int[] { 1, 3, 8, 10, 15 }, 4));
    }
}
