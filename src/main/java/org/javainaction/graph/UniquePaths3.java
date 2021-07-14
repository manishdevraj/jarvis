package org.javainaction.graph;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square,
 * that walk over every non-obstacle square exactly once.
 *
 *
 * Example 1:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 * @see org.javainaction.dp.UniquePaths
 * @see org.javainaction.dp.UniquePaths2
 */
public class UniquePaths3 {

    private static final int[] DIR = {0, -1, 0, 1, 0};
    public int uniquePathsIII(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int empty = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //counting spaces that we can use
                if (grid[i][j] == 0) ++empty;
                else if (grid[i][j] == 1) {
                    //we found starting point
                    x = i;
                    y = j;
                }

            }
        }

        //count is -1 because we need to exclude starting point, as we cannot set visited[x][y] = true here
        return uniquePathDfs(grid, visited, x,  y, -1, empty);
    }

    public int uniquePathDfs(int[][] grid, boolean[][] visited, int i, int j,
                             int count, int need) {
        //check bounds
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || visited[i][j] || grid[i][j] == -1) return 0;

        if (grid[i][j] == 2) {
            //that walk over every non-obstacle square exactly once, meaning we need to visit all of them
            if (need == count) return 1;
            return 0;
        }

        visited[i][j] = true;

        int noOfWays = 0;
        for (int d = 0; d < 4; d++) {
            noOfWays += uniquePathDfs(grid, visited, i + DIR[d], j + DIR[d + 1], count + 1, need);
        }

        visited[i][j] = false;

        return noOfWays;
    }

    public static void main(String[] args) {
        int expected = 2;
        int[][] grid = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        var actual = new UniquePaths3().uniquePathsIII(grid);
        System.out.println("UniquePaths : " + actual);
    }
}
