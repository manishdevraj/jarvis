package org.javainaction.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given an unsorted array containing numbers taken from the range 1 to ‘n’.
 * The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.
 *
 * Example 1:
 *
 * Input: [2, 3, 1, 8, 2, 3, 5, 1]
 * Output: 4, 6, 7
 * Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing.
 * Example 2:
 *
 * Input: [2, 4, 1, 2]
 * Output: 3
 * Example 3:
 *
 * Input: [2, 3, 2, 1]
 * Output: 4
 */
public class AllMissingNumbers {
    public static List<Integer> findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            //For numbers 1.. n we see if we have num at num - 1, if no we keep on swapping them until we
            //either of them at correct position
            //or when they are duplicate we will ignore as at least one instance is at correct position
            //[2, 4, 1, 2]
            //[4, 2, 1, 2]
            //[2, 2, 1, 4]
            //[1, 2, 2, 4] after i is at 2
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        //from above we can see that we have 2 at 3rd index being duplicate
        //so we compute missing number being i + 1 = 3 in this case
        List<Integer> missingNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                missingNumbers.add(i + 1);

        return missingNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> missing = AllMissingNumbers.findNumbers(new int[] { 2, 3, 1, 8, 2, 3, 5, 1 });
        System.out.println("Missing numbers: " + missing);

        missing = AllMissingNumbers.findNumbers(new int[] { 2, 4, 1, 2 });
        System.out.println("Missing numbers: " + missing);

        missing = AllMissingNumbers.findNumbers(new int[] { 2, 3, 2, 1 });
        System.out.println("Missing numbers: " + missing);
    }
}
