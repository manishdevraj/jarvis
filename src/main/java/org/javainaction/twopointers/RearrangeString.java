package org.javainaction.twopointers;

/**
 * Given a string with lowercase, uppercase and numbers, rearrange it so that uppercase letters are first,
 * then lowercase and then numbers. It should maintain the original order.
 *
 * Input- "cdBnC52c" output - "BCcdnc52"
 * Input - "123abA" output - "Aab123"
 *
 * Rearrange it in o(n) in one pass without using extra space.
 */
public class RearrangeString {
    public String rerrangeWord(String word) {
        return new String(rerrangeWord(word.toCharArray()));
    }

    // This runs in O(n) with O(1) space
    public char[] rerrangeWord(char[] word) {
        int low = 0;
        int high = word.length-1;
        int mid = 0;

        while(mid <= high) {
            if (isUpperCase(word[mid])) swap(word, low++, mid++);
            if (isNumber(word[mid])) swap(word, mid, high--);
            if (isLowerCase(word[mid])) mid++;
        }
        return word;
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private boolean isLowerCase(char c) {
        return Character.isLowerCase(c);
    }
    private boolean isUpperCase(char c) {
        return Character.isUpperCase(c);
    }
    private boolean isNumber(char c) {
        return Character.isDigit(c);
    }

    public static void main(String[] args) {
        System.out.println(new RearrangeString().rerrangeWord("cdBnC52c"));
        System.out.println(new RearrangeString().rerrangeWord("123abA"));
    }
}
