package org.javainaction.string;

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 *
 * Example 1:
 *
 * Input: String="araaci", K=2
 * Output: 4
 * Explanation: The longest substring with no more than '2' distinct characters is "araa".
 * @see org.javainaction.slidingwindow.LongestSubstringKDistinct
 */
public class LongestSubstringKDistinct {

    public static int findLength(String str, int k) {
        if (str == null || str.length() == 0 || str.length() < k)
            throw new IllegalArgumentException();

        //user hashmap if we have more variants of letters such as capital letters, symbols, numbers
        int[] alphabets = new int[26];
        char[] chars = str.toCharArray();
        int count = 0, maxLength = 0, left = 0;
        for (int right = 0; right < chars.length; right++) {
            char rightChar = chars[right];
            if (alphabets[rightChar - 'a'] == 0) {
                count += 1;
            }
            alphabets[rightChar - 'a']++;
            //shrink window from left character as we got more than k distinct characters
            while (count > k) {
                char leftChar = chars[left];
                //decrement count of leaving window
                alphabets[leftChar - 'a']--;
                //change duplicate element count
                count--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("cbbebi", 3));
    }
}
