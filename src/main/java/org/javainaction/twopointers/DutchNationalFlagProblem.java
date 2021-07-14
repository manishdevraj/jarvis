package org.javainaction.twopointers;

/**
 * Given an array containing 0s, 1s and 2s, sort the array in-place. You should treat numbers of the array as objects,
 * hence, we can’t count 0s, 1s, and 2s to recreate the array.
 *
 * The flag of the Netherlands consists of three colors: red, white and blue; and since our input array also consists
 * of three different numbers that is why it is called Dutch National Flag problem.
 *
 * Example 1:
 *
 * Input: [1, 0, 2, 1, 0]
 * Output: [0 0 1 1 2]
 * Example 2:
 *
 * Input: [2, 2, 0, 1, 2, 0]
 * Output: [0 0 1 2 2 2 ]
 *
 * Given an array of strictly the characters 'R', 'G', and 'B', segregate the values of the array so that all the Rs
 * come first, the Gs come second, and the Bs come last. You can only swap elements of the array.
 *
 * Do this in linear time and in-place.
 *
 * For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become ['R', 'R', 'R', 'G', 'G',
 * 'B', 'B'].
 *
 * @see RearrangeString
 */
public class DutchNationalFlagProblem {
    public static void sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int i = 0;
        while (i <= high) {
            if (arr[i] == 0) {
                //swap all 0's with low pointer
                //as we got swap right we can increment both i and low
                swap(arr, i, low);
                i++;
                low++;
            } else if(arr[i] == 1) {
                //do nothing for 1's as they are in place just move along
                //even case such as [1, 1, 1] will not sorted with 1's in place with O(n) time
                i++;
            } else if (arr[i] == 2) {
                //swap all 2's with igh pointer
                //after swap we might get 0 or 1 so do not move i, just move high pointer
                // [0, 1, 2, 1, 0] with i at 3 and high at 4 we get swap and get [0, 1, 0, 1, 2]
                // no we got 2 moved to right place but at 3 we still got 0 which needs swapping
                swap(arr, i, high);
                high--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 0, 2, 1, 0 };
        sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 2, 2, 0, 1, 2, 0 };
        sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
    }
}
