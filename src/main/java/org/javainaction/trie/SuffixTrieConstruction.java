package org.javainaction.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Build a Trie data structure where class should have a root node and should support :
 * 1. Creating trie from a given string
 * 2. Searching for a string in current trie
 *
 * Note that every string added to trie ends with an end character '*'
 *
 * Input : "babc"
 *
 * {
 *     "c": {"*", true},
 *     "b": {
 *         "c": {"*", true},
 *         "a": {"b": {"c": {"*", true}}}
 *     },
 *     "a": {"b": {"c": {"*", true}}}
 * }
 *
 * Node this is different than Prefix Trie
 */
public class SuffixTrieConstruction {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }

        // O(n^2) time | O(n^2) space
        public void populateSuffixTrieFrom(String str) {
            for (int i = 0; i < str.length(); i++) {
                insertSubstingStartingAt(i, str);
            }
        }

        public void insertSubstingStartingAt(int i, String str) {
            TrieNode node = root;
            for (int j = i; j < str.length(); j++) {
                char letter = str.charAt(j);
                node.children.putIfAbsent(letter, new TrieNode());
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
        }

        public boolean contains(String str) {
            TrieNode node = root;
            for (int i = 0; i < str.length(); i++) {
                char letter = str.charAt(i);
                if (!node.children.containsKey(letter)) {
                    return false;
                }
                node = node.children.get(letter);
            }
            return node.children.containsKey(endSymbol);
        }
    }

    public static void main(String[] args) {
        var trie = new SuffixTrieConstruction.SuffixTrie("babc");
        System.out.println("Does trie contains 'abc' " + trie.contains("abc"));
    }
}
