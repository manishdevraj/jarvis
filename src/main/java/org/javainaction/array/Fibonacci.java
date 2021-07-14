package org.javainaction.array;

public class Fibonacci {

    //O(n) time | O(1) space
    public static int getNthFib(int n) {
        int[] result = {0 , 1};
        int counter = 3;
        if(n == 1) return 0;
        while (counter <= n) {
            int nextFib = result[0] + result[1];
            result[0] = result[1];
            result[1] = nextFib;
            counter++;
        }
        return result[1];
    }
}
