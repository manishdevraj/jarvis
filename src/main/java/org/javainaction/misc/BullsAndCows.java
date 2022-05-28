package org.javainaction.misc;

/**
 * You are playing the Bulls and Cows game with your friend.
 *
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 *
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 *
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
 *
 *
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 *   |
 * "7810"
 * Example 2:
 *
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1123"        "1123"
 *   |      or     |
 * "0111"        "0111"
 * Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 *
 *
 * Constraints:
 *
 * 1 <= secret.length, guess.length <= 1000
 * secret.length == guess.length
 * secret and guess consist of digits only.
 */
public class BullsAndCows {
    public static String getHint(String secret, String guess) {
        if (secret == null || guess == null
                || secret.length() == 0
                || guess.length() == 0
                || secret.length() != guess.length()) return null;

        /**
         *
             "1123"        "1123"
               |      or     |
             "0111"        "0111"
         Cows Arr            [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
         Iteration 1         [-1, 1, 0, 0, 0, 0, 0, 0, 0, 0] => cowsCount => 0
         Iteration 2         [-1, 1, 0, 0, 0, 0, 0, 0, 0, 0] => cowsCount => 0 and BullsCount = 1
         Iteration 3         [-1, 0, 1, 0, 0, 0, 0, 0, 0, 0] => cowsCount => 1 and BullsCount = 1
         Iteration 4         [-1, -1, 1, 1, 0, 0, 0, 0, 0, 0] => cowsCount => 1 and BullsCount = 1

         **/
        int[] cows = new int[10];
        int bullsCount = 0;
        int cowsCount = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bullsCount++;
            } else {
                int secretChar = secret.charAt(i) - '0';
                int guessChar = guess.charAt(i) - '0';
                //we check -ve value as only if guess had it then it will make it negative
                if (cows[secretChar] < 0) cowsCount++;
                //we check +ve value as only if secret had it then it will make it positive
                if (cows[guessChar] > 0) cowsCount++;
                cows[secretChar]++; //mark presence of secret as +ve
                cows[guessChar]--; //mark presence in guest as -ve
            }
        }

        return bullsCount + "A" + cowsCount + "B";
    }
    public static void main(String[] args) {
        System.out.println(getHint("1807","7810"));
        System.out.println(getHint("1123","0111"));
        System.out.println(getHint("1122","2211"));
    }
}
