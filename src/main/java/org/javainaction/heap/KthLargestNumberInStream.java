package org.javainaction.heap;

import java.util.PriorityQueue;

/**
 * Design a class to efficiently find the Kth largest element in a stream of numbers.
 *
 * The class should have the following two things:
 *
 * The constructor of the class should accept an integer array containing initial numbers from the stream and an
 * integer ‘K’.
 * The class should expose a function add(int num) which will store the given number and return the Kth largest number.
 * Example 1:
 *
 * Input: [3, 1, 5, 12, 2, 11], K = 4
 * 1. Calling add(6) should return '5'.
 * 2. Calling add(13) should return '6'.
 * 2. Calling add(4) should still return '6'.
 */
public class KthLargestNumberInStream {
    private PriorityQueue<Integer> minHeap;
    private int kthLargestNumber = 0;

    public KthLargestNumberInStream(int[] nums, int k) {
        this.minHeap = new PriorityQueue<>((a, b) -> (a - b));
        for (int n : nums) {
            minHeap.add(n);
            if (minHeap.size() > k) minHeap.poll();
        }
        this.kthLargestNumber = k;
    }

    public int add(int num) {
        minHeap.add(num);
        if (minHeap.size() > kthLargestNumber) minHeap.poll();
        return (minHeap.isEmpty()) ? -1 : minHeap.peek();
    }

    public static void main(String[] args) {
        int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
        KthLargestNumberInStream kthLargestNumber = new KthLargestNumberInStream(input, 4);
        System.out.println("4th largest number is: " + kthLargestNumber.add(6));
        System.out.println("4th largest number is: " + kthLargestNumber.add(13));
        System.out.println("4th largest number is: " + kthLargestNumber.add(4));
    }
}
