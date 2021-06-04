package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, find all of its permutations preserving the character sequence but changing case.
 *
 * Example 1:
 *
 * Input: "ad52"
 * Output: "ad52", "Ad52", "aD52", "AD52"
 * Example 2:
 *
 * Input: "ab7c"
 * Output: "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
 */
public class LetterCaseStringPermutation {
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null) return permutations;

        permutations.add(str);
        for(int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))){
                int n = permutations.size();
                for (int j = 0; j < n; j++) {
                    char[] chars = permutations.get(j).toCharArray();

                    if (Character.isUpperCase(chars[i])) {
                        chars[i] = Character.toLowerCase(chars[i]);
                    } else {
                        chars[i] = Character.toUpperCase(chars[i]);
                    }
                    permutations.add(String.valueOf(chars));
                }
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        List<String> result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }
}
