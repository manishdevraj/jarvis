package org.javainaction.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any
 * subarrays from array B any number of times.
 * Signature
 * bool areTheyEqual(int[] arr_a, int[] arr_b)
 * Input
 * All integers in array are in the range [0, 1,000,000,000].
 * Output
 * Return true if B can be made equal to A, return false otherwise.
 * Example
 * A = [1, 2, 3, 4]
 * B = [1, 4, 3, 2]
 * output = true
 * After reversing the subarray of B from indices 1 to 3, array B will equal array A.
 */
public class ReverseToMakeEqual {
    boolean areTheyEqual(int[] array_a, int[] array_b) {
        Map<Integer, Integer> frequency = new HashMap<>();
        //get frequencies of all numbers from first array
        for (int a : array_a) {
            frequency.put(a, frequency.getOrDefault(a, 0) + 1);
        }

        for (int b : array_b) {
            //if we have new number then there is no way they can ever be equal no matter how much we flip the array
            if (!frequency.containsKey(b)) return false;

            frequency.put(b, frequency.get(b) - 1);
            //remove when last element frequency
            if (frequency.get(b) == 0) {
                frequency.remove(b);
            }
        }

        //we got all numbers matched
        return frequency.size() == 0;
    }

    int test_case_number = 1;
    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }

    public void run() {
        int[] array_a_1 = {1, 2, 3, 4};
        int[] array_b_1 = {1, 4, 3, 2};
        boolean expected_1 = true;
        boolean output_1 = areTheyEqual(array_a_1, array_b_1);
        check(expected_1, output_1);

        int[] array_a_2 = {1, 2, 3, 4};
        int[] array_b_2 = {1, 4, 3, 3};
        boolean expected_2 = false;
        boolean output_2 = areTheyEqual(array_a_2, array_b_2);
        check(expected_2, output_2);

        int[] array_a_3 = {1, 2, 3, 4};
        int[] array_b_3 = {1, 4, 5, 3};
        boolean expected_3 = false;
        boolean output_3 = areTheyEqual(array_a_3, array_b_3);
        check(expected_3, output_3);

    }

    public static void main(String[] args) {
        new ReverseToMakeEqual().run();
    }
}
