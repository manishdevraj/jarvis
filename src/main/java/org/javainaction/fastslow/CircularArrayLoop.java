package org.javainaction.fastslow;

/**
 * You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of
 * indices forward/backward you must move if you are located at index i:
 *
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move nums[i] steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you on the first element,
 * and moving backwards from the first element puts you on the last element.
 *
 * A cycle in the array consists of a sequence of indices seq of length k where:
 *
 * Following the movement rules above results in the repeating index sequence
 * seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * Every nums[seq[j]] is either all positive or all negative. and k > 1
 *
 * Return true if there is a cycle in nums, or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [2,-1,1,2,2]
 * Output: true
 * Explanation:
 * There is a cycle from index 0 -> 2 -> 3 -> 0 -> ...
 * The cycle's length is 3.
 * Example 2:
 *
 * Input: nums = [-1,2]
 * Output: false
 * Explanation:
 * The sequence from index 1 -> 1 -> 1 -> ... is not a cycle because the sequence's length is 1.
 * By definition the sequence's length must be strictly greater than 1 to be a cycle.
 * Example 3:
 *
 * Input: nums = [-2,1,-1,-2,-2]
 * Output: false
 * Explanation:
 * The sequence from index 1 -> 2 -> 1 -> ... is not a cycle because nums[1] is positive, but nums[2] is negative.
 * Every nums[seq[j]] must be either all positive or all negative.
 *
 * Example 1:
 *
 * Input: [1, 2, -1, 2, 2]
 * Output: true
 * Explanation: The array has a cycle among indices: 0 -> 1 -> 3 -> 0
 * Example 2:
 *
 * Input: [2, 2, -1, 2]
 * Output: true
 * Explanation: The array has a cycle among indices: 1 -> 3 -> 1
 * Example 3:
 *
 * Input: [2, 1, -1, -2]
 * Output: false
 * Explanation: The array does not have any cycle.
 */
public class CircularArrayLoop {
    public static boolean loopExists(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isForward = arr[i] >= 0; // if we are moving forward or not
            int slow = i, fast = i;
            int seqLength = 0;
            // if slow or fast becomes '-1' this means we can't find cycle for this number
            do {
                slow = findNextIndex(arr, isForward, slow); // move one step for slow pointer
                fast = findNextIndex(arr, isForward, fast); // move one step for fast pointer
                if (fast != -1)
                    fast = findNextIndex(arr, isForward, fast); // move another step for fast pointer
                seqLength++;
            } while (slow != -1 && fast != -1 && slow != fast);

            if (slow != -1 && slow == fast && seqLength > 1)
                return true;
        }

        return false;
    }

    private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        boolean direction = arr[currentIndex] >= 0;
        if (isForward != direction)
            return -1; // change in direction, return -1

        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        if (nextIndex < 0) nextIndex += arr.length; // wrap around for negative numbers

        // one element cycle, return -1
        if (nextIndex == currentIndex) nextIndex = -1;

        return nextIndex;
    }

    public static void main(String[] args) {

        System.out.println("{2,-1,1,2,2} has cycle " + loopExists(new int[] { 2,-1,1,2,2 }));
        System.out.println("{ 1, 2, -1, 2, 2 } has cycle " + loopExists(new int[] { 1, 2, -1, 2, 2 }));
        System.out.println("{ 2, 2, -1, 2 } has cycle " + loopExists(new int[] { 2, 2, -1, 2 }));
        System.out.println("{ 2, 1, -1, -2 } has cycle " + loopExists(new int[] { 2, 1, -1, -2 }));
        System.out.println("{2,-1, 1, 2, 2} has cycle : " + loopExists(new int[] { 2,-1,1,2,2 }));
        System.out.println("{-1, 2} has cycle : " + loopExists(new int[] { -1, 2 }));
        System.out.println("{-2, 1,-1,-2,-2} has cycle : " + loopExists(new int[] { -2,1,-1,-2,-2}));
    }
}
