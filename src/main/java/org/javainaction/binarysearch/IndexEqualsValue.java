package org.javainaction.binarysearch;

/**
 * Given a sorted array return the minimum index such that index = arr[index]
 * if there is no such element then return -1;
 * Input {-5, -3, 0, 3, 4, 5, 9}
 *
 * Output: 3
 */
public class IndexEqualsValue {
    //O(log(n)) time | O(1) space

    /**
     * Key is if middleValue < middle then there is no way min matching index is in left subarray
     * if middle > middle then there is not way min matching index is in right subarray
     * When in case of match we need to make sure we check previous element for possibility to go towards left of array
     */
    public static int indexEqualsValue(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int middleValue = array[middle];
            if (middleValue < middle) {
                left = middle + 1;
            } else if	(middleValue == middle && middle == 0) return middle; //we are at starting position
            else if (middleValue == middle && array[middle - 1] < middle - 1) return middle; //we are min matching index
            else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(indexEqualsValue(new int[]{-5, -3, 0, 3, 4, 5, 9}));
    }
}
