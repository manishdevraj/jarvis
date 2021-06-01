package org.javainaction.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Build heap with following methods
 * insert:
 * remove:
 * buildHeap:
 * peek:
 */
public class HeapConstruction {
    static class MinHeap {
        List<Integer> heap = new ArrayList<>();

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        //O(n) time | O(1) space
        public List<Integer> buildHeap(List<Integer> array) {
            int parentIdx = (array.size() - 2) / 2;
            for (int currentIdx = parentIdx; currentIdx >= 0; currentIdx--) {
                siftDown(currentIdx, array.size() - 1, array);
            }
            return array;
        }

        //O(log(n)) time | O(1) space
        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            int leftIndex = (currentIdx * 2) + 1;
            while (leftIndex <= endIdx) {
                //check bounds
                int rightIndex = (currentIdx * 2) + 2 <= endIdx ? (currentIdx * 2) + 2 : -1;
                int indexToSwap;
                if (rightIndex != -1 && heap.get(rightIndex) < heap.get(leftIndex)) {
                    indexToSwap = rightIndex;
                } else {
                    indexToSwap = leftIndex;
                }

                if (heap.get(indexToSwap) < heap.get(currentIdx)){
                    swap(currentIdx, indexToSwap, heap);
                    currentIdx = indexToSwap;
                    leftIndex = currentIdx * 2 + 1;
                } else return;
            }

        }

        private void swap(int currentIdx, int indexToSwap, List<Integer> heap) {
            Integer temp = heap.get(currentIdx);
            heap.set(currentIdx, heap.get(indexToSwap));
            heap.set(indexToSwap, temp);
        }

        //O(log(n)) time | O(1) space
        public void siftUp(int currentIdx, List<Integer> heap) {
            int parentIndex = (currentIdx - 1) / 2;
            while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIndex)) {
                swap(currentIdx, parentIndex, heap);
                currentIdx = parentIndex;
                parentIndex = (currentIdx - 1) / 2;
            }
        }

        //O(1) time | O(1) space
        public int peek() {
            return heap.get(0);
        }

        //O(log(n)) time | O(1) space
        public int remove() {
            swap(0, heap.size() - 1, heap);
            int valueToRemove = heap.get(heap.size() - 1);
            heap.remove(heap.size() - 1);
            siftDown(0, heap.size() - 1, heap);
            return valueToRemove;
        }

        //O(log(n)) time | O(1) space
        public void insert(int value) {
            heap.add(value);
            siftUp(heap.size() - 1, heap);
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap =
                new MinHeap(
                        new ArrayList<Integer>(
                                Arrays.asList(48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41)));
        minHeap.insert(76);
        assert isMinHeapPropertySatisfied(minHeap.heap);
        assert minHeap.peek() == -5;
        assert minHeap.remove() == -5;
        assert isMinHeapPropertySatisfied(minHeap.heap);
        assert minHeap.peek() == 2;
        assert minHeap.remove() == 2;
        assert isMinHeapPropertySatisfied(minHeap.heap);
        assert minHeap.peek() == 6;
        minHeap.insert(87);
        assert isMinHeapPropertySatisfied(minHeap.heap);
        System.out.println("Heap satisfied");
    }

    static boolean isMinHeapPropertySatisfied(List<Integer> array) {
        for (int currentIdx = 1; currentIdx < array.size(); currentIdx++) {
            int parentIdx = (currentIdx - 1) / 2;
            if (array.get(parentIdx) > array.get(currentIdx)) {
                return false;
            }
        }
        return true;
    }
}
