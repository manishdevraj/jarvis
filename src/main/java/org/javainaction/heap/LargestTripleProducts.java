package org.javainaction.heap;

import java.util.PriorityQueue;

/**
 * You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that, for each index i (between 0 and n-1, inclusive), output[i] is equal to the product of the three largest elements out of arr[0..i] (or equal to -1 if i < 2, as arr[0..i] then includes fewer than three elements).
 * Note that the three largest elements used to form any product may have the same values as one another, but they must be at different indices in arr.
 * Signature
 * int[] findMaxProduct(int[] arr)
 * Input
 * n is in the range [1, 100,000].
 * Each value arr[i] is in the range [1, 1,000].
 * Output
 * Return a list of n integers output[0..(n-1)], as described above.
 * Example 1
 * n = 5
 * arr = [1, 2, 3, 4, 5]
 * output = [-1, -1, 6, 24, 60]
 * The 3rd element of output is 3*2*1 = 6, the 4th is 4*3*2 = 24, and the 5th is 5*4*3 = 60.
 * Example 2
 * n = 5
 * arr = [2, 1, 2, 1, 2]
 * output = [-1, -1, 4, 4, 8]
 * The 3rd element of output is 2*2*1 = 4, the 4th is 2*2*1 = 4, and the 5th is 2*2*2 = 8.
 */
public class LargestTripleProducts {
    int[] findMaxProduct(int[] arr) {
        if (arr == null || arr.length < 3) return new int[]{};
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] result = new int[arr.length];
        int lastMinimum = Integer.MIN_VALUE;
        int product = 1;
        for (int i = 0; i < arr.length; i++) {
            if (minHeap.size() == 2) {
                if (arr[i] > lastMinimum) {
                    lastMinimum = minHeap.poll();
                    product = arr[i] * lastMinimum * minHeap.peek();
                    minHeap.offer(arr[i]);
                }
                result[i] = product;
            } else {
                result[i] = product * -1;
                minHeap.offer(arr[i]);
            }
          }
        return result;
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for(int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int[] arr_1 = {1, 2, 3, 4, 5};
        int[] expected_1 = {-1, -1, 6, 24, 60};
        int[] output_1 = findMaxProduct(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {2, 4, 7, 1, 5, 3};
        int[] expected_2 = {-1, -1, 56, 56, 140, 140};
        int[] output_2 = findMaxProduct(arr_2);
        check(expected_2, output_2);
    }

    public static void main(String[] args) {
        new LargestTripleProducts().run();
    }
}
