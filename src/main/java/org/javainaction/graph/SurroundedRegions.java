package org.javainaction.graph;

import java.util.Arrays;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Surrounded regions should not be on the border, which means that any 'O' on
 * the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not
 * connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells
 * connected horizontally or vertically.
 * Example 2:
 *
 * Input: board = [["X"]]
 * Output: [["X"]]
 * @see RemoveIslands
 */
public class SurroundedRegions {
    public static void solve(char[][] board) {
        boolean[][] osToBorder = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean isBorder = i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1;
                if (!isBorder) continue;
                if (board[i][j] != 'O') continue;
                findRegionsOnBorder(board, i, j, osToBorder);
            }
        }

        //fill all non border connected regions with O's to X's
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (osToBorder[i][j]) continue;
                if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public static void findRegionsOnBorder(char[][] board, int i, int j, boolean[][] osToBorder) {
        if (i >=0 && i < board.length
                && j >=0 && j < board[i].length
                && !osToBorder[i][j]
                && board[i][j] == 'O') {

            osToBorder[i][j] = true;
            findRegionsOnBorder(board, i - 1, j, osToBorder);
            findRegionsOnBorder(board, i + 1, j, osToBorder);
            findRegionsOnBorder(board, i, j - 1, osToBorder);
            findRegionsOnBorder(board, i, j + 1, osToBorder);
        }
    }

    public static void main(String[] args) {
        // * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
        // * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
        char [][] input = {
                {'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        System.out.println(Arrays.deepToString(input));
        solve(input);
        System.out.println("Flipping O to X that are surrounded by Xs");
        System.out.println(Arrays.deepToString(input));

        input = new char[][]{
                {'O','O','O','O'},{'O','O','O','O'},{'O','O','O','O'},{'O','O','O','O'}};
        System.out.println(Arrays.deepToString(input));
        solve(input);
        System.out.println("Flipping O to X that are surrounded by Xs");
        System.out.println(Arrays.deepToString(input));
    }
}
