package org.javainaction.sort;

import java.util.Arrays;

/**
 * Implement heap sort
 *
 * Throughout the algorithm we are going to have two subarrays one sorted and one not sorted.
 * Idea is to build a max heap and then move our max element to the end and sift down rest of the subarray to make
 * them as a max heap
 * We continue doing so until we no longer have to maintain maxheap and entire array is sorted
 */
public class HeapSort {
    // Best: O(nlog(n)) time | O(1) space
    // Average : O(nlog(n)) time | O(1) space
    // Worse: O(nlog(n)) time | O(1) space
    public static int[] heapSort(int[] array) {
        buildMaxHeap(array);
        for(int end = array.length - 1; end > 0; end--){
            swap(0, end, array);
            siftDown(0, end - 1, array);
        }
        return array;
    }

    // O(n) time | O(1) space
    public static void buildMaxHeap(int[] array) {
        int firstParent = (array.length - 2) / 2;
        for(int current = firstParent; current >= 0; current--){
            siftDown(current, array.length - 1, array);
        }
    }

    // O(log(n)) time | O(1) space
    public static void siftDown(int current, int end, int[] heap) {
        int left = current * 2 + 1;
        while (left <= end) {
            int right = current * 2 + 2 <= end ?
                    current * 2 + 2 : -1;

            int idxToSwap;
            if(right != -1 && heap[right] > heap[left]) {
                idxToSwap = right;
            } else {
                idxToSwap = left;
            }
            if(heap[idxToSwap] > heap[current]) {
                swap(current, idxToSwap, heap);
                current = idxToSwap;
                left = current * 2 + 1;
            } else return;
        }
    }

    public static void swap(int i, int j, int[] heap) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public static void main(String[] args) {
        int[] expected = {2, 3, 5, 5, 6, 8, 9};
        int[] input = {8, 5, 2, 9, 5, 6, 3};
        System.out.println(Arrays.toString(HeapSort.heapSort(input)));
    }
}
