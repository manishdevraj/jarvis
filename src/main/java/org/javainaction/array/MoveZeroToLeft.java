package org.javainaction.array;

import java.util.Arrays;

/**
 * Given an integer array, move all elements that are 0 to the left while maintaining the order of
 * other elements in the array. The array has to be modified in-place.
 * Array: [1, 10, 20, 0, 59, 63, 0, 88, 0]
 * Output: [0, 0, 0, 1, 10, 20, 59, 63, 88]
 * @see MoveElemToRight
 */
public class MoveZeroToLeft {

    private static int[] moveZeros(int[] array) {
        if (array == null || array.length == 0) return array;

        int left = array.length - 1;
        int right = array.length - 1;
        //while we traverse from right to left
        while (left >= 0) {
            //swap all non zero at right index because we plan to move all zero's at start
            if(array[left] != 0) {
                array[right] = array[left];
                right--;
            }
            left--;
        }

        //mark remaining of right index to 0 to delete remaining swaps
        while (right >= 0) {
            array[right--] = 0;
        }

        return array;
    }

    public static void main(String[] args) {
        int[] result = MoveZeroToLeft.moveZeros(new int[] { 1, 10, 20, 0, 59, 63, 0, 88, 0 });
        System.out.println("{ 1, 10, 20, 0, 59, 63, 0, 88, 0 } result after moving zeros to left: " + Arrays.toString(result));
    }

}
