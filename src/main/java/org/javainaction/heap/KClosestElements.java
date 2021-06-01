package org.javainaction.heap;

import java.util.*;

/**
 * Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in the array.
 * Return the numbers in the sorted order. ‘X’ is not necessarily present in the array.
 *
 * Example 1:
 *
 * Input: [5, 6, 7, 8, 9], K = 3, X = 7
 * Output: [6, 7, 8]
 * Example 2:
 *
 * Input: [2, 4, 5, 6, 9], K = 3, X = 6
 * Output: [4, 5, 6]
 * Example 3:
 *
 * Input: [2, 4, 5, 6, 9], K = 3, X = 10
 * Output: [5, 6, 9]
 */
public class KClosestElements {

    /**
     *
     * Sort with custom comparator
     * O(Nlog(N) + klog(k)) time  | O(n) space
     */
    public static List<Integer> findClosestElementsSortComparator(int[] arr, int k, int x) {
        // Convert from array to list first to make use of Collections.sort()
        List<Integer> sortedArr = new ArrayList<>();
        for (int num: arr) {
            sortedArr.add(num);
        }

        // Sort using custom comparator
        sortedArr.sort((num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

        // Only take k elements
        sortedArr = sortedArr.subList(0, k);

        // Sort again to have output in ascending order
        Collections.sort(sortedArr);
        return sortedArr;
    }

    static class Entry {
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     *
     * Binary search with min heap
     * Find the middle from which we can expand both direction by K distance
     * Find out absolute difference from low to high pointer and calculate top K elements
     *
     * Only issue is that if both side of the window has similar absolute difference then heap cannot guarantee the
     * ordering and hence we may not have expected elements from in ascending order.
     * Though technically they would still be with actual absolute difference expected. As shown below
     *
     * { 0,0,1,2,3,3,4,7,7,8 } 'K=3' closest numbers to 'X=5' are: [3, 4, 7]
     * From indices 3 to 9, abs difference with X is
     * {3, 2, 2, 1, 2, 2, 3} when we push this to heap we cannot guarantee if 2 from two 3's difference or 2 from
     * two 7's
     * difference is at the top output could be {3, 3, 4} or {3, 4, 7}
     */
    public static List<Integer> findClosestElementsBinarySearchHeap(int[] arr, int K, Integer X) {
        int index = binarySearch(arr, X);
        int low = index - K, high = index + K;
        low = Math.max(low, 0); // 'low' should not be less than zero
        high = Math.min(high, arr.length - 1); // 'high' should not be greater the size of the array

        PriorityQueue<Entry> minHeap = new PriorityQueue<>((n1, n2) -> Math.abs(n1.key - X) - Math.abs(n2.key - X));
        // add all candidate elements to the min heap, sorted by their absolute difference from 'X'
        for (int i = low; i <=high; i++)
            minHeap.add(new Entry(arr[i], i));

        // we need the top 'K' elements having smallest difference from 'X'
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < K && !minHeap.isEmpty(); i++)
            result.add(arr[minHeap.poll().value]);

        Collections.sort(result);
        return result;
    }

    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low > 0) {
            return low - 1;
        }
        return low;
    }

    /**
     * Binary Search To Find The Left Bound
     *
     *
     */
    public static List<Integer> findClosestElementsBinarySearchLeftBound(int[] arr, int K, Integer X) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - K;

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            //check elements absolute difference and accordingly shift binary search
            if (X - arr[mid] > arr[mid + K] - X) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // get all first smallest difference elements
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + K; i++) {
            result.add(arr[i]);
        }

        return result;
    }



    public static void main(String[] args) {
        System.out.println("--------------Sort comparator----------------");
        System.out.println("{ 5, 6, 7, 8, 9 } 'K=3' closest numbers to 'X=7' are: "
                + findClosestElementsSortComparator(new int[] { 5, 6, 7, 8, 9 }, 3, 7));

        System.out.println("{ 2, 4, 5, 6, 9 } 'K=3' closest numbers to 'X=6' are: "
                + findClosestElementsSortComparator(new int[] { 2, 4, 5, 6, 9 }, 3, 6));

        System.out.println("{ 2, 4, 5, 6, 9 } 'K=3' closest numbers to 'X=10' are: "
                + findClosestElementsSortComparator(new int[] { 2, 4, 5, 6, 9 }, 3, 10));

        System.out.println("{ 1, 1, 1, 10, 10, 10 } 'K=1' closest numbers to 'X=9' are: "
                + findClosestElementsSortComparator(new int[] { 1, 1, 1, 10, 10, 10 }, 1, 9));

        System.out.println("{ 0,0,1,2,3,3,4,7,7,8 } 'K=3' closest numbers to 'X=5' are: "
                + findClosestElementsSortComparator(new int[] {0, 0, 1, 2, 3, 3, 4, 7, 7, 8 }, 3, 5));

        System.out.println("--------------------------------------------");

        System.out.println("--------------Binary search left bounds----------------");
        System.out.println("{ 5, 6, 7, 8, 9 } 'K=3' closest numbers to 'X=7' are: "
                + findClosestElementsBinarySearchLeftBound(new int[] { 5, 6, 7, 8, 9 }, 3, 7));

        System.out.println("{ 2, 4, 5, 6, 9 } 'K=3' closest numbers to 'X=6' are: "
                + findClosestElementsBinarySearchLeftBound(new int[] { 2, 4, 5, 6, 9 }, 3, 6));

        System.out.println("{ 2, 4, 5, 6, 9 } 'K=3' closest numbers to 'X=10' are: "
                + findClosestElementsBinarySearchLeftBound(new int[] { 2, 4, 5, 6, 9 }, 3, 10));

        System.out.println("{ 1, 1, 1, 10, 10, 10 } 'K=1' closest numbers to 'X=9' are: "
                + findClosestElementsBinarySearchLeftBound(new int[] { 1, 1, 1, 10, 10, 10 }, 1, 9));

        System.out.println("{ 0,0,1,2,3,3,4,7,7,8 } 'K=3' closest numbers to 'X=5' are: "
                + findClosestElementsBinarySearchLeftBound(new int[] {0, 0, 1, 2, 3, 3, 4, 7, 7, 8 }, 3, 5));


        System.out.println("--------------------------------------------");

        System.out.println("--------------Binary search min heap----------------");

        System.out.println("{ 5, 6, 7, 8, 9 } 'K=3' closest numbers to 'X=7' are: "
                + findClosestElementsBinarySearchHeap(new int[] { 5, 6, 7, 8, 9 }, 3, 7));

        System.out.println("{ 2, 4, 5, 6, 9 } 'K=3' closest numbers to 'X=6' are: "
                + findClosestElementsBinarySearchHeap(new int[] { 2, 4, 5, 6, 9 }, 3, 6));

        System.out.println("{ 2, 4, 5, 6, 9 } 'K=3' closest numbers to 'X=10' are: "
                + findClosestElementsBinarySearchHeap(new int[] { 2, 4, 5, 6, 9 }, 3, 10));

        System.out.println("{ 1, 1, 1, 10, 10, 10 } 'K=1' closest numbers to 'X=9' are: "
                + findClosestElementsBinarySearchHeap(new int[] { 1, 1, 1, 10, 10, 10 }, 1, 9));

        System.out.println("{ 0,0,1,2,3,3,4,7,7,8 } 'K=3' closest numbers to 'X=5' are: "
                + findClosestElementsBinarySearchHeap(new int[] {0, 0, 1, 2, 3, 3, 4, 7, 7, 8 }, 3, 5));

    }
}
