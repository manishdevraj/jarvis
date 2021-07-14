package org.javainaction.dp;

import java.util.Arrays;

/**
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom
 * sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or
 * to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented
 * in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented
 * in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom.
 * A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either
 * wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after
 * dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 *
 * Example 1:
 *
 * Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * Output: [1,-1,-1,-1,-1]
 * Explanation: This example is shown in the photo.
 * Ball b0 is dropped at column 0 and falls out of the box at column 1.
 * Ball b1 is dropped at column 1 and will get stuck in the box between column 2 and 3 and row 1.
 * Ball b2 is dropped at column 2 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b3 is dropped at column 3 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b4 is dropped at column 4 and will get stuck on the box between column 2 and 3 and row 1.
 * Example 2:
 *
 * Input: grid = [[-1]]
 * Output: [-1]
 * Explanation: The ball gets stuck against the left wall.
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * Output: [0,1,2,3,4,-1]
 * @see WaterfallStreams
 */
public class WhereWillBallFall {
    // O(grid.length * grid[0].length) time, because for every column we iterate at most all rows (top to bottom).
    // O(1) additional space. O(grid[0].length) space to store the result.
    public int[] findBall(int[][] grid) {
        if (grid == null || grid.length == 0) return new int[0];
        //ball is dropped at each index and we need to each row bottom value if they have the ball
        int[] result = new int[grid[0].length];
        // Each loop computes the result for when be drop a ball in column i.
        for (int i = 0; i < grid[0].length; ++i) {
            int currRow = 0, currCol = i;

            //at each fall make sure we are within grid
            while (currRow < grid.length) {
                // We go to the right if the current value and the value to the right are both equal to 1.
                if (grid[currRow][currCol] == 1 && currCol + 1 < grid[0].length
                        &&  grid[currRow][currCol + 1] == 1){
                    currRow++;
                    currCol++;
                // We go to the left if the current value and the value to the left are both equal to -1.
                } else if (grid[currRow][currCol] == -1 && currCol - 1 >= 0
                        &&  grid[currRow][currCol - 1] == -1){
                    currRow++;
                    currCol--;
                } else {
                    // If we can't move to the left, and we can't move to the right,
                    // then the ball is stuck because there is no other way to move.
                    break;
                }
            }
            //if our row reached the current row then that means we could reach at the bottom
            //use column value to indicate position of ball
            result[i] = currRow == grid.length ? currCol : -1;
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,1,1,1,1,1}, {-1,-1,-1,-1,-1,-1}, {1,1,1,1,1,1}, {-1,-1,-1,-1,-1,-1}};
        System.out.println(Arrays.deepToString(grid));
        var obj = new WhereWillBallFall();
        System.out.println(Arrays.toString(obj.findBall(grid)));

        grid = new int[][]{{1,1,1,-1,-1}, {1,1,1,-1,-1}, {-1,-1,-1,1,1}, {1,1,1,1,-1}, {-1,-1,-1,-1,-1}};
        System.out.println(Arrays.deepToString(grid));
        obj = new WhereWillBallFall();
        System.out.println(Arrays.toString(obj.findBall(grid)));
    }
}
