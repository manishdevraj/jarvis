package org.javainaction.heap;

import java.util.PriorityQueue;

/**
 * Given an
 * N
 * ∗
 * N
 * N∗N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the matrix.
 *
 * Example 1:
 *
 * Input: Matrix=[
 *     [2, 6, 8],
 *     [3, 7, 10],
 *     [5, 8, 11]
 *   ],
 *   K=5
 * Output: 7
 * Explanation: The 5th smallest number in the matrix is 7.
 */
public class KthSmallestInSortedMatrix {
    static class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int findKthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);

        // put the 1st element of each row in the min heap
        // we don't need to push more than 'k' elements in the heap
        for (int i = 0; i < matrix.length && i < k; i++)
            minHeap.add(new Node(i, 0));

        // take the smallest (top) element form the min heap, if the running count is equal to k return the number
        // if the row of the top element has more elements, add the next element to the heap
        int numberCount = 0, result = 0;
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result = matrix[node.row][node.col];
            if (++numberCount == k)
                break;
            node.col++;
            if (matrix[0].length > node.col)
                minHeap.add(node);
        }
        return result;
    }

    public static int findKthSmallestBinarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            // first number is the smallest and the second number is the largest
            int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };

            int count = countLessEqual(matrix, mid, smallLargePair);

            if (count == k)
                return smallLargePair[0];

            if (count < k)
                start = smallLargePair[1]; // search higher
            else
                end = smallLargePair[0]; // search lower
        }

        return start;
    }

    private static int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length, row = n - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int matrix[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
        int result = KthSmallestInSortedMatrix.findKthSmallest(matrix, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}
