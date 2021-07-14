package org.javainaction.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * ContinuousMedianHandler class that supports
 * 1.  Continuous insertions of the numbers using insert() method
 * 2. The instant O(1) getMedian() method to return a median
 *
 * Example {1, 3, 5} the median would be 3
 *
 * Example {1, 3, 7, 8} the median would be (3+7)/2 = 5
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 */
public class ContinuousMedian {

    static class ContinuousMedianHandler {
        BiFunction<Integer, Integer, Boolean> MAX_HEAP_FUNC = (a, b) -> a.compareTo(b) > 0;
        BiFunction<Integer, Integer, Boolean> MIN_HEAP_FUNC = (a, b) -> a.compareTo(b) < 0;
        //lower is max heap such that we get trailing end of the item form heap
        //greater is min heap such that we get min items that is >= lower trailing end
        Heap lower = new Heap(MAX_HEAP_FUNC, new ArrayList<>());
        Heap greater = new Heap(MIN_HEAP_FUNC, new ArrayList<>());
        double median = 0;

        // O(log(n)) time | O(n) space
        public void insert(int number) {
            if (lower.length == 0 || number < lower.peek()) {
                lower.insert(number);
            } else {
                greater.insert(number);
            }
            rebalanceHeaps();
            updateMedian();
        }

        public double getMedian(){
            return median;
        }

        private void updateMedian() {
            if (lower.length == greater.length) {
                median =  ((double) lower.peek() + (double)greater.peek()) / 2;
            } else if (greater.length > lower.length){
                median = greater.peek();
            } else {
                median = lower.peek();
            }
        }

        private void rebalanceHeaps() {
            if (lower.length > greater.length + 1) {
                greater.insert(lower.remove());
            } else if (greater.length > lower.length){
                lower.insert(greater.remove());
            }
        }
    }

    static class Heap {
        List<Integer> heap = new ArrayList<>();
        BiFunction<Integer, Integer, Boolean> comparisonFunc;
        int length;

        public Heap(BiFunction<Integer, Integer, Boolean> func,
                    List<Integer> array){
            heap = buildHeap(array);
            comparisonFunc = func;
            length = heap.size();
        }

        public List<Integer> buildHeap(List<Integer> array) {
            int firstParentIdx = (length - 2) / 2;
            for (int currentIdx = firstParentIdx; currentIdx >= 0;
                 currentIdx--) {
                siftDown(currentIdx, length - 1, array);
            }
            return array;
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap){
            int childOneIdx = currentIdx * 2 + 1;
            while (childOneIdx <= endIdx) {
                int childTwoIdx = currentIdx * 2 + 2 <= endIdx ?
                        currentIdx * 2 + 2 : -1;
                int indexToSwap;
                if (childTwoIdx != -1) {
                    if (comparisonFunc.apply(heap.get(childTwoIdx), heap.get(childOneIdx))) {
                        indexToSwap = childTwoIdx;
                    } else {
                        indexToSwap = childOneIdx;
                    }
                } else {
                    indexToSwap = childOneIdx;
                }

                if (comparisonFunc.apply(heap.get(indexToSwap), heap.get(currentIdx))) {
                    swap(currentIdx, indexToSwap, heap);
                    currentIdx = indexToSwap;
                    childOneIdx = currentIdx * 2 + 1;
                } else {
                    return;
                }
            }
        }

        public void siftUp(int currentIdx, List<Integer> heap){
            int parentIdx = (currentIdx - 1) / 2;
            while (currentIdx > 0) {
                if (comparisonFunc.apply(heap.get(currentIdx), heap.get(parentIdx))) {
                    swap(currentIdx, parentIdx, heap);
                    currentIdx = parentIdx;
                    parentIdx = (currentIdx - 1) / 2;
                } else {
                    return;
                }
            }
        }

        public int peek() {
            return heap.get(0);
        }

        public int remove() {
            swap(0, length - 1, heap);
            int valueToRemove = heap.get(length - 1);
            heap.remove(length - 1);
            length--;
            siftDown(0, length - 1, heap);
            return valueToRemove;
        }

        public void insert(int value) {
            heap.add(value);
            length++;
            siftUp(length - 1, heap);
        }

        public void swap(int i, int j, List<Integer> heap){
            Integer temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

    public static void main(String[] args) {
        ContinuousMedianHandler handler = new ContinuousMedianHandler();
        handler.insert(5);
        handler.insert(10);
        //== 7.5
        System.out.println(handler.getMedian());
        handler.insert(100);
        //10
        System.out.println(handler.getMedian());
    }
}
