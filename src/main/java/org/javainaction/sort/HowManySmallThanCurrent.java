package org.javainaction.sort;

import java.util.Arrays;

/**
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it.
 * That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 *
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 *
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 *
 */
public class HowManySmallThanCurrent {
    public static int[] smallerNumbersThanCurrent(int[] arr) {
        int[] count = new int[101];
        int[] result = new int[arr.length];

        for(int num : arr) {
            count[num]++;
        }

        //running sum of all numbers before current index which tells us
        //how many number say before 5 index are available
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < arr.length; i++) {
            //we do not have any number less than current number
            if (arr[i] == 0) result[i] = 0;
            //take running sum count before current number to know smallest before x
            else result[i] = count[arr[i] - 1];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("smaller at each current number "
                + Arrays.toString(smallerNumbersThanCurrent(new int[]{8,1,2,2,3})));
    }
}
