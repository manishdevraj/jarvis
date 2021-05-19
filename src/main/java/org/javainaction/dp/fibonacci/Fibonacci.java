package org.javainaction.dp.fibonacci;

public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        System.out.println("5th Fibonacci is ---> " + fib.bottomUpMemoize(5));
        System.out.println("6th Fibonacci is ---> " + fib.bottomUpMemoize(6));
        System.out.println("7th Fibonacci is ---> " + fib.bottomUpMemoize(7));

        System.out.println("5th Fibonacci is ---> " + fib.bottomUp(5));
        System.out.println("6th Fibonacci is ---> " + fib.bottomUp(6));
        System.out.println("7th Fibonacci is ---> " + fib.bottomUp(7));
    }

    public int bottomUpMemoize(int n) {
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
}
