package org.javainaction.trie;

import java.util.*;

/**
 * Write a function where it accepts a big string and a an array of small strings.
 * Write a function to return boolean (true|false) whether string in small string array is part of big string.
 *
 * Big string: "this is a big string"
 * Small string array: {"this", "yo", "is", "a", "bigger", "string", "kappa"}
 *
 * Output: {true, false, true, true, false, true, false}
 *
 */
public class MultiStringSearch {
    // O(ns + bs) time | O(ns) space
    public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {

        SuffixTrie suffixTrie = new SuffixTrie();
        for (String smallString : smallStrings) {
            suffixTrie.insert(smallString);
        }

        Set<String> containedStrings = new HashSet<>();

        for (int i = 0; i < bigString.length(); i++) {
            findSmallStringsIn(bigString, i, suffixTrie, containedStrings);
        }

        List<Boolean> solution = new ArrayList<>(smallStrings.length);
        for (String smallString : smallStrings) {
            solution.add(containedStrings.contains(smallString));
        }
        return solution;
    }

    public static void findSmallStringsIn(String str, int startIdx,
                                          SuffixTrie suffixTrie,
                                          Set<String> containedStrings) {
        TrieNode node = suffixTrie.root;
        for (int i = startIdx; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (!node.children.containsKey(letter)) {
                break;
            }
            node = node.children.get(letter);
            if (node.children.containsKey(suffixTrie.endSymbol)) {
                containedStrings.add(node.word);
            }
        }
    }
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word;
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';


        public void insert(String str) {
            TrieNode node = root;
            for (int j = 0; j < str.length(); j++) {
                char letter = str.charAt(j);
                if (!node.children.containsKey(letter)) {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                }
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
            node.word = str;
        }
    }

    public static void main(String[] args) {
        boolean[] expected = {true, false, true, true, false, true, false};
        List<Boolean> output =
                multiStringSearch(
                        "this is a big string",
                        new String[] {"this", "yo", "is", "a", "bigger", "string", "kappa"});
        System.out.println("Expected results " + output);
    }
}
