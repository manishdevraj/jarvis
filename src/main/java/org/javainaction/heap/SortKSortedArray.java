package org.javainaction.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * K sorted array is partially sorted array where elements are at most k positions away from sorted positions
 */
public class SortKSortedArray {
    /// O(nlog(k)) time | O(k) space where n is the number of elements in array
    //k is how far away elements are from their sorted positions
    public static void sortKSortedArray(int[] array, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

        //we select a range between 0 to K + 1 and add all those elements to min heap
        for(int i = 0; i < Math.min(k + 1, array.length); i++)  minHeap.offer(array[i]);

        int newIndex = 0;
        //we not have temporary storage for each 0 to K + 1 elements so we can do in-place replacement using index
        //every time we add sorted element to address, also add k + 1 th item min heap for next iteration
        // we sort all elements up to K + 1
        //note take K + 1 items so that when array is K = array.length / 2, then we miss kth element
        //input = [-1, -3, -4, 2, 1, 3]
        //output becomes this [-3, -4, -1, 1, 2, 3]
        //instead of [-4, -3, -1, 1, 2, 3]
        for (int i = k + 1; i < array.length && !minHeap.isEmpty(); i++) {
            array[newIndex++] = minHeap.poll();
            minHeap.offer(array[i]);
        }

        //if min heap has any elements then get them into array
        while (!minHeap.isEmpty()) array[newIndex++] = minHeap.poll();
    }

    public static void main(String[] args) {
        int[] input = new int[] {3, 2, 1, 5, 4, 7, 6, 5};
        int k = 3;
        //int[] expected = new int[] {1, 2, 3, 4, 5, 5, 6, 7};
        sortKSortedArray(input, k);
        System.out.println("{3, 2, 1, 5, 4, 7, 6, 5} K=3 sorted array after sorting " + Arrays.toString(input));

        input = new int[]{-4, -3, -1, 1, 2, 3};
        sortKSortedArray(input, 3);
        System.out.println("{-4, -3, -1, 1, 2, 3} K=3 sorted array after sorting " + Arrays.toString(input));
    }
}
