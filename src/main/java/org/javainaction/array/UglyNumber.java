package org.javainaction.array;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 *
 *
 * Example 1:
 *
 * Input: n = 6
 * Output: true
 * Explanation: 6 = 2 × 3
 * Example 2:
 *
 * Input: n = 8
 * Output: true
 * Explanation: 8 = 2 × 2 × 2
 * Example 3:
 *
 * Input: n = 14
 * Output: false
 * Explanation: 14 is not ugly since it includes the prime factor 7.
 * Example 4:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 */
public class UglyNumber {
    public static boolean isUgly(int n) {
        if (n < 1) return false;
        if (n == 1) return true;

        int[] uglyArray = new int[]{2, 3, 5};

        for (int ugly : uglyArray) {
            //while n is fully divisible by either 2 or 3 or 5 then n /= ugly makes it 1
            while (n % ugly == 0) n /= ugly;
        }

        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println("8th ugly number is " + isUgly(8));
        System.out.println("1st ugly number is " + isUgly(1));
        System.out.println("14th ugly number is " + isUgly(14));
    }
}
