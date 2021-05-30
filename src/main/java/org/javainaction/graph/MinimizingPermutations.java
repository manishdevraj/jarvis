package org.javainaction.graph;

import java.util.*;

/**
 * In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N,
 * denoted as (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into increasing order,
 * repeatedly making the following operation:
 * Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 * Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
 * Signature
 * int minOperations(int[] arr)
 * Input
 * Array arr is a permutation of all integers from 1 to N, N is between 1 and 8
 * Output
 * An integer denoting the minimum number of operations required to arrange the permutation in increasing order
 * Example
 * If N = 3, and P = (3, 1, 2), we can do the following operations:
 * Select (1, 2) and reverse it: P = (3, 2, 1).
 * Select (3, 2, 1) and reverse it: P = (1, 2, 3).
 * output = 2
 */
public class MinimizingPermutations {
    static int minOperations(int[] arr) {
        Set<String> seen = new HashSet<>();

        Queue<int[]> candidates = new LinkedList<>();
        Queue<Integer> positions = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();

        candidates.add(arr);
        positions.add(0);
        levels.add(0);

        while(!candidates.isEmpty()) {
            int[] current = candidates.remove();
            int i = positions.remove();
            int level = levels.remove();

            seen.add(Arrays.toString(current));

            while(i < current.length && current[i] == i + 1) i++;

            if(i == current.length) return level;

            for(int j = i + 1; j < current.length; ++j) {
                int[] otherCandidate = Arrays.copyOf(current, current.length);
                reverse(otherCandidate, i, j);

                if(!seen.contains(Arrays.toString(otherCandidate))) {
                    candidates.add(otherCandidate);
                    positions.add(i);
                    levels.add(level + 1);
                }
            }
        }

        return Integer.MAX_VALUE;

    }

    static void reverse(int[] arr, int i, int j) {
        while(i < j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++; j--;
        }
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    static int test_case_number = 1;
    static void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    static void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public static void run() {

        int n_1 = 5;
        int[] arr_1 = {1, 2, 5, 4, 3};
        int expected_1 = 1;
        int output_1 = minOperations(arr_1);
        check(expected_1, output_1);

        int n_2 = 3;
        int[] arr_2 = {3, 1, 2};
        int expected_2 = 2;
        int output_2 = minOperations(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }
    public static void main(String[] args) {
        run();
    }
}
