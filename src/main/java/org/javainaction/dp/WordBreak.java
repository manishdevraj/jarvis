package org.javainaction.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * @see WordBreakProblem
 */
public class WordBreak {
    private static boolean wordBreak(String[] dict, String str) {
        boolean[] dp = new boolean[str.length() + 1];
        Set<String> wordSet = Stream.of(dict).collect(Collectors.toSet());

        dp[0] = true;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] && wordSet.contains(str.substring(j, i));
                if (dp[i]) break;
            }
        }
        return dp[str.length()];
    }

    public static void main(String[] arg) {
        String[] words = {"the", "quick", "brown", "fox"};
        String str = "thequickbrownfox";

        System.out.println("thequickbrownfox [the, quick, brow, fox] : " + wordBreak(words, str));

        words = new String[]{"apple","pen"};
        str = "applepenapple";
        System.out.println("applepenapple [apple, pen] : " + wordBreak(words, str));

        words = new String[]{"cats","dog","sand","and","cat"};
        str = "catsandog";
        System.out.println("catsandog [cats, dog, sand, and, cat] : " + wordBreak(words, str));
    }
}
