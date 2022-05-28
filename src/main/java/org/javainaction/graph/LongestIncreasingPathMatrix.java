package org.javainaction.graph;

import java.util.Arrays;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathMatrix {
    private static final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[][] memoize = new int[matrix.length][matrix[0].length];

        int maxLength = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int length = findLongestIncPathRecursive(matrix, i, j, memoize);
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }

    public static int findLongestIncPathRecursive(int[][] matrix, int i, int j, int[][] memoize) {
        if (memoize[i][j] != 0) return memoize[i][j];
        int maxLength = 1;
        for (int[] dirs : directions) {
            int x = i + dirs[0];
            int y = j + dirs[1];
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[i][j] >= matrix[x][y])
                continue;
            int length = 1 + findLongestIncPathRecursive(matrix, x, y, memoize);
            maxLength = Math.max(maxLength, length);

        }
        memoize[i][j] = maxLength;
        return memoize[i][j];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {9, 9 , 4},
                {6, 6, 8},
                {2, 1, 1}};
        //int expected = 4;
        int output = longestIncreasingPath(grid);
        System.out.println(Arrays.deepToString(grid));
        System.out.println("Longest increasing path : " + output);

        int[][] grid2 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}};
        //int expected = 4;
        output = longestIncreasingPath(grid2);
        System.out.println(Arrays.deepToString(grid2));
        System.out.println("Longest increasing path : " + output);

        int[][] grid3 = {
                {1}};
        //int expected = 1;
        output = longestIncreasingPath(grid3);
        System.out.println(Arrays.deepToString(grid3));
        System.out.println("Longest increasing path : " + output);


    }
}
