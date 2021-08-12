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
 * @see RemoveAllAdjDuplicate where we remove all duplicates vs just duplicate occurrence
 */
public class RemoveDuplicates {

    /**
     * Remove Duplicates from Sorted Array(no duplicates) :
     *
     * public int removeDuplicates(int[] nums) {
     *     int i = 0;
     *     for(int n : nums)
     *         if(i < 1 || n > nums[i - 1])
     *             nums[i++] = n;
     *     return i;
     * }
     * Remove Duplicates from Sorted Array II (allow duplicates up to 2):
     *
     * public int removeDuplicates(int[] nums) {
     *    int i = 0;
     *    for (int n : nums)
     *       if (i < 2 || n > nums[i - 2])
     *          nums[i++] = n;
     *    return i;
     * }
     */


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

    public static int removeDuplicatesV2(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            //first two elements
            if (i < 1) {
                nums[i++] = nums[j];
            } else if (nums[j] != nums[i - 1]) {
                //check a number prior to current number;
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public static int removeDuplicates(int[] arr) {
        int i = 0;
        for (int j = 0; j < arr.length; j++, i++) {
            arr[i] = arr[j];
            if (i > 0 && arr[i - 1] == arr[i]) {
                --i;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(remove(arr));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(remove(arr));

        arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(removeDuplicates(arr));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(removeDuplicates(arr));

        arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(removeDuplicatesV2(arr));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(removeDuplicatesV2(arr));
    }
}
