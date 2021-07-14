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

        int read = array.length - 1;
        int write = array.length - 1;
        //while we traverse from right to left
        while(read >= 0) {
            //swap all non zero at write index
            if(array[read] != 0) {
                array[write] = array[read];
                write--;
            }
            read--;
        }

        //mark remaining of write index to 0 to delete remaining swaps
        while(write >= 0) {
            array[write--] = 0;
        }

        return array;
    }

    public static void main(String[] args) {
        int[] result = MoveZeroToLeft.moveZeros(new int[] { 1, 10, 20, 0, 59, 63, 0, 88, 0 });
        System.out.println("{ 1, 10, 20, 0, 59, 63, 0, 88, 0 } result after moving zeros to left: " + Arrays.toString(result));
    }

}
