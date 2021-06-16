package org.javainaction.recursion;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 */
public class JumpGame3 {
    public static boolean canReach(int[] arr, int start) {
        //last check is make sure visited elements are not visited again
        //if we are sure the elements are non negative then we can also mark it as
        // -1 and have a check arr[start] > 0
        if (start >= 0 && start < arr.length && arr[start] < arr.length) {
            int jump = arr[start];
            arr[start] += arr.length;
            return jump == 0 //we reached at 0 or
                    || canReach(arr, start - jump) // jump backwards
                    || canReach(arr, start + jump); //jump forward
        }

        // if never reached value 0 while traversing all jumps
        return false;
    }

    public static void main(String[] args) {
        System.out.println("{4,2,3,0,3,1,2} can reach at 0 from 5? : " + canReach(new int[]{4,2,3,0,3,1,2}, 5));
        System.out.println("{3,0,2,1,2} can reach at 0 from 2? : " + canReach(new int[]{3,0,2,1,2}, 2));
    }
}
