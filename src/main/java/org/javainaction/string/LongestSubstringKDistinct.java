package org.javainaction.string;

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 *
 * Example 1:
 *
 * Input: String="araaci", K=2
 * Output: 4
 * Explanation: The longest substring with no more than '2' distinct characters is "araa".
 */
public class LongestSubstringKDistinct {

    public static int findLength(String str, int k) {
        if (str == null || str.length() == 0 || str.length() < k)
            throw new IllegalArgumentException();

        int[] alphabets = new int[26];
        char[] chars = str.toCharArray();
        int count = 0, maxLength = 0, start = 0;
        for (int end = 0; end < chars.length; end++) {
            char c = chars[end];
            if (alphabets[c - 'a'] == 0) {
                count += 1;
            }
            alphabets[c - 'a']++;
            while (count > k) {
                char s = chars[start];
                alphabets[s - 'a']--;
                count--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("cbbebi", 3));
    }
}
