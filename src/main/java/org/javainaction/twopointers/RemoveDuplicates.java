package org.javainaction.twopointers;

/**
 * Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; after
 * removing the duplicates in-place return the new length of the array.
 *
 * Problem 1: Given an unsorted array of numbers and a target ‘key’, remove all instances of ‘key’ in-place and
 * return the new length of the array.
 */
public class RemoveDuplicates {

    public static int remove(int[] arr) {
        int notDuplicatedIdx = 1;
        for(int j = 1; j < arr.length; j++) {
            if (arr[notDuplicatedIdx - 1] != arr[j]) {
                arr[notDuplicatedIdx] = arr[j];
                notDuplicatedIdx++;
            }
        }
        return notDuplicatedIdx;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(RemoveDuplicates.remove(arr));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(RemoveDuplicates.remove(arr));
    }
}
