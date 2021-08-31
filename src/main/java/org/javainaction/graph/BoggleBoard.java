package org.javainaction.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given a 2D array of unequal height and width and list of words
 *
 * Write a function to return an array of words contained in the boggle board.
 *
 * A word can be constructed in boggle board by connecting adjacent nodes (diagonally, vertically, horizontally)
 * while using single word at a position just once without repetition. Two or more words can overlap each other.
 *
 *      char[][] board = {
 *       {'t', 'h', 'i', 's', 'i', 's', 'a'},
 *       {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
 *       {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
 *       {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
 *       {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
 *       {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
 *       {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
 *       {'N', 'O', 'T', 'R', 'E', '-', 'P'},
 *       {'x', 'x', 'D', 'E', 'T', 'A', 'E'},
 *     };
 *
 *     String[] words = {
 *       "this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"
 *     };
 *     String[] expected = {"this", "is", "a", "simple", "boggle", "board", "NOTRE-PEATED"};
 *
 */
public class BoggleBoard {

    // O(nm*8^s + ws) time | O(nm+ ws) space
    public static List<String> boggleBoard(char[][] board, String[] words) {
        SuffixTrie suffixTrie = new SuffixTrie();
        for (String word : words) {
            suffixTrie.insert(word);
        }

        Set<String> finalWords = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                exploreBoard(board, i, j, visited, finalWords, suffixTrie.root);
            }
        }
        return List.copyOf(finalWords);
    }

    private static void exploreBoard(char[][] board, int i, int j,
                                     boolean[][] visited,
                                     Set<String> finalWords,
                                     TrieNode trieNode) {

        if (visited[i][j]) return;

        char letter = board[i][j];
        if (!trieNode.children.containsKey(letter)) return;

        visited[i][j] = true;

        trieNode = trieNode.children.get(letter);
        if (trieNode.children.containsKey('*'))
            finalWords.add(trieNode.word); //add word as we got match

        List<Integer[]> neighbors = getNeighbors(board, i, j);

        for (Integer[] neighbor : neighbors) {
            exploreBoard(board, neighbor[0], neighbor[1], visited, finalWords, trieNode);
        }

        visited[i][j] = false;
    }

    private static List<Integer[]> getNeighbors(char[][] board, int i, int j) {
        List<Integer[]> neighbors = new ArrayList<>();

        if (i > 0 && j > 0) neighbors.add(new Integer[]{ i - 1, j - 1}); // top left
        if (i > 0 && j < board[i].length - 1) neighbors.add(new Integer[]{ i - 1, j + 1}); // top right
        if (i < board.length - 1 && j > 0) neighbors.add(new Integer[]{ i + 1, j - 1}); // bottom left
        if (i < board.length - 1 && j < board[i].length - 1) neighbors.add(new Integer[]{ i + 1, j + 1}); //bottom right

        if (i > 0) neighbors.add(new Integer[]{ i - 1, j}); // up
        if (j < board[i].length - 1) neighbors.add(new Integer[]{ i, j + 1}); // right
        if (j > 0) neighbors.add(new Integer[]{ i, j - 1}); // left
        if (i < board.length - 1) neighbors.add(new Integer[]{ i + 1, j}); //down

        return neighbors;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'t', 'h', 'i', 's', 'i', 's', 'a'},
                {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
                {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
                {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
                {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
                {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'N', 'O', 'T', 'R', 'E', '-', 'P'},
                {'x', 'x', 'D', 'E', 'T', 'A', 'E'},
        };
        String[] words = {
                "this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"
        };
        String[] expected = {"this", "is", "a", "simple", "boggle", "board", "NOTRE-PEATED"};

        List<String> output = boggleBoard(board, words);

        System.out.println("Boggle board contains : " + output);
    }
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public void insert(final String str) {
            TrieNode node = root;
            for (int i = 0; i < str.length(); i++) {
                char letter = str.charAt(i);
                node.children.putIfAbsent(letter, new TrieNode());
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
            node.word = str;
        }
    }
}
