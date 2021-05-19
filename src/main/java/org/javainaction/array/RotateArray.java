package org.javainaction.array;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] result = RotateArray.rotateReverse(new int[] { 1,2,3,4,5,6,7 }, 3);
        System.out.println(" After rotation " + Arrays.toString(result) + " by " + 3 + " positions!");
        result = RotateArray.rotateReverse(new int[] { -1,-100,3,99 }, 2);
        System.out.println(" After rotation " + Arrays.toString(result) + " by " + 2 + " positions!");
        result = RotateArray.rotateLikeCyclicReplacement(new int[] { 1,2,3,4,5,6,7 }, 3);
        System.out.println(" After rotation " + Arrays.toString(result) + " by " + 3 + " positions!");
        result = RotateArray.rotateLikeCyclicReplacement(new int[] { -1,-100,3,99 }, 2);
        System.out.println(" After rotation " + Arrays.toString(result) + " by " + 2 + " positions!");
    }

    public static int[] rotateReverse(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        return nums;
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static int[] rotateLikeCyclicReplacement(int[] nums, int k) {
        k %= nums.length;
        int count = 0;
        for( int start = 0; count < nums.length; start++){
            int current = start;
            int previous = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = previous;
                previous = temp;
                current = next;
                count++;

            } while(start != current);
        }
        return nums;
    }


}
