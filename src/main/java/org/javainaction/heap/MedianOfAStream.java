package org.javainaction.heap;

import java.util.PriorityQueue;

/**
 * Design a class to calculate the median of a number stream. The class should have the following two methods:
 *
 * insertNum(int num): stores the number in the class
 * findMedian(): returns the median of all numbers inserted in the class
 * If the count of numbers inserted in the class is even, the median will be the average of the middle two numbers.
 *
 * Example 1:
 *
 * 1. insertNum(3)
 * 2. insertNum(1)
 * 3. findMedian() -> output: 2
 * 4. insertNum(5)
 * 5. findMedian() -> output: 3
 * 6. insertNum(4)
 * 7. findMedian() -> output: 3.5
 */
public class MedianOfAStream {
    PriorityQueue<Integer> maxHeap; //containing first half of numbers
    PriorityQueue<Integer> minHeap; //containing second half of numbers

    public MedianOfAStream() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    private static double median;

    public void insertNum(int num) {
        if (minHeap.isEmpty() || minHeap.peek() >= num) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
        rebalanceHeap();
        updateMedian();
    }

    public void updateMedian(){
        if (minHeap.size() == maxHeap.size()) {
            median = ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            median = minHeap.peek();
        } else {
            median = maxHeap.peek();
        }
    }

    public double findMedian() {
        return median;
    }

    public void rebalanceHeap() {
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public static void main(String[] args) {
        MedianOfAStream medianOfAStream = new MedianOfAStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}
