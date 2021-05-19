package org.javainaction.array;

import java.util.Arrays;

/**
 * Given an integer array, move all elements that are 0 to the left while maintaining the order of
 * other elements in the array. The array has to be modified in-place.
 * Array: [1, 10, 20, 0, 59, 63, 0, 88, 0]
 * Output: [0, 0, 0, 1, 10, 20, 59, 63, 88]
 */
public class MoveZeroToLeft {
    public static void main(String[] args) {
        int[] result = MoveZeroToLeft.moveZeros(new int[] { 1, 10, 20, 0, 59, 63, 0, 88, 0 });
        System.out.println("{ 1, 10, 20, 0, 59, 63, 0, 88, 0 } result after moving zeros to left: " + Arrays.toString(result));
    }

    private static int[] moveZeros(int[] array) {
        if (array == null || array.length == 0) return array;
        int readIndex = array.length - 1;
        int writeIndex = array.length - 1;
        while(readIndex >= 0) {
            if(array[readIndex] != 0) {
                array[writeIndex] = array[readIndex];
                writeIndex--;
            }
            readIndex--;
        }
        while(writeIndex >= 0) {
            array[writeIndex--] = 0;
        }

        return array;
    }
}
