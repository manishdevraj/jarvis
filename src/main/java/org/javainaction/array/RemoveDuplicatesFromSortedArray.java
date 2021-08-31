package org.javainaction.array;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns
 * the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with
 * O(1) extra memory.
 *
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2]
 * Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the returned length.
 *
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4]
 * Explanation: Your function should return length = 5, with the first five elements of nums being modified to
 * 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.
 * @see MoveElemToRight
 */
public class RemoveDuplicatesFromSortedArray {
    private static int removeDuplicates(int[] nums) {
        int left = 0;
        for (int right = 0; right < nums.length; right++){
            //this has no effect if we are having unique value
            //at left and right = 0; it will compute once before starting to find duplicate
            nums[left++] = nums[right];
            //until we have duplicate, slide the window, up to next unique element and place at next iteration
            while (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                right++;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int length = removeDuplicates(new int[] { 0,0,1,1,1,2,2,3,3,4 });
        System.out.println(" {0,0,1,1,1,2,2,3,3,4} : length after removing duplicates: " + length);
        length = removeDuplicates(new int[] { 1, 1, 2 });
        System.out.println(" {1,1,2} : length after removing duplicates: " + length);
    }
}
