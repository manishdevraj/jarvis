package org.javainaction.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle).
 * In one step, you can move up, down, left or right from and to an empty cell.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1)
 * given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 *
 * Example 1:
 *
 * Input:
 * grid =
 * [[0,0,0],
 *  [1,1,0],
 *  [0,0,0],
 *  [0,1,1],
 *  [0,0,0]],
 * k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6.
 * Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 *
 * Example 2:
 *
 * Input:
 * grid =
 * [[0,1,1],
 *  [1,1,1],
 *  [1,0,0]],
 * k = 1
 * Output: -1
 * Explanation:
 * We need to eliminate at least two obstacles to find such a walk.
 * @see org.javainaction.array.ShortestPathMaze Where we are not allowed to remove elimination
 * @see MaxDistanceRobotFloorPlan Where we use 3rd dimention to maintain max distance from source
 */
public class ShortestPathGridObstaclesElimination {
    private static final int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int shortestPath(int[][] grid, int k) {
        //3rd dimension is to keep obstacle elimination count
        boolean[][][] visited = new boolean[grid.length][grid[0].length][k + 1];
        Queue<int[]> cellToExplore = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        cellToExplore.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;

        int distance = 0;
        int minDistance = Integer.MAX_VALUE;

        while(!cellToExplore.isEmpty()) {
            int queueSize = cellToExplore.size();

            for (int q = 0; q < queueSize; q++) {
                int[] currentCell = cellToExplore.poll();
                if (currentCell != null) {
                    int i = currentCell[0];
                    int j = currentCell[1];
                    //reached at bottom right which is destination
                    if (i == m - 1 && j == n - 1) {
                        minDistance = Math.min(minDistance, distance);
                    }

                    for(int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        int nextK =  currentCell[2];
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            if (grid[x][y] == 1) nextK++;
                            if (nextK <= k && !visited[x][y][nextK]) {
                                visited[x][y][nextK] = true;
                                cellToExplore.offer(new int[]{x, y, nextK});
                            }
                        }
                    }
                }
            }
            distance++;

        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }


    public static void main(String[] args) {
        int[][] grid = new int[][] {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        var obj = new ShortestPathGridObstaclesElimination();

        System.out.println(Arrays.deepToString(grid));
        System.out.println("Shortest path " + obj.shortestPath(grid, 1));
    }
}
