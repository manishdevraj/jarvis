package org.javainaction.trie;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 * @see WordSearch2
 * @see org.javainaction.graph.BoggleBoard
 */
public class WordSearch {
    // O(nm*4^s + ws) time | O(nm+ ws) space
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(exploreBoard(board, i, j, words, visited,  0)) return true;
            }
        }
        return false;
    }

    private static boolean exploreBoard(char[][] board, int i, int j,
                                        char[] words,
                                        boolean[][] visited,
                                        int index) {

        //if we are at end of thr word search
        if (index == words.length) return true;

        //check bounds
        if (i < 0 || j < 0 || i == board.length || j == board[i].length) return false;
        //check if characters match
        if (words[index] != board[i][j]) return false;

        //check if unvisited
        if (visited[i][j]) return false;

        visited[i][j] = true;

        //try all neighbours
        boolean found = exploreBoard(board, i - 1, j, words, visited, index + 1)
                || exploreBoard(board, i + 1, j, words, visited, index + 1)
                || exploreBoard(board, i, j - 1, words, visited, index + 1)
                || exploreBoard(board, i, j + 1, words, visited, index + 1);

        visited[i][j] = false;

        return found;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {{'A','B','C','E'}, {'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(exist(board, "ABCCED"));
    }
}
