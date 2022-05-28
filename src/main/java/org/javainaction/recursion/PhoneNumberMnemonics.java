package org.javainaction.recursion;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the 
 * number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * Numeric pad represents:
 *
 * '0': "0"
 * '1': "1"
 * '2': "abc"
 * '3': "def"
 * '4': "ghi"
 * '5': "jkl"
 * '6': "mno"
 * '7': "pqrs"
 * '8': "tuv"
 * '9': "wxyz"
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * @see GeneralizedAbbreviationRecursive
 * @see LetterCaseStringPermutation
 */
public class PhoneNumberMnemonics {
    public static Map<Character, String> mnemonicsMap = new HashMap<>();

    static {
        mnemonicsMap.put('0', "0");
        mnemonicsMap.put('1', "1");
        mnemonicsMap.put('2', "abc");
        mnemonicsMap.put('3', "def");
        mnemonicsMap.put('4', "ghi");
        mnemonicsMap.put('5', "jkl");
        mnemonicsMap.put('6', "mno");
        mnemonicsMap.put('7', "pqrs");
        mnemonicsMap.put('8', "tuv");
        mnemonicsMap.put('9', "wxyz");
    }

    public static ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
        ArrayList<String> mnemonicsResult = new ArrayList<>();
        String[] currentPhoneNumber = new String[phoneNumber.length()];
        Arrays.fill(currentPhoneNumber, "");
        phoneNumberMnemonicsRecursive(0, phoneNumber, currentPhoneNumber, mnemonicsResult);
        return mnemonicsResult;
    }

    public static void phoneNumberMnemonicsRecursive(int position, String phoneNumber,
                                              String[] currentPhoneNumber,
                                              ArrayList<String> mnemonicsResult) {
        if (position == phoneNumber.length()) {
            String mnemonic = String.join("", currentPhoneNumber);
            mnemonicsResult.add(mnemonic);
        } else {
            char digit = phoneNumber.charAt(position);
            //for every digit we need to try combinations
            //recursive call stack with pos = 0 we put all characters for 2 in call stack
            // [a, null],  [b, null],  [c, null]
            //in stack for a when pos = 1 we try letters for 3 and for first result
            //we get "ad", "ae", "af"
            String letters = mnemonicsMap.get(digit);
            for(char c : letters.toCharArray()) {
                currentPhoneNumber[position] = String.valueOf(c);
                phoneNumberMnemonicsRecursive(position + 1, phoneNumber,
                        currentPhoneNumber, mnemonicsResult);
            }
        }
    }

    public static ArrayList<String> phoneNumberMnemonicsList(String phoneNumber) {
        ArrayList<String> mnemonicsResult = new ArrayList<>();
        List<String> currentPhoneNumber = new ArrayList<>();
        phoneNumberMnemonicsRecursive(0, phoneNumber, currentPhoneNumber, mnemonicsResult);
        return mnemonicsResult;
    }

    public static void phoneNumberMnemonicsRecursive(int position, String phoneNumber,
                                                     List<String> currentPhoneNumber,
                                                     ArrayList<String> mnemonicsResult) {
        if (position == phoneNumber.length()) {
            String mnemonic = String.join("", currentPhoneNumber);
            mnemonicsResult.add(mnemonic);
        } else {
            char digit = phoneNumber.charAt(position);
            //for every digit we need to try combinations
            //recursive call stack with pos = 0 we put all characters for 2 in call stack
            // [a, null],  [b, null],  [c, null]
            //in stack for a when pos = 1 we try letters for 3 and for first result
            //we get "ad", "ae", "af"
            String letters = mnemonicsMap.get(digit);
            for(char c : letters.toCharArray()) {
                currentPhoneNumber.add(String.valueOf(c));
                phoneNumberMnemonicsRecursive(position + 1, phoneNumber,
                        currentPhoneNumber, mnemonicsResult);
                currentPhoneNumber.remove(currentPhoneNumber.size() - 1);
            }
        }
    }

    public static List<String> letterCombinations(String phoneNumber) {
        LinkedList<String> queue = new LinkedList<>();
        if(phoneNumber.isEmpty()) return queue;;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        queue.add("");
        for(int i = 0; i < phoneNumber.length(); i++){
            int digit = Character.getNumericValue(phoneNumber.charAt(i));
            while(queue.peek().length() == i){
                String t = queue.poll();
                for(char s : mapping[digit].toCharArray())
                    queue.offer(t + s);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        String phoneNumber = "1905";
        String[] expectedValues =
                new String[] {
                        "1w0j", "1w0k", "1w0l", "1x0j", "1x0k", "1x0l", "1y0j", "1y0k", "1y0l", "1z0j", "1z0k",
                        "1z0l"
                };
        System.out.println("Mnemonics for 1905 are  " + phoneNumberMnemonics(phoneNumber));
        System.out.println("Mnemonics for 1905 are  " + letterCombinations(phoneNumber));
        System.out.println("Mnemonics for 1905 are  " + phoneNumberMnemonicsList(phoneNumber));


        System.out.println("Mnemonics for 23 are  " + phoneNumberMnemonics("23"));
        System.out.println("Mnemonics for 23 are  " + letterCombinations("23"));
        System.out.println("Mnemonics for 23 are  " + phoneNumberMnemonicsList("23"));
    }
}
