package org.javainaction.search;

import java.util.Arrays;

/**
 * You are given a sorted 2D array and an integer target
 * Find the target in sorted matrix and return it's indices x,y
 * Input
 *                  {1, 4, 7, 12, 15, 1000},
 *                 {2, 5, 19, 31, 32, 1001},
 *                 {3, 8, 24, 33, 35, 1002},
 *                 {40, 41, 42, 44, 45, 1003},
 *                 {99, 100, 103, 106, 128, 1004}
 *
 *  target = 44
 *  Output: {3, 3} as (x, y)
 */
public class SearchInSortedMatrix {
    // O(n) time | O(1) space
    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return new int[] {row, col};
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 12, 15, 1000},
                {2, 5, 19, 31, 32, 1001},
                {3, 8, 24, 33, 35, 1002},
                {40, 41, 42, 44, 45, 1003},
                {99, 100, 103, 106, 128, 1004},
        };
        int[] expected = {3, 3};
        int[] output = SearchInSortedMatrix.searchInSortedMatrix(matrix, 44);
        System.out.println(Arrays.toString(output));
    }
}
