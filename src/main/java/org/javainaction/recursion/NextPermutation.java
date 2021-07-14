package org.javainaction.recursion;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order
 * (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 */
public class NextPermutation {
    /**
     * Solution:
     *      Step1, from right to left, find the first number which not increase in a ascending order.
     *      In this case which is 3.
     *      Step2, here we can have two situations:
     *
     *      We cannot find the number, all the numbers increasing in a ascending order.
     *      This means this permutation is the last permutation, we need to rotate back to the first permutation.
     *      So we reverse the whole array, for example, 6,5,4,3,2,1 we turn it to 1,2,3,4,5,6.
     *
     *      We can find the number, then the next step, we will start from right most to leftward,
     *      try to find the first number which is larger than 3, in this case it is 4.
     *      Then we swap 3 and 4, the list turn to 2,4,6,5,3,1.
     *      Last, we reverse numbers on the right of 4, we finally get 2,4,1,3,5,6.
     *
     *      Time complexity is: O(3*n)=O(n).
     */
    public static void nextPermutation(int[] nums) {
        int i, j;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) break;
        }

        if (i < 0) {
            reverse(nums, 0, nums.length - 1);
        } else {
            for (j = nums.length - 1; j >= 0; j--) {
                if (nums[j] > nums[i]) break;
            }
            swap(nums, i, j);
            reverse(nums, i + 1, nums.length - 1);
        }
    }

    public static void reverse(int[] nums, int i, int j){
        while(i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] input  = new int[]{1, 2, 3};
        nextPermutation(input);
        System.out.println("{1, 2, 3} next permutation is " + Arrays.toString(input));

        input  = new int[]{3, 2, 1};
        nextPermutation(input);
        System.out.println("{3, 2, 1} next permutation is " + Arrays.toString(input));

        input  = new int[]{1, 1, 5};
        nextPermutation(input);
        System.out.println("{1, 1, 5} next permutation is " + Arrays.toString(input));
    }
}
