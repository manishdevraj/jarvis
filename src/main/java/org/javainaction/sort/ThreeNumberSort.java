package org.javainaction.sort;

import java.util.Arrays;

/**
 * You are given two arrays : order array [x, y, z] and integer array which are guaranteed to be in order array
 * Order array decides the expected order in main array for example [x,x,x.....,y,y,y,y.....,z,z,z,z]
 *
 * This function should run in O(1) space
 * @see org.javainaction.twopointers.DutchNationalFlagProblem
 *
 */
public class ThreeNumberSort {
    public int[] threeNumberSort(int[] array, int[] order) {
        int low = 0;
        int high = array.length - 1;
        int i = 0;
        while (i <= high) {
            if (array[i] == order[0])	{
                swap(array, i, low);
                i++;
                low++;
            } else if (array[i] == order[1]) {
                i++;
            } else if (array[i] == order[2]){
                swap(array, i, high);
                high--;
            }
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        var array = new int[] {1, 0, 0, -1, -1, 0, 1, 1};
        var order = new int[] {0, 1, -1};
        var expected = new int[] {0, 0, 0, 1, 1, 1, -1, -1};
        var actual = new ThreeNumberSort().threeNumberSort(array, order);
        System.out.print(Arrays.toString(actual));
    }
}
