package org.javainaction.dp.fibonacci;

/**
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 */

public class NTribonacciNumber {
    public int tribonacci(int n) {
        int[] dp = {0, 1, 1};
        //as Tn+3 = Tn + Tn+1 + Tn+2
        // we are only storing up to n, n + 1 and n + 2
        // when we % with 3 from 3 to n we are storing nth tribonacci for N integers
        //dp[0] = Tn, dp[1]= Tn+1 and dp[2] = Tn+2
        for (int i = 3; i <= n; ++i)
            dp[i % 3] = dp[0] + dp[1] + dp[2];
        return dp[n % 3];
    }

    public static void main(String[] args) {
        var fib = new NTribonacciNumber();
        System.out.println("4th Fibonacci is ---> " + fib.tribonacci(4));
        System.out.println("6th Fibonacci is ---> " + fib.tribonacci(6));
        System.out.println("25th Fibonacci is ---> " + fib.tribonacci(25));
    }
}
