package org.javainaction.recursion;

import java.util.function.Supplier;

/**
 * Give three strings ‘m’, ‘n’, and ‘p’, write a method to find out if ‘p’ has been formed by interleaving ‘m’ and ‘n’.
 * ‘p’ would be considered interleaving ‘m’ and ‘n’ if it contains all the letters from ‘m’ and ‘n’ and
 * the order of letters is preserved too.
 *
 * Example 1:
 *
 * Input: m="abd", n="cef", p="abcdef"
 * Output: true
 * Explanation: 'p' contains all the letters from 'm' and 'n' and preserves their order too.
 * Example 2:
 *
 * Input: m="abd", n="cef", p="adcbef"
 * Output: false
 * Explanation: 'p' contains all the letters from 'm' and 'n' but does not preserve the order.
 * Example 3:
 *
 * Input: m="abc", n="def", p="abdccf"
 * Output: false
 * Explanation: 'p' does not contain all the letters from 'm' and 'n'.
 * Example 4:
 *
 * Input: m="abcdef", n="mnop", p="mnaobcdepf"
 * Output: true
 * Explanation: 'p' contains all the letters from 'm' and 'n' and preserves their order too.
 */
public class StringInterweaving {
    //O(nm) time | O(nm) time where n is first string length
    // and m are lengths of second string
    public static boolean interweavingStrings(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        Boolean[][] cache = new Boolean[s1.length() + 1][s2.length() + 1];
        return isInterleavingStrings(s1, s2, s3, 0, 0, 0, cache);
    }

    public static boolean isInterleavingStrings(String s1, String s2, String s3,
                                                int i, int j, int k,
                                                Boolean[][] cache) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }

        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }

        if (cache[i][j] != null) return cache[i][j];

        if (k == s3.length()) return true;

        Supplier<Boolean> supplierOne = () ->
                i < s1.length() && s1.charAt(i) == s3.charAt(k)
                        && isInterleavingStrings(s1, s2, s3, i + 1, j, k + 1, cache);

        Supplier<Boolean> supplierTwo = () ->
                j < s2.length() && s2.charAt(j) == s3.charAt(k)
                        && isInterleavingStrings(s1, s2, s3, i, j + 1, k + 1, cache);

        cache[i][j] = supplierOne.get() || supplierTwo.get();

        return cache[i][j];
    }

    public static void main(String[] args) {
        System.out.println(interweavingStrings("abd", "cef", "abcdef"));
        System.out.println(interweavingStrings("abd", "cef", "adcbef"));
        System.out.println(interweavingStrings("abc", "def", "abdccf"));
        System.out.println(interweavingStrings("abcdef", "mnop", "mnaobcdepf"));
        System.out.println(interweavingStrings("aabcc", "dbbca", "aadbbcbcac"));
    }
}
