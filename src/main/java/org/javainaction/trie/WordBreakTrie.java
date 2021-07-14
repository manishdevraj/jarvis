package org.javainaction.trie;

import java.util.*;

/**
 * Given a dictionary of words and a string made up of those words (no spaces), return the original sentence in a list.
 * If there is more than one possible reconstruction, return any of them. If there is no possible reconstruction,
 * then return null.
 *
 * For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string "thequickbrownfox",
 * you should return ['the', 'quick', 'brown', 'fox'].
 *
 * Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond",
 * return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].
 *
 * @see MultiStringSearch
 */
public class WordBreakTrie {

    private static List<String> wordBreak(String[] words, String str) {
        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }

        Set<String> foundMatch = new HashSet<>();

        findBigStringInSuffixTrie(str, root, foundMatch);
        return new ArrayList<>(foundMatch);
    }

    private static void findBigStringInSuffixTrie(String bigString, Trie root, Set<String> solution) {
        for (int i = 0; i < bigString.length(); i++) {
            findInSuffixTrie(bigString, i, root, solution);
        }
    }

    private static void findInSuffixTrie(String bigString, int startIdx, Trie trie, Set<String> solution) {
        char[] charArray = bigString.toCharArray();
        TrieNode node = trie.root;
        for (int i = startIdx; i < charArray.length; i++) {
            char letter = charArray[i];
            if (!node.children.containsKey(letter)) break;

            node = node.children.get(letter);
            if (node.children.containsKey(trie.endSymbol)) {
                solution.add(node.word);
            }
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    static class Trie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public void insert(String str) {
            TrieNode node = root;
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                if (!node.children.containsKey(c)) {
                    TrieNode children = new TrieNode();
                    node.children.put(c, children);
                }
                node = node.children.get(c);
            }
            node.children.put(endSymbol, null);
            node.word = str;
        }
    }

    public static void main(String[] arg) {
        String[] words = {"the", "quick", "brown", "fox"};
        String str = "thequickbrownfox";

        List<String> output = wordBreak(words, str);
        System.out.println(output);

        System.out.println(wordBreak(new String[] {"bed", "bath", "bedbath", "and", "beyond"}, "bedbathandbeyond"));

        String bigString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        String[] repeatedWords = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};

        System.out.println(wordBreak(repeatedWords, bigString));
    }
}
