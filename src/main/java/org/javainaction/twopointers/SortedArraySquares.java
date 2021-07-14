package org.javainaction.twopointers;

/**
 * Given a sorted array, create a new array containing squares of all the number of the input array in the
 * sorted order.
 * Example 1:
 *
 * Input: [-2, -1, 0, 2, 3]
 * Output: [0, 1, 4, 4, 9]
 * Example 2:
 *
 * Input: [-3, -1, 0, 1, 2]
 * Output: [0 1 1 4 9]
 * @see SortedSquaredArray
 */

public class SortedArraySquares {
    public static int[] makeSquares(int[] arr) {

        int[] squares = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;
        int highestSquareIdx = arr.length - 1; //start from last so that we have array sorted

        while (left < right) {
            //we need not worry about abs as -a x -a will result in positive squares
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            if (leftSquare > rightSquare) {
                squares[highestSquareIdx--] = leftSquare;
                left++;
            } else {
                //when right elements are greater or equal
                squares[highestSquareIdx--] = rightSquare;
                right--;
            }
        }
        return squares;
    }

    public static void main(String[] args) {

        int[] result = SortedArraySquares.makeSquares(new int[] { -2, -1, 0, 2, 3 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = SortedArraySquares.makeSquares(new int[] { -3, -1, 0, 1, 2 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }
}
