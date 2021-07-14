package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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
 *
 * Given a string s, we can transform every letter individually to be lowercase or uppercase to create another string.
 *
 * Return a list of all possible strings we could create. You can return the output in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 *
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 * Example 3:
 *
 * Input: s = "12345"
 * Output: ["12345"]
 * Example 4:
 *
 * Input: s = "0"
 * Output: ["0"]
 */
public class LetterCaseStringPermutation {
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();

        if (str == null) return permutations;

        Function<Character, Character> changeCaseFunction
                = (c) -> Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);

        permutations.add(str);

        for(int i = 0; i < str.length(); i++) {
            //we only need to change letters
            if (Character.isLetter(str.charAt(i))){
                int n = permutations.size();
                for (int j = 0; j < n; j++) {
                    //we try permutations of each characters from previous permutation
                    //get previous permutation and change case
                    //first time we have ad52 and we change i = 0 case so we add Ad52
                    //next time we have [ad52, Ad52] we need to change only i = 1 char
                    //so we get [ad52, Ad52, aD52, AD52] because we get previous permutations
                    char[] chars = permutations.get(j).toCharArray();
                    //change only ith character every time
                    chars[i] = changeCaseFunction.apply(chars[i]);
                    permutations.add(String.valueOf(chars));
                }
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        System.out.println("ad52 permutations are: " + findLetterCaseStringPermutations("ad52"));
        System.out.println("ab7c permutations are: " + findLetterCaseStringPermutations("ab7c"));
    }
}
