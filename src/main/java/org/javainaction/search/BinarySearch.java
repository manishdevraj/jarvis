package org.javainaction.search;

/**
 * Write a binary search function
 */
public class BinarySearch {
    //O (log(n)) time | O(log(n)) space
    public static int binarySearch(int[] array, int target) {
        return binarySearch(array, target, 0, array.length -1);
    }

    public static int binarySearch(int[] array, int target, int left, int right) {
        if (left > right) return -1;

        int middle = (left + right) / 2;
        int potentialMatch = array[middle];
        if (target == potentialMatch) {
            return middle;
        } else if(target < potentialMatch) {
            return binarySearch(array, target, left, middle - 1);
        } else {
            return binarySearch(array, target, middle + 1, right);
        }
    }

    public static void main(String[] args){
        binarySearch(new int[] {0, 1, 21, 33, 45, 45, 61, 71, 72, 73}, 33);
    }
}
