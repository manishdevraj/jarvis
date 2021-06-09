package org.javainaction.binarysearch;


/**
 * An ugly number is a positive integer that is divisible by a, b, or c.
 *
 * Given four integers n, a, b, and c, return the nth ugly number.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 * Example 2:
 *
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
 * Example 3:
 *
 * Input: n = 5, a = 2, b = 11, c = 13
 * Output: 10
 * Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
 * Example 4:
 *
 * Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
 * Output: 1999999984
 */
public class UglyNumber3 {
    /**
     * Calculate how many numbers from 1 to num are divisible by either a, b or c by using below formula:
     * num/a + num/b + num/c – num/lcm(a, b) – num/lcm(b, c) – num/lcm(a, c) + num/lcm(a, b, c)
     * Lowest common multiple
     */

    // O(log(MAX_ANS)), MAX_ANS = 2*10^9 time | O(1)
    public int nthUglyNumber(int n, int a, int b, int c) {
        int MAX_ANS = (int) 2e9; // 2*10^9
        int left = 0, right = MAX_ANS, result = 0;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (count(middle, a, b, c) >= n) {
                result = middle;
                right = middle - 1;
            } else
                left = middle + 1;
        }

       return result;
    }

    int count(long num, long longA, long longB, long longC) {
        return (int) (((num / longA) + (num / longB) + (num / longC))
                - num / lcm(longA, longB)
                - num / lcm(longB, longC)
                - num / lcm(longA, longC)
                + num / lcm(longA, lcm(longB, longC)));
    }

    long gcd(long a, long b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        System.out.println("4th ugly number " + new UglyNumber3().nthUglyNumber(4, 2, 3, 4));
        System.out.println("3rd ugly number " + new UglyNumber3().nthUglyNumber(3, 2, 3, 5));
    }
}
