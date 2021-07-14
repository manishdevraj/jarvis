package org.javainaction.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be
 * built one character at a time by other words in words.
 *
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 * If there is no answer, return the empty string.
 *
 *
 * Example 1:
 *
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 *
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is
 * lexicographically smaller than "apply".
 */
public class LongestWordDictionary {

    public static String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        return trie.findLongestWord();
    }

    static class Trie {
        TrieNode root = new TrieNode('0', true, "");

        public void insert(String word) {
            TrieNode node = root;
            for (char letter : word.toCharArray()) {
                node.children.putIfAbsent(letter, new TrieNode(letter));
                node = node.children.get(letter);
            }
            node.isWord = true;
            node.word = word;
        }

        public String findLongestWord() {
            String result = "";
            Queue<TrieNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TrieNode node = queue.poll();
                    for(TrieNode child : node.children.values()){
                        //we only process complete words
                        if (child.isWord && child.word != null
                                //either trie child word is bigger than previous result
                                //or is lexicographically smallest
                                && (child.word.length() > result.length() || child.word.compareTo(result) < 0)) {
                            result = child.word;
                        }
                        //we might have bigger word made after this trie so try for next iteration
                        if (child.isWord) queue.offer(child);
                    }
                }
            }
            return result;
        }
    }

    static class TrieNode {
        char letter;
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
        String word;

        public TrieNode(char letter) {
            this.letter = letter;
        }

        public TrieNode(char letter, boolean isWord, String word) {
            this.letter = letter;
            this.isWord = isWord;
            this.word = word;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"a","banana","app","appl","ap","apply","apple"};
        System.out.println("Find longest word " + longestWord(words));
    }
}
