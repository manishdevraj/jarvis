package org.javainaction.twopointers;

import java.util.Arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique
 * element appears at most twice. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be
 * placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first
 * k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1)
 * extra memory.
 *
 * Custom Judge:
 *
 * The judge will test your solution with the following code:
 *
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 *
 * int k = removeDuplicates(nums); // Calls your implementation
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * Example 2:
 *
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * @see RemoveDuplicates Remove one duplicate element
 * @see RemoveAllAdjDuplicate Remova all adjacent duplicate until no adjacent resulting items are duplicates
 */
public class RemoveDuplicates3 {
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            //first two elements
            if (i < 2) {
                nums[i++] = nums[j];
            } else if (nums[j] != nums[i - 2]) {
                //check a number prior to current number;
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] input = new int[] {1,1,1,2,2,3};
        System.out.println("[1,1,1,2,2,3] after removing at more than 2 duplicate " + removeDuplicates(input));
        System.out.println(Arrays.toString(input));
    }
}
