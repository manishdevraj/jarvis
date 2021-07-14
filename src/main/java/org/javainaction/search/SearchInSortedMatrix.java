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
    //Idea is to start at top right corner
    //every time we have target less than index value that means it has to be row and previous columne
    //if target is larger than current index then we nee to scan next row
    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
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
