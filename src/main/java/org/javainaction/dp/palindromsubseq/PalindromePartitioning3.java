package org.javainaction.dp.palindromsubseq;

import java.util.HashMap;

/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 *
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 *
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 *
 * Input: s = "leetcode", k = 8
 * Output: 0
 */
public class PalindromePartitioning3 {

    public int palindromePartition(String s, int k) {
        var dp = new HashMap<String, Integer>();
        return palindromePartitionDfs(s, k, 0, dp);
    }

    private int palindromePartitionDfs(String s, int k, int start, HashMap<String, Integer> dp) {
        String key = start + "#" + k;
        //Case1:  already in computed
        if (dp.containsKey(key)) return dp.get(key);

        //Case2: base case that each substring just have one character
        //there is no cost for single string as long as we have available K partition for it
        if (s.length() - start == k) return 0;

        //Case3: base case that need to transfer whole substring into palindrome
        if (k == 1)
            return cost(s, start, s.length() - 1);

        int cost = Integer.MAX_VALUE;
        //keep making next part of substring into palindrome
        //split string into k parts
        for (int i = start + 1; i <= s.length() - k + 1; i++) {
            //compare different sides to get the minimum cost
            //either last cost or
            //cost to convert string from i to len with k - 1 splits
            //and cost to to convert string between start and i - 1
            cost = Math.min(cost, palindromePartitionDfs(s, k - 1, i, dp) + cost(s, start, i - 1));
        }
        dp.put(key, cost);
        return dp.get(key);
    }

    /**
     * Calculate the cost of transferring one substring into palindrome string
     */
    private int cost(String s, int i, int j) {
        int cost = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) cost++;
            i++;
            j--;
        }
        return cost;
    }

    public static void main(String[] args) {
        var obj = new PalindromePartitioning3();
        System.out.println("Palindrome partitioning " + obj.palindromePartition("abc", 2));
        System.out.println("Palindrome partitioning " + obj.palindromePartition("aabbc", 3));
        System.out.println("Palindrome partitioning " + obj.palindromePartition("leetcode", 8));
    }
}
