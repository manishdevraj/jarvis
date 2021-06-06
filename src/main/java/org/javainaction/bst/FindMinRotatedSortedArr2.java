package org.javainaction.bst;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 */
public class FindMinRotatedSortedArr2 {
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
     * nums[lo] > nums[mi] <= nums[hi], (lo, mi] is not sorted, min is inside
     * nums[lo] <= nums[mi] > nums[hi], (mi, hi] is not sorted, min is inside
     * nums[lo] > nums[mi] > nums[hi], impossible
     */
    public static int findMin(int[] arr) {
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

            if (arr[right] < arr[middle]) {
                left = middle + 1;
            } else if (arr[middle] < arr[right]) {
                right = middle;
            } else {
                //we have either same element or arr[left] <= arr[middle] <= arr[right]
                if (arr[right - 1] > arr[right]) {
                    left = right;
                    break;
                }
                right--;
            }
        }

        return arr[left];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] { 1, 3, 5 }));
        System.out.println(findMin(new int[] { 3, 1, 3}));
        System.out.println(findMin(new int[] { 2, 2, 2, 0, 1, 2}));
    }
}
