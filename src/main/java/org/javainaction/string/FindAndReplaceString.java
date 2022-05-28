package org.javainaction.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" occurs at index 0 in s, so we replace it with "eee".
 * "cd" occurs at index 2 in s, so we replace it with "ffff".
 * Example 2:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" occurs at index 0 in s, so we replace it with "eee".
 * "ec" does not occur at index 2 in s, so we do nothing.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * k == indices.length == sources.length == targets.length
 * 1 <= k <= 100
 * 0 <= indexes[i] < s.length
 * 1 <= sources[i].length, targets[i].length <= 50
 * s consists of only lowercase English letters.
 * sources[i] and targets[i] consist of only lowercase English letters.
 */
public class FindAndReplaceString {

    public static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        if (indices == null || indices.length == 0 || sources == null || targets == null  ||
                sources.length != targets.length) return s;

        List<int[]> sortedIndices = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) sortedIndices.add(new int[]{indices[i], i});

        sortedIndices.sort((a, b) -> b[0] - a[0]);

        for (int[] index : sortedIndices) {
            System.out.println(Arrays.toString(index));
            int i = index[0];
            int j = index[1];
            String source = sources[j];
            String target = targets[j];
            if (s.substring(i, i + source.length()).equals(source)) {
                //prefix to keep data from right to left at current index
                //target allows replacement
                //suffix to keep previous replacement intact and exclude source's length
                s = s.substring(0, i) + target + s.substring(i + source.length());
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "abcd";
        int[] indices = new int[] {0, 2};
        String[] sources = new String[]{"a", "cd"};
        String[] targets = new String[]{"eee","ffff"};
        System.out.println("abcd with [0,2] and source=['a', 'cd'] and targets['eee', 'ffff'] => " +
                findReplaceString(s, indices, sources, targets));
    }
}
