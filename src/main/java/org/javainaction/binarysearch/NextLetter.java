package org.javainaction.binarysearch;

/**
 * Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array greater
 * than a given ‘key’.
 *
 * Assume the given array is a circular list, which means that the last letter is assumed to be connected with the
 * first letter. This also means that the smallest letter in the given array is greater than the last letter of the
 * array and is also the first letter of the array.
 *
 * Write a function to return the next letter of the given ‘key’.
 *
 * Example 1:
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'f'
 * Output: 'h'
 * Explanation: The smallest letter greater than 'f' is 'h' in the given array.
 * Example 2:
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'b'
 * Output: 'c'
 * Explanation: The smallest letter greater than 'b' is 'c'.
 * Example 3:
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'm'
 * Output: 'a'
 * Explanation: As the array is assumed to be circular, the smallest letter greater than 'm' is 'a'.
 * Example 4:
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'h'
 * Output: 'a'
 * Explanation: As the array is assumed to be circular, the smallest letter greater than 'h' is 'a'.
 * @see CeilingOfANumber
 */
public class NextLetter {
    public static char searchNextLetter(char[] letters, char key) {
        int n = letters.length;
        //because array is circular, otherwise for key > letter[n - 1] we would have returned ceiling as letter[n - 1]
        if (key < letters[0] || key > letters[n - 1]) return letters[0];

        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (key < letters[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        //because we have circular list and we now have math, we can use mod to point at next possible cieling of the
        //number
        //For [a, c, f, h] and target b = 98 integer representation, when b is not found in array, left points at 1
        //[ 1 % 4 ] points at 1st index which is c character as expected
        return letters[left % n];
    }

    public static void main(String[] args) {
        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        System.out.println(searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));
    }
}
