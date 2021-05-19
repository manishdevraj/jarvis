package org.javainaction.string;

/**
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated
 * successive characters as a single count and character. For example,
 * the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 *
 * Implement run-length encoding and decoding. You can assume the string to be encoded have no digits and consists
 * solely of alphabetic characters. You can assume the string to be decoded is valid.
 */
public class RunLengthEncoding {

    public static void main(String[] args){
        String encode = "4A3B2C1D2A";
        String decode = "AAAABBBCCDAA";
        System.out.println("Encoded value for "+ decode +" is " + encode(decode));
        System.out.println("Encoded value for AAAA is " + encode("AAAA"));

        System.out.println("Encoded value for "+ encode +" is " + decode(encode));
        System.out.println("Encoded value for 4A is " + decode("4A"));

    }

    public static String encode(String input) {
        if (input == null || input.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();
        char[] chars = input.toCharArray();
        char c = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (c == chars[i]) {
               count++;
            } else {
                builder.append(count).append(c);
                c = chars[i];
                count = 1;
            }
        }
        builder.append(count).append(c);
        return builder.toString();
    }

    public static String decode(String input) {
        if (input == null || input.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 1; i <= chars.length;) {
            int length = Integer.parseInt(String.valueOf(chars[i-1]));
            char c = chars[i];
            while (length != 0) {
                builder.append(c);
                length--;
            }
            i+=2;
        }
        return builder.toString();
    }
}
