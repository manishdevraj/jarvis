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

    public DesignDictionary() {
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char letter : word.toCharArray()) {
            node.children.putIfAbsent(letter, new TrieNode());
            node = node.children.get(letter);
        }
        //add word at the end
        node.word = word;
    }

    public boolean search(String word) {
        return searchRecursive(word, 0, root);
    }

    public boolean searchRecursive(String word, int index, TrieNode node) {
        // when we have reached at the end make sure word at end of trie node is not empty, which means
        // we reached end of trie prematurely
        if (word.length() == index) return !node.word.equals("");

        if (word.charAt(index) != skipLetter) {
            //make sure current character is in trie
            // and recursively find next character from search pattern
            return node.children.containsKey(word.charAt(index))
                    && searchRecursive(word, index + 1, node.children.get(word.charAt(index)));
        } else {
            //if we have skip letter then escape check if node contains character
            //simply forward to child of node and recursively find next character from search pattern
            for (Map.Entry<Character, TrieNode> nextChild : node.children.entrySet()) {
                if (searchRecursive(word, index + 1, nextChild.getValue())) return true;
            }

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
