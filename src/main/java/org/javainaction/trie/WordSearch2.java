package org.javainaction.trie;

import org.javainaction.graph.BoggleBoard;

import java.util.*;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 *
 *
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 * @see BoggleBoard
 */
public class WordSearch2 {
    // O(nm*8^s + ws) time | O(nm+ ws) space
    public static List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> finalWords = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                exploreBoard(board, i, j, visited, finalWords, trie.root);
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

        if (i > 0) neighbors.add(new Integer[]{ i - 1, j});
        if (j < board[i].length - 1) neighbors.add(new Integer[]{ i, j + 1});
        if (j > 0) neighbors.add(new Integer[]{ i, j - 1});
        if (i < board.length - 1) neighbors.add(new Integer[]{ i + 1, j});

        return neighbors;
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    static class Trie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public void insert(final String str) {
            TrieNode node = root;
            for (char letter: str.toCharArray()) {
                node.children.putIfAbsent(letter, new TrieNode());
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
            node.word = str;
        }
    }
    
    public static void main(String[] args) {
        char[][] board = new char[][] {{'o','a','a','n'}, {'e','t','a','e'},{'i','h','k','r'}, {'i','f','l','v'}};
        String[] words = new String[]{"oath","pea","eat","rain"};
        System.out.println(findWords(board, words));
    }
}
