package org.javainaction.trie;

/**
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix
 * and the suffix suffix. If there is more than one valid index, return the largest of them.
 * If there is no such word in the dictionary, return -1.
 *
 *
 * Example 1:
 *
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 *
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 */
public class PrefixSuffixSearch {
    class TrieNode {
        TrieNode[] children;
        int weight;
        public TrieNode() {
            children = new TrieNode[27];
            // 'a' - 'z' and '{'. 'z' and '{' are neighbours in ASCII table
            weight = 0;
        }

        public void insert(String s, int weight) {
            TrieNode node = root;
            for (char letter : s.toCharArray()) {
                int n = letter - 'a';
                if (node.children[n] == null) {
                    node.children[n] = new TrieNode();
                }
                node = node.children[n];
                node.weight = weight;
            }
        }

        public int startsWith(String prefix) {
            TrieNode node = root;
            for (char letter : prefix.toCharArray()) {
                TrieNode next = node.children[letter - 'a'];
                if (next == null) return -1;
                node = next;
            }
            return node.weight;
        }
    }

    TrieNode root = null;
    // apple -> {apple, e{apple, le{apple, ple{apple, pple{apple, apple{apple;
    public PrefixSuffixSearch(String[] words) {
        root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            for (int j = 0; j <= str.length(); j++) {
                root.insert(str.substring(j) + '{' + str, i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        return root.startsWith(suffix + '{' + prefix);
    }

    public static void main(String[] args) {
        /**
         * WordFilter wordFilter = new WordFilter(["apple"]);
         * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
         */
        var worldFilter = new PrefixSuffixSearch(new String[]{"apple"});
        System.out.println(worldFilter.f("a", "e"));
    }
}
