package org.javainaction.cyclicsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an unsorted array containing numbers and a number ‘k’, find the first ‘k’ missing positive numbers in
 * the array.
 *
 * Example 1:
 *
 * Input: [3, -1, 4, 5, 5], k=3
 * Output: [1, 2, 6]
 * Explanation: The smallest missing positive numbers are 1, 2 and 6.
 * Example 2:
 *
 * Input: [2, 3, 4], k=3
 * Output: [1, 5, 6]
 * Explanation: The smallest missing positive numbers are 1, 5 and 6.
 * Example 3:
 *
 * Input: [-2, -3, 4], k=2
 * Output: [1, 2]
 * Explanation: The smallest missing positive numbers are 1 and 2.
 * @see AllMissingNumbers
 */
public class FirstKMissingPositive {
    public static List<Integer> findNumbers(int[] nums, int k) {
        int i = 0;
        while (i < nums.length) {
            //since we have -ve numbers we need add two more checks
            //number needs to be greater than 0 and less than length
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        List<Integer> missingNumbers = new ArrayList<>();
        Set<Integer> duplicateNumbers = new HashSet<>();
        // find the first number missing from its index, that will be our required number
        // notice we are only finding k missing numbers
        for (i = 0; i < nums.length && missingNumbers.size() < k; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
                duplicateNumbers.add(nums[i]);
            }
        }

        // add the remaining missing numbers
        // if we did not get k total missing then find extra possible missing numbers
        // by adding them to length of array as we already got missing within array range
        // make sure they are not already considered as duplicate values
        for (i = 1; missingNumbers.size() < k; i++) {
            int candidateNumber = i + nums.length;
            // ignore if the array contains the candidate number
            if (!duplicateNumbers.contains(candidateNumber)) {
                missingNumbers.add(candidateNumber);
            }
        }

        return missingNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> missingNumbers = FirstKMissingPositive.findNumbers(new int[] { 3, -1, 4, 5, 5 }, 3);
        System.out.println("{ 3, -1, 4, 5, 5 } K=3 missing numbers: " + missingNumbers);

        missingNumbers = findNumbers(new int[] { 2, 3, 4 }, 3);
        System.out.println("{ 2, 3, 4 } K=3 missing numbers: " + missingNumbers);

        missingNumbers = findNumbers(new int[] { -2, -3, 4 }, 2);
        System.out.println("{ -2, -3, 4 } K=2 missing numbers: " + missingNumbers);

        missingNumbers = findNumbers(new int[] {2, 3, 4, 7, 11 }, 5);
        System.out.println("{2, 3, 4, 7, 11} K=5 missing numbers: " + missingNumbers);
    }

    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i && //if element is not already on its place
                    nums[i] >= 0 && nums[i] < nums.length && //and it can be put to its place
                    nums[nums[i]] != nums[i] //and it wasn't put to its place earlier (this extra check
                //is needed because initial array can have duplicate elements)
            ) {
                //swap nums[i] and nums[nums[i]]
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return (nums[0] == nums.length) ? nums.length + 1 : nums.length;
    }

    public static void _main(String[] args) {
        int[] a = {3,4,-1,1};
        int[] b = {7,8,9,11,12};
        System.out.println(firstMissingPositive(a));
    }
}
