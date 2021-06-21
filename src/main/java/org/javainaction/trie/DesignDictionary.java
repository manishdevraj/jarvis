package org.javainaction.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
public class DesignDictionary {
    static class TrieNode {
        public Map<Character, TrieNode> children = new HashMap<>();
        public String word = "";
    }

    TrieNode root = new TrieNode();
    char skipLetter = '.';

    /** Initialize your data structure here. */
    public DesignDictionary() {
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char letter : word.toCharArray()) {
            if (!node.children.containsKey(letter)) {
                TrieNode newNode = new TrieNode();
                node.children.put(letter, newNode);
            }
            node = node.children.get(letter);
        }
        node.word = word;
    }

    public boolean search(String word) {
        return searchRecursive(word, 0, root);
    }

    public boolean searchRecursive(String word, int index, TrieNode node) {
        if (word.length() == index) return !node.word.equals("");
        if (word.charAt(index) != skipLetter) {
            return node.children.containsKey(word.charAt(index))
                    && searchRecursive(word, index + 1, node.children.get(word.charAt(index)));
        } else {
            for (Map.Entry<Character, TrieNode> nextChild : node.children.entrySet())
                if (searchRecursive(word, index + 1, nextChild.getValue())) return true;
        }
        return false;
    }

    public static void main(String[] args){
        DesignDictionary wordDictionary = new DesignDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println("Search pad " + wordDictionary.search("pad")); // return False
        System.out.println("Search bad " + wordDictionary.search("bad")); // return True
        System.out.println("Search .ad " + wordDictionary.search(".ad")); // return True
        System.out.println("Search b.. " + wordDictionary.search("b..")); // return True
    }
}
