package org.javainaction.string;

/**
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated
 * successive characters as a single count and character. For example,
 * the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 *
 * Implement run-length encoding and decoding. You can assume the string to be encoded have no digits and consists
 * solely of alphabetic characters. You can assume the string to be decoded is valid.
 * @see RunLengthEncoding2 Where we limit digit to 0...9
 * @see RunLengthEncoding3 Where we need to replace output with char array
 */
public class RunLengthEncoding {

    public static String encode(String string) {
        if (string == null || string.isEmpty()) return null;
        StringBuilder encoded = new StringBuilder();
        int frequency = 1;

        for (int i = 1; i < string.length(); i++) {
            char curChar = string.charAt(i);
            //previous character so we can compare
            char prevChar = string.charAt(i  -1);

            if (curChar != prevChar) {
                //we found new character, apply run length encoding for previous character
                encoded.append(frequency).append(prevChar);
                //reset counter
                frequency = 0;
            }
            frequency++;
        }
        //trailing case
        encoded.append(frequency);
        encoded.append(string.charAt(string.length() - 1));
        return encoded.toString();
    }

    public static String decode(String input) {
        if (input == null || input.isEmpty()) return null;
        StringBuilder decoded = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 1; i <= chars.length; ) {
            if (Character.isDigit(chars[i - 1])) {
                int count = Integer.parseInt(String.valueOf(chars[i - 1]));
                while (count-- != 0) decoded.append(chars[i]);
            }
            //we are jumping by two positions : one for digit and one for character
            i += 2;
        }

        return decoded.toString();
    }

    public static void main(String[] args){
        String encode = "4A3B2C1D2A";
        String decode = "AAAABBBCCDAA";
        System.out.println("Encoded value for "+ decode +" is " + encode(decode));
        System.out.println("Encoded value for AAAA is " + encode("AAAA"));

        System.out.println("Encoded value for "+ encode +" is " + decode(encode));
        System.out.println("Encoded value for 4A is " + decode("4A"));

    }
}
