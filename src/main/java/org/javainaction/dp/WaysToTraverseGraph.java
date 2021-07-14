package org.javainaction.dp;

/**
 * Give a width and height of a grid where w * h >= 2 find how many ways you can reach to end of the grid which is
 * matrix[h][w] from matrix[0][0] starting position.
 *
 * Condition is that you cannot go to left or up from a given grid position. Only down or right
 *
 * Input: width 4 and height 3
 * Grid would be
 *          [x] [] [] []
 *          [] [] [] []
 *          [] [] [] [0]
 *
 *  Explanation to reach from x to 0
 *  We can go
 *  1. Down, Down, Right
 *  2. Right, Down, Down
 *  3. Down, Right, Down
 *
 *  Output: 10
 * @see UniquePaths
 */
public class WaysToTraverseGraph {
    //O(n * m) time | O(n * m) space
    public int numberOfWaysToTraverseGraph(int width, int height) {
        int[][] noOfWays = new int[height + 1][width + 1];

        for (int col = 1; col < width + 1; col++) {
            for (int row = 1; row < height + 1; row++) {
                if (row == 1 || col == 1) {
                    noOfWays[row][col] = 1;
                } else {
                    int up = noOfWays[row - 1][col];
                    int left = noOfWays[row][col - 1];
                    noOfWays[row][col] = up + left;
                }
            }
        }
        return noOfWays[height][width];
    }

    public static void main(String[] args) {
        int width = 4;
        int height = 3;
        int expected = 10;
        var actual = new WaysToTraverseGraph().numberOfWaysToTraverseGraph(width, height);
        System.out.println("WaysToTraverseGraph : " + actual);
    }
}
