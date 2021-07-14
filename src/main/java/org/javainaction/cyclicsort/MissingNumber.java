package org.javainaction.cyclicsort;

/**
 * We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. Since the array has only ‘n’
 * numbers out of the total ‘n+1’ numbers, find the missing number.
 *
 * Example 1:
 *
 * Input: [4, 0, 3, 1]
 * Output: 2
 * Example 2:
 *
 * Input: [8, 3, 5, 2, 4, 6, 0, 1]
 * Output: 7
 */
public class MissingNumber {

    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // In this problem, each number should be equal to its index, compared to index + 1 in the Cyclic Sort.
            // we have 0 .. n + 1 => nums[i] == nums[nums[i]]
            // when we have 1 .. n then we compare num[i] with num[num[i] - 1]
            if (nums[i] < nums.length && nums[i] != nums[nums[i]])
                swap(nums, i, nums[i]);
            else
                i++;
        }

        // find the first number missing from its index, that will be our required number
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i)
                return i;

        return nums.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(MissingNumber.findMissingNumber(new int[] { 4, 0, 3, 1 }));
        System.out.println(MissingNumber.findMissingNumber(new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
    }
}
