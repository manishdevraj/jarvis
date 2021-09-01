package org.javainaction.dp.palindromsubseq;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 * @see LongestPalindromicSubstring for how to check palindrome substring
 * @see org.javainaction.recursion.Permutations for how to handle permutation
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String str) {
        int N = str.length();
        var isPalindrome = new boolean[N][N];
        var result = new ArrayList<List<String>>();
        partitionRecursive(str, result, 0, new ArrayList<>(), isPalindrome);
        return result;
    }

    public void partitionRecursive(String str,
                                   List<List<String>> result,
                                   int start,
                                   List<String> currentList,
                                   boolean[][] dp) {

        //base case, we have reached at the end of computation, add whatever we may have
        if (start >= str.length()) {
            result.add(new ArrayList<>(currentList));
        }

        //like permutations we are trying for all permutations from 0 to end
        for (int end = start; end < str.length(); end++) {
            //if we have palindrome and we have palindrome for previous letters too
            if (str.charAt(start) == str.charAt(end)  && (end - start <= 2 || dp[start + 1][end - 1])) {
                //store result so that we can use that to find if we have already computed this
                dp[start][end] = true;
                currentList.add(str.substring(start, end + 1));
                //try for end + 1 combinations
                partitionRecursive(str, result, end + 1, currentList, dp);
                //remove permutation at the end of processing
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        var obj = new PalindromePartitioning();
        System.out.println("Palindrome partitioning " + obj.partition("aab"));
    }
}
