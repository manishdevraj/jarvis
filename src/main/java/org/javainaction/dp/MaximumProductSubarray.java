package org.javainaction.dp;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0 ) return 0;
        int result = nums[0];
        int left = 0;
        int right = 0;
        int n = nums.length;

        //Calculate prefix product in A.
        //Calculate suffix product in A.
        // Return the max

        for (int i = 0; i < n; i++) {
            left = (left == 0 ? 1 : left) * nums[i];
            right = (right == 0 ? 1 : right) * nums[n - 1 - i];
            result = Math.max(result, Math.max(left, right));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("[2,3,-2,4] max product subarray => " + maxProduct(new int[]{2,3,-2,4}));
        System.out.println("[-2,0,-1] max product subarray => " + maxProduct(new int[]{-2,0,-1}));
    }


}
