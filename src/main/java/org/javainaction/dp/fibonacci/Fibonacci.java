package org.javainaction.dp.fibonacci;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 *
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 */
public class Fibonacci {
    public int bottomUpMemoize(int n) {
        if (n <= 0) return n;
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    private int bottomUp(int n) {
        if (n < 2) return n;
        int n1 = 0;
        int n2 = 1, temp;
        for (int i = 2; i <= n; i++) {
            temp = n1 + n2;
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        System.out.println("5th Fibonacci is ---> " + fib.bottomUpMemoize(5));
        System.out.println("6th Fibonacci is ---> " + fib.bottomUpMemoize(6));
        System.out.println("7th Fibonacci is ---> " + fib.bottomUpMemoize(7));

        System.out.println("5th Fibonacci is ---> " + fib.bottomUp(5));
        System.out.println("6th Fibonacci is ---> " + fib.bottomUp(6));
        System.out.println("7th Fibonacci is ---> " + fib.bottomUp(7));
    }
}
