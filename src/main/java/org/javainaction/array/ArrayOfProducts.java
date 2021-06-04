package org.javainaction.array;

import java.util.Arrays;

/**
 * Write a function where given an integer array
 * the result should contain an array with product of all values except value at i
 * For e.g. {5, 1, 4, 2} should return {8, 40, 10, 20}
 *
 */
public class ArrayOfProducts {
    /**
     * Approach 1 where we calculate product of each elements from i to end of array but time complexity would be O(n^2)
     *
     * Approach 2: we could build a product and divide the product by the number at index to get remaining product
     * Challenge it, it will fail for number with zero with the divide by zero error.
     *
     * Approach 3: Move from left to right calculating running product from left it each element
     * Move from right to left to calculating running product for right to for each element.
     * when we we make product of these two arrays we get the result we are looking for
     *
     */


    public static void main(String[] args) {
        var input = new int[] {5, 1, 4, 2};
        //var expected = new int[] {8, 40, 10, 20};
        System.out.println(Arrays.toString(input) + " array of product is : " + arrayOfProducts(input));
    }

        //O(n) time | O(n) space

    public static int[] arrayOfProducts(int[] array) {
        int[] products = new int[array.length];

        int leftRunnningProduct = 1;
        for (int i = 0; i < array.length; i++) {
            products[i] = leftRunnningProduct;
            leftRunnningProduct *= array[i];
        }

        int rightRunnningProduct = 1;
        for (int i = array.length - 1; i >= 0; i--) {
            products[i] *= rightRunnningProduct;
            rightRunnningProduct *= array[i];
        }
        return products;
    }
}
