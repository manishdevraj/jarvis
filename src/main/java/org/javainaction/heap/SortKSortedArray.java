package org.javainaction.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * K sorted array is partially sorted array where elements are at most k positions away from sorted positions
 */
public class SortKSortedArray {
    /// O(nlog(k)) time | O(k) space where n is the number of elements in array
    //k is how far away elements are from their sorted positions
    public static void sortKSortedArray(int[] array, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

        for(int i = 0; i < Math.min(k + 1, array.length); i++){
            minHeap.offer(array[i]);
        }

        int newIndex = 0;
        for(int i = k + 1; i < array.length && !minHeap.isEmpty(); i++){
            array[newIndex++] = minHeap.poll();
            minHeap.offer(array[i]);
        }

        while (!minHeap.isEmpty()) {
            array[newIndex++] = minHeap.poll();
        }
    }

    public static void main(String[] args) {
        int[] input = new int[] {3, 2, 1, 5, 4, 7, 6, 5};
        int k = 3;
        //int[] expected = new int[] {1, 2, 3, 4, 5, 5, 6, 7};
        sortKSortedArray(input, k);
        System.out.println("{3, 2, 1, 5, 4, 7, 6, 5} K=3 sorted array after sorting "
                + Arrays.toString(input));
    }
}
