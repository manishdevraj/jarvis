package org.javainaction.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function that returns the nth Fibonacci number for given integer
 *
 * Example 1 : n 2
 * Output : 1 as series would (0,1)
 *
 * Example 6 : n 6
 * Output : 5 as series would (0,1,1,2,3,5)
 */
public class NthFibonacci {
    //O(n) time | O(n) space
    private static int getNthFib(int n) {
        Map<Integer, Integer> memoize = new HashMap<>();
        memoize.put(2, 1);
        memoize.put(1, 0);
        return nthFibonacci(n, memoize);
    }

    private static int nthFibonacci(int n, Map<Integer, Integer> memoize) {
        if (memoize.containsKey(n)) return memoize.get(n);
        memoize.put(n, nthFibonacci(n - 1, memoize) + nthFibonacci(n - 2, memoize));
        return memoize.get(n);
    }

    //O(n) time | O(1) space
    private static int getNthFibonacci(int n) {
        int[] result = new int[]{0 , 1};
        if (n == 1) return result[n - 1];
        int count = 3;
        while (count++ <= n) {
            int nextFibonacci = result [0] + result[1];
            result[0] = result[1];
            result[1] = nextFibonacci;
        }
        return result[1];
    }

    public static void main(String[] args) {
        System.out.println("Nth fibonacci for n = 6 : " + getNthFib(6));
        System.out.println("Nth fibonacci for n = 2 : " + getNthFib(2));
        System.out.println("Nth fibonacci for n = 6 : " + getNthFibonacci(6));
        System.out.println("Nth fibonacci for n = 2 : " + getNthFibonacci(2));
    }


}
