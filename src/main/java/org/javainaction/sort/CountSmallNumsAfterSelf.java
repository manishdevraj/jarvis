package org.javainaction.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 *
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 *
 * Input: nums = [-1,-1]
 * Output: [0,0]
 * @see org.javainaction.bst.CountSmallNumsAfterSelf
 */
public class CountSmallNumsAfterSelf {

    /**
     * 
     * To apply merge sort, one key observation is that:
     *
     * The smaller elements on the right of a number will jump from its right to its left during the sorting process.
     *
     * If we can record the numbers of those elements during sorting, then the problem is solved.
     *
     * Can we modify the merge sort a little to meet our needs?
     *
     * Consider when merging two sorted list
     *
     *
     * Yes! When we select an element i on the left array, we know that elements selected previously from the right
     * array jump from i's right to i's left.
     *
     * By adding the counts of those elements in every merge step, we get the total number of elements that jumped 
     * from i's right to i's left.
     * 
     * Implement a merge sort function.
     *
     * For each element i, the function records the number of elements jumping from i's right to i's left during the
     * merge sort.
     * Merge sort nums, store the number of elements jumping from right to left in result.
     *
     * Alternatively, one can sort the indices with corresponding values in nums. That is to say, we are going to
     * sort list [0, 1, ..., n-1] according to the comparator nums[i]. This helps to track the indices and update
     * result. You can find additional details in the implementations below.
     */
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[] indices = new int[n]; // record the index. we are going to sort this array
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        // sort indices with their corresponding values in nums, i.e., nums[indices[i]]
        mergeSort(indices, 0, n, result, nums);
        // change int[] to List to return
        List<Integer> resultToReturn = new ArrayList<>(n);
        for (int i : result) {
            resultToReturn.add(i);
        }
        return resultToReturn;
    }

    private void mergeSort(int[] indices, int left, int right, int[] result, int[] nums) {
        if (right - left <= 1) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(indices, left, mid, result, nums);
        mergeSort(indices, mid, right, result, nums);
        merge(indices, left, right, mid, result, nums);
    }

    private void merge(int[] indices, int left, int right, int mid, int[] result, int[] nums) {
        // merge [left, mid) and [mid, right)
        int i = left; // current index for the left array
        int j = mid; // current index for the right array
        // use temp to temporarily store sorted array
        List<Integer> temp = new ArrayList<>(right - left);
        while (i < mid && j < right) {
            if (nums[indices[i]] <= nums[indices[j]]) {
                // j - mid numbers jump to the left side of indices[i]
                result[indices[i]] += j - mid;
                temp.add(indices[i]);
                i++;
            } else {
                temp.add(indices[j]);
                j++;
            }
        }
        // when one of the subarrays is empty
        while (i < mid) {
            // j - mid numbers jump to the left side of indices[i]
            result[indices[i]] += j - mid;
            temp.add(indices[i]);
            i++;
        }
        while (j < right) {
            temp.add(indices[j]);
            j++;
        }
        // restore from temp
        for (int k = left; k < right; k++) {
            indices[k] = temp.get(k - left);
        }
    }

    public static void main(String[] args) {
        var array = Arrays.asList(8, 5, 11, -1, 3, 4, 2);
        var expected = Arrays.asList(5, 4, 4, 0, 1, 1, 0);
        System.out.println("Using modified merge sort all elements smaller to right are " +
                new CountSmallNumsAfterSelf().countSmaller(new int[]{8, 5, 11, -1, 3, 4, 2}));
    }
}
