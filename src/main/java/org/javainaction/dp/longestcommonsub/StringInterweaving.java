package org.javainaction.dp.longestcommonsub;

/**
 * Give three strings ‘m’, ‘n’, and ‘p’, write a method to find out if ‘p’ has been formed by interleaving ‘m’ and ‘n’.
 * ‘p’ would be considered interleaving ‘m’ and ‘n’ if it contains all the letters from ‘m’ and ‘n’ and the order of letters is preserved too.
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
    public static boolean interweavingStrings(String one, String two, String three) {
        if (three.length() != one.length() + two.length()) return false;

        Boolean[][] cache = new Boolean [one.length() + 1][two.length() + 1];
        return areInterwoven(one, two, three, 0, 0, cache);
    }

    private static boolean areInterwoven(String one, String two, String three, int i, int j, Boolean[][] cache) {
        if(cache[i][j] != null) return cache[i][j];

        int k = i + j;
        if(k == three.length()) return true;

        if (i < one.length() && one.charAt(i) == three.charAt(k)) {
            cache[i][j] = areInterwoven(one, two, three, i + 1, j, cache);
            return cache[i][j];
        }

        if (j < two.length() && two.charAt(j) == three.charAt(k)) {
            cache[i][j] = areInterwoven(one, two, three, i, j + 1, cache);
            return cache[i][j];
        }

        cache[i][j] = false;
        return cache[i][j];
    }

    public static void main(String[] args) {
        System.out.println(interweavingStrings("abd", "cef", "abcdef"));
        System.out.println(interweavingStrings("abd", "cef", "adcbef"));
        System.out.println(interweavingStrings("abc", "def", "abdccf"));
        System.out.println(interweavingStrings("abcdef", "mnop", "mnaobcdepf"));
    }
}
