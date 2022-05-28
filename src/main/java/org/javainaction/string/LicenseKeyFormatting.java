package org.javainaction.string;

/**
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 *
 * We want to reformat the string s such that each group contains exactly k characters, except for the first group, which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.
 *
 * Return the reformatted license key.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "5F3Z-2e-9-w", k = 4
 * Output: "5F3Z-2E9W"
 * Explanation: The string s has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 * Example 2:
 *
 * Input: s = "2-5g-3-J", k = 2
 * Output: "2-5G-3J"
 * Explanation: The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of English letters, digits, and dashes '-'.
 * 1 <= k <= 104
 */
public class LicenseKeyFormatting {

    /**
     * Key here is that
     * 1. if we have K = 4 and length of builder becomes 8 then it is time to tokenize with '-'
     * 2. All other times just keep on appending in reverse order
     * 3. At the end just reverse and make it capital case
     */
    public static String licenseKeyFormatting(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) return s;

        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--){
            if (chars[i] != '-') {
                //append - when we have k + 1 length matches length formatted string token
                // k + 1 because only after K + 1 =>  4 % (4 + 1) will be 4
                builder.append(builder.length() % (k + 1) == k ? "-" : "").append(chars[i]);
            }

        }
        return builder.reverse().toString().toUpperCase();
    }

    public static void main(String[] arg){
        System.out.println("5F3Z-2e-9-w with 4 => " + licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println("2-5G-3J with 2 => " + licenseKeyFormatting("2-5G-3J", 2));
    }
}
