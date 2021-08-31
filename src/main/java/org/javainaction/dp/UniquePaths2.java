package org.javainaction.dp;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 *
 *
 * Example 1:
 *
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * Example 2:
 *
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 * @see UniquePaths No obstacles just walk
 * @see org.javainaction.graph.UniquePaths3 With some spaces that needs to be walked over exactly once
 *
 */
public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        // if there is a blockage at start
        if (obstacleGrid[0][0] == 1) return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        //possibilities for first column
        for (int row = 1; row < m; row++) {
            //if up is reachable and current is not blocked then we can reach current cell
            obstacleGrid[row][0] = (obstacleGrid[row][0] == 0 && obstacleGrid[row - 1][0] == 1) ? 1 : 0;
        }

        //possibilities for first row
        for (int col = 1; col < n; col++) {
            //if left is reachable and current is not blocked then we can reach current cell
            obstacleGrid[0][col] = (obstacleGrid[0][col] == 0 && obstacleGrid[0][col - 1] == 1) ? 1 : 0;
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                //number ways to reach to current is
                //number ways to reach from left + ways to reach from top
                if (obstacleGrid[row][col] == 0) {
                    int up = obstacleGrid[row - 1][col];
                    int left = obstacleGrid[row][col - 1];
                    obstacleGrid[row][col] = up + left;
                } else {
                    //cannot reach
                    obstacleGrid[row][col] = 0;
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }


    public static void main(String[] args) {
        int expected = 2;
        int[][] grid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0 , 0, 0}};
        var actual = new UniquePaths2().uniquePathsWithObstacles(grid);
        System.out.println("UniquePaths via DP : " + actual);
    }
}
