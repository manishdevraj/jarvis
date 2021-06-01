package org.javainaction.heap;

import java.util.PriorityQueue;

/**
 * Given ‘N’ ropes with different lengths, we need to connect these ropes into one big rope with minimum cost.
 * The cost of connecting two ropes is equal to the sum of their lengths.
 *
 * Example 1:
 *
 * Input: [1, 3, 11, 5]
 * Output: 33
 * Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)
 * Example 2:
 *
 * Input: [3, 4, 5, 6]
 * Output: 36
 * Explanation: First connect 3+4(=7), then 5+6(=11), 7+11(=18). Total cost is 36 (7+11+18)
 * Example 3:
 *
 * Input: [1, 3, 11, 5, 2]
 * Output: 42
 * Explanation: First connect 1+2(=3), then 3+3(=6), 6+5(=11), 11+11(=22). Total cost is 42 (3+6+11+22)
 */
public class ConnectRopes {
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        // add all ropes to the min heap
        for (int ropeLength : ropeLengths) minHeap.add(ropeLength);

        // go through the values of the heap, in each step take top (lowest) rope lengths from the min heap
        // connect them and push the result back to the min heap.
        // keep doing this until the heap is left with only one rope
        int costOfConnection = 0;
        while (minHeap.size() > 1) {
            int cost = minHeap.poll() + minHeap.poll();
            costOfConnection += cost;
            minHeap.add(cost);
        }

        return costOfConnection;
    }

    public static void main(String[] args) {
        System.out.println("{1, 3, 11, 5} minimum cost to connect ropes: " +
                minimumCostToConnectRopes(new int[] { 1, 3, 11, 5 }));
        System.out.println("{ 3, 4, 5, 6 } minimum cost to connect ropes: " +
                minimumCostToConnectRopes(new int[] { 3, 4, 5, 6 }));
        System.out.println("{ 1, 3, 11, 5, 2 } minimum cost to connect ropes: " +
                minimumCostToConnectRopes(new int[] { 1, 3, 11, 5, 2 }));
    }
}
