package org.javainaction.string;

import java.util.function.BiFunction;

/**
 * Given a non empty string and key, write  function that returns new string obtained by shifting letters
 * by key positions. The letters needs to wrap around like z shifted one key would be a
 *
 *
 */
public class CaesarCypherEncryptor {
    // O(n) time | O(1) space
    public static String caesarCypherEncryptor(String str, int key) {
        char[] output = new char[str.length()];
        //add key to char's integer value and % with 26 as it is 26 lower letter alphabets
        BiFunction<Character, Integer, Character> encryptLowercase
                = (c, k) -> (char) ((char) (c - 'a' + k) % 26 + 'a');
        int i = 0;
        for (char c : str.toCharArray()) {
            output[i++] = encryptLowercase.apply(c, key);
        }
        return new String(output);
    }

    public static void main(String[] args) {
        System.out.println(CaesarCypherEncryptor.caesarCypherEncryptor("xyz", 2).equals("zab"));
    }
}
