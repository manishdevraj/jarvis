package org.javainaction.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in
 * the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 */
public class ContainsDuplicate2 {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> duplicates = new HashSet<>();
        //sliding window where i is left and k is right of the window
        //we maintain duplicates in set
        //if we grow by k window size and no duplicates found then we shrink window by
        //removing elements of set
        for (int i = 0; i < nums.length; i++) {
            //shrink window by removing numbers falling out of range of K window
            if (i > k) duplicates.remove(nums[i - k -1]);

            //this allows us to find if we have a window of size K that contains duplicate
            if(!duplicates.add(nums[i])) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("{1, 2, 3, 1} contains nearby duplicate "
                + containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println("{1, 0, 1, 1} contains nearby duplicate "
                + containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println("{1, 2, 3, 1, 2, 3} contains nearby duplicate "
                + containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }
}
