package org.javainaction.greedy;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 * @see org.javainaction.recursion.JumpGame3
 * @see org.javainaction.dp.JumpGame4
 */
public class JumpGame {

    public boolean canJump(int[] array) {
        int n = array.length, farthest = 0;
        //we iterate step by step looking at max we can reach at
        //if at any point in time we have an element that does not take us anywhere like 0
        //our farthest will be less than current index and we can safely say we cannot jump at the end
        for (int i = 0; i < n; i++) {
            // if previous farthest is smaller than i,
            // meaning we cannot reach location i, thus return false.
            if (farthest < i) return false;
            farthest = Math.max(i + array[i], farthest);
        }
        //because we did not have any hurdles' means we reached at end
        return true;
    }

    public static void main(String[] args) {
        var jumpGame = new JumpGame();
        System.out.println("{2,3,1,1,4} can jump to end " + jumpGame.canJump(new int[]{2,3,1,1,4}) );
        System.out.println("{3,2,1,0,4} can jump to end " + jumpGame.canJump(new int[]{3,2,1,0,4}) );
    }
}

