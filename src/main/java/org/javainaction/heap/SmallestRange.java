package org.javainaction.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given ‘M’ sorted arrays, find the smallest range that includes at least one number from each of the ‘M’ lists.
 *
 * Example 1:
 *
 * Input: L1=[1, 5, 8], L2=[4, 12], L3=[7, 8, 10]
 * Output: [4, 7]
 * Explanation: The range [4, 7] includes 5 from L1, 4 from L2 and 7 from L3.
 * Example 2:
 *
 * Input: L1=[1, 9], L2=[4, 12], L3=[7, 10, 16]
 * Output: [9, 12]
 * Explanation: The range [9, 12] includes 9 from L1, 12 from L2 and 10 from L3.
 *
 * You have k lists of sorted integers in non-decreasing order.
 * Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 * Example 1:
 *
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 *
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 * Example 3:
 *
 * Input: nums = [[10,10],[11,11]]
 * Output: [10,11]
 * Example 4:
 *
 * Input: nums = [[10],[11]]
 * Output: [10,11]
 * Example 5:
 *
 * Input: nums = [[1],[2],[3],[4],[5],[6],[7]]
 * Output: [1,7]
 */
public class SmallestRange {
    static class Node {
        int elementIndex;
        int arrayIndex;

        Node(int elementIndex, int arrayIndex) {
            this.elementIndex = elementIndex;
            this.arrayIndex = arrayIndex;
        }
    }

    public static int[] findSmallestRange(List<Integer[]> lists) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(
                (n1, n2) -> lists.get(n1.arrayIndex)[n1.elementIndex] - lists.get(n2.arrayIndex)[n2.elementIndex]);

        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE, currentMaxNumber = Integer.MIN_VALUE;
        // put the 1st element of each array in the min heap, this way we can guarantee to have a range that we want
        // that is having  single elements from each array
        for (int i = 0; i < lists.size(); i++) {
            minHeap.add(new Node(0, i));
            //max number helps us know possible range ending element from the beginning
            currentMaxNumber = Math.max(currentMaxNumber, lists.get(i)[0]);
        }

        // take the smallest (top) element from the min heap, if it gives us smaller range, update the ranges
        // if the array of the top element has more elements, insert the next element in the heap
        //as we need heap with size of all list indicating single element from all
        while (minHeap.size() == lists.size()) {
            Node node = minHeap.poll();
            int potentialStart = lists.get(node.arrayIndex)[node.elementIndex];
            //we might have smaller range than previous range
            if (currentMaxNumber - potentialStart < rangeEnd - rangeStart ) {
                rangeStart = potentialStart;
                rangeEnd = currentMaxNumber;
            }

            node.elementIndex++;
            //check if we have more items from this array
            if (lists.get(node.arrayIndex).length > node.elementIndex) {
                minHeap.add(node); // insert the next element in the heap
                //as we are adding new element we need to make sure we have new max
                currentMaxNumber = Math.max(currentMaxNumber, lists.get(node.arrayIndex)[node.elementIndex]);
            }
        }
        return new int[] { rangeStart, rangeEnd };
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 1, 5, 8 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 8, 10 };
        List<Integer[]> lists = new ArrayList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = SmallestRange.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
    }
}
