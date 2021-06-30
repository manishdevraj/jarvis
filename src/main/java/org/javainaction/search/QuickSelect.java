package org.javainaction.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Write a function when given an array and integer k, return the kth smallest number in array
 *
 * Input: {8, 5, 2, 9, 7, 6, 3} and K = 3
 * Output: 5
 *
 */
public class QuickSelect {
    //Best O(n) time | O(1) space
    //Avg O(n) time | O(1) space
    //Worse O(n^2) time | O(1) space
    public static int quickSelect(int[] array, int k) {
        int position = k - 1;
        return quickSort(array, 0, array.length - 1, position);
    }

    public static int quickSort(int[] array, int start,
                                int end, int position) {
        while (true) {
            if (start > end) {
                throw new RuntimeException("Edge case!!!");
            }

            int pivot = start;
            int left = start + 1;
            int right = end;

            while (left <= right) {
                if (array[left] > array[pivot] && array[right] < array[pivot]){
                    swap(left, right, array);
                    left++;
                    right--;
                }
                if (array[left] <= array[pivot]) left++;
                if (array[right] >= array[pivot]) right--;
            }
            swap(pivot, right, array);
            if (right == position) {
                return array[position];
            } else if (right < position) {
                start = right + 1;
            } else {
                end = right - 1;
            }
        }
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //O(log(n)) time | O(1) space
    public static int quickselectUsingHeap(int[] array, int k) {
        int position = k - 1;
        List<Integer> list = IntStream.of(array).boxed().collect(Collectors.toList());
        MinHeap minHeap = new MinHeap(list);
        int result = -1;
        while (position < minHeap.heap.size()) {
            result = minHeap.remove();
        }
        return result;
    }

    static class MinHeap {
        List<Integer> heap;

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        // O(n) time | O(1) space
        public List<Integer> buildHeap(List<Integer> array) {
            int firstParentIdx = (array.size() - 2) / 2;
            for(int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--){
                siftDown(currentIdx, array.size() - 1, array);
            }
            return array;
        }

        // O(log(n)) time | O(1) space
        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            int leftIdx = currentIdx * 2 + 1;
            while (leftIdx <= endIdx) {
                int rightIdx = currentIdx * 2 + 2 <= endIdx ?
                        currentIdx * 2 + 2 : -1;

                int idxToSwap;
                if(rightIdx != -1 && heap.get(rightIdx) > heap.get(leftIdx)) {
                    idxToSwap = rightIdx;
                } else {
                    idxToSwap = leftIdx;
                }
                if(heap.get(idxToSwap) > heap.get(currentIdx)) {
                    swap(currentIdx, idxToSwap, heap);
                    currentIdx = idxToSwap;
                    leftIdx = currentIdx * 2 + 1;
                } else return;
            }
        }

        // O(log(n)) time | O(1) space
        public void siftUp(int currentIdx, List<Integer> heap) {
            int parentIdx = (currentIdx - 1) / 2;
            while(currentIdx > 0 && heap.get(currentIdx) > heap.get(parentIdx)){
                swap(currentIdx, parentIdx, heap);
                currentIdx = parentIdx;
                parentIdx = (currentIdx - 1) / 2;
            }
        }

        public int peek() {
            return heap.get(0);
        }

        public int remove() {
            swap(0, heap.size() - 1, heap);
            int valueToRemove = heap.get(heap.size() - 1);
            heap.remove(heap.size() - 1);
            siftDown(0, heap.size() - 1, heap);
            return valueToRemove;
        }

        public void insert(int value) {
            heap.add(value);
            siftUp(heap.size() - 1, heap);
        }

        public void swap(int i, int j, List<Integer> heap) {
            Integer tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
        }
    }

    public static void main(String[] args) {
        System.out.println(QuickSelect.quickSelect(new int[] {8, 5, 2, 9, 7, 6, 3}, 3));
        System.out.println(QuickSelect.quickselectUsingHeap(new int[] {8, 5, 2, 9, 7, 6, 3}, 3));
    }

}
