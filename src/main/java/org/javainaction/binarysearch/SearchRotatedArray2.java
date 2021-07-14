package org.javainaction.binarysearch;

/**
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums,
 * or false if it is not in nums.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * @see SearchRotatedArray
 */
public class SearchRotatedArray2 {
    public static boolean searchRotatedArrayDuplicate(int[] rotatedArr, int target) {
        //because the order is ascending from min value and descending from 0 to peak we cannot use standard binary array
        //we need to first find the min index and then search on either side of the pic to get the element index
        int min = findMinIndex(rotatedArr);

        // find target in the left of peak
        // ascending order
        int left = min;
        int right = rotatedArr.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int value = rotatedArr[middle];
            if (target == value) {
                return true;
            } else if (target < value) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        // find target in the right of peak
        // descending order
        left = 0;
        right = min;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int value = rotatedArr[middle];
            if (target == value) {
                return true;
            } else if (target > value) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return false;
    }

    /**
     * Logical Thought
     * We assert the loop invariant is the index of the minimum, min, is within the range [lo, hi].
     *
     * Before the loop, min is in [0, nums.length - 1]. To satisfy the invariant, lo = 0, hi = nums.length - 1
     * If we guess mi,
     * if nums[mi] > nums[hi], min should be always in [mi + 1, hi] (explained in Essence). To satisfy the invariant,
     * lo = mi + 1;
     * else if nums[mi] < nums[lo], min should be always in [lo + 1, mi] (explained in Essence),
     * to satisfy the assertion, hi = mi, lo = lo + 1;
     * else (nums[lo] <= nums[mi] <= nums[hi]) min should be always nums[lo].
     * After the loop, lo = hi, min should be in [lo, lo], to satisfy the assertion, min = lo.
     * Essence
     * If we split the array with mi into [lo, mi] and [mi, hi]. If [lo, mi] is not sorted, since we detect [lo, mi]
     * is not sorted by nums[lo] > nums[mi] so nums[lo] cannot be min, min must be within (lo, mi].
     * If [mi, hi] is not sorted, min must be within (mi, hi] - since we detect [mi, hi] is not
     * sorted by nums[mi] > nums[hi], nums[mi] cannot be min. If they are both sorted, nums[lo] is the min.
     * There are 4 kinds of relationship among num[lo], nums[mi], nums[hi]
     *
     * nums[lo] <= nums[mi] <= nums[hi], min is nums[lo]
     * nums[lo] > nums[mi] <= nums[hi], [lo, mi] is not sorted, min is inside
     * nums[lo] <= nums[mi] > nums[hi], [mi, hi] is not sorted, min is inside
     * nums[lo] > nums[mi] > nums[hi], impossible
     */
    public static int findMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return arr[0];
        int left = 0; int right = arr.length - 1;

        while (left < right) {
            // to avoid duplicates
            while (left < right && arr[left] == arr[left + 1])
                ++left;
            while (left < right && arr[right] == arr[right - 1])
                --right;

            int middle = left + (right - left) / 2;

            //we have array descending from middle so min should be between middle + 1 and right
            //nums[lo] <= nums[mi] > nums[hi], [mi, hi] is not sorted, min is inside
            if (arr[right] < arr[middle]) {
                left = middle + 1;
                //we have array ascending from middle to right so array must be between left and middle (including self)
                // nums[lo] > nums[mi] <= nums[hi], [lo, mi] is not sorted, min is inside
            } else if (arr[middle] < arr[right]) {
                right = middle;
            } else {
                //we have either same element or arr[left] <= arr[middle] <= arr[right]
                //nums[lo] <= nums[mi] <= nums[hi], min is nums[lo]
                left = right;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(searchRotatedArrayDuplicate(new int[] { 2,5,6,6,0,0,2 }, 0));
        System.out.println(searchRotatedArrayDuplicate(new int[] { 2,5,6,0,0,1,2 }, 3));
        System.out.println(searchRotatedArrayDuplicate(new int[] { 1, 0, 1, 1, 1, 1}, 0));
        System.out.println(searchRotatedArrayDuplicate(new int[] { 1, 2, 3, 4, 5, 6}, 1));
        System.out.println(searchRotatedArrayDuplicate(new int[] { 2, 3, 4, 5, 6, 1}, 1));
    }
}
