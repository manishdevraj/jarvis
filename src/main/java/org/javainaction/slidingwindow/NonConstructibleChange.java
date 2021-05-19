package org.javainaction.slidingwindow;

import java.util.Arrays;

/**
 * Given the array of positive integers with coins in your possession find the minimum number of the change you
 * cannot create
 *
 * For e.g. if you had {1, 2, 5} then we cannot create change 4
 * If you are given no coin then min change you cannot create is 1
 */
public class NonConstructibleChange {
    public static void main(String[] args) {
        int[] input = new int[] {5, 7, 1, 1, 2, 3, 22};
        System.out.println("With coins " + Arrays.toString(input)
                + " we cannot create min change " + nonConstructibleChange(input));

        input = new int[] {1, 1, 1, 1};
        System.out.println("With coins " + Arrays.toString(input)
                + " we cannot create min change " + nonConstructibleChange(input));

        input = new int[] {1, 1, 3};
        System.out.println("With coins " + Arrays.toString(input)
                + " we cannot create min change " + nonConstructibleChange(input));
    }
    public static int nonConstructibleChange(int[] coins) {
        if (coins == null || coins.length == 0) return 1;
        Arrays.sort(coins);

        int currentChange = 0;

        for (int coin : coins) {
            if (coin > currentChange + 1) {
                return currentChange + 1;
            }
            currentChange += coin;
        }

        return currentChange + 1;
    }
}
