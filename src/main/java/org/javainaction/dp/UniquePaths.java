package org.javainaction.dp;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * Example 3:
 *
 * Input: m = 7, n = 3
 * Output: 28
 * Example 4:
 *
 * Input: m = 3, n = 3
 * Output: 6
 * @see UniquePaths
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] noOfWays = new int[m + 1][n + 1];

        for (int row = 1; row < m + 1; row++) {
            for (int col = 1; col < n + 1; col++) {
                if (row == 1 || col == 1) {
                    noOfWays[row][col] = 1;
                } else {
                    int up = noOfWays[row - 1][col];
                    int left = noOfWays[row][col - 1];
                    noOfWays[row][col] = up + left;
                }
            }
        }
        return noOfWays[m][n];
    }
    public static void main(String[] args) {
        int expected = 28;
        var actual = new UniquePaths().uniquePaths(3, 7);
        System.out.println("UniquePaths : " + actual);
        actual = new UniquePaths().uniquePaths(3, 3);
        System.out.println("UniquePaths : " + actual);
    }
}