package org.javainaction.string;

/**
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
 * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
 * so "a" is considered a different type of stone from "A".
 *
 * Example 1:
 *
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 * Example 2:
 *
 * Input: J = "z", S = "ZZ"
 * Output: 0
 * Note:
 *
 * S and J will consist of letters and have length at most 50.
 * The characters in J are distinct.
 */
public class JewelsStones {
    //O(max (J, S)) time | O(1) space
    public static int findJewelsInStones(String J, String S) {
        int[] count = new int[126];
        int jewelsInStone = 0;
        for (char c : J.toCharArray())
            count[c]++;

        for (char c : S.toCharArray())
            jewelsInStone += count[c];

        return jewelsInStone;
    }

    public static void main(String[] args) {
        System.out.println(findJewelsInStones("aA", "aAAbbbb"));
        System.out.println(findJewelsInStones("z", "ZZ"));
    }

}
