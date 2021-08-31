package org.javainaction.string;

/**
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated
 * successive characters as a single count and character. For example,
 * the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 *
 * Implement run-length encoding and decoding. You can assume the string to be encoded have no digits and consists
 * solely of alphabetic characters. You can assume the string to be decoded is valid.
 *
 * To make things more interesting and we need to be able to decode characters as well so we can't naively run encode
 * AAAAAAAAAAAA as 12A as it could be decoded as 1AA. We need to limit our encode to less than 10 so we can uniquely
 * use 0...9 as decoding parameters for e.g. 9A3A
 *
 * Input: AAAAAAAAAAAAABBCCCCDD
 * Output:  9A4A2B4C2D
 * @see RunLengthEncoding Standard run length
 * @see RunLengthEncoding3 Where we need to replace output with char array
 */
public class RunLengthEncoding2 {
    public static String runLengthEncoding(String string) {
        StringBuilder encoded = new StringBuilder();
        int frequency = 1;

        for (int i = 1; i < string.length(); i++) {
            char curChar = string.charAt(i);
            char prevChar = string.charAt(i - 1);
            //when we found new character or we found frequency == 9 encode
            if (curChar != prevChar || frequency == 9) {
                encoded.append(frequency).append(prevChar);
                frequency = 0;
            }
            frequency++;
        }
        //trailing case
        encoded.append(frequency);
        encoded.append(string.charAt(string.length() - 1));
        return encoded.toString();
    }

    public static void main(String[] args){
        System.out.println("Encoded value for " + runLengthEncoding("AAAAAAAAAAAAABBCCCCDD"));
    }
}
