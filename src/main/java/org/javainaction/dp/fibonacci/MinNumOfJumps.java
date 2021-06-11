package org.javainaction.dp.fibonacci;

/**
 * Given an integer array find the minimum number of jumps needed to reach at the end of array
 * {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}
 *
 * Output would be 4
 *
 * 3 --> (4 or 2 ) --> (2 or 3) --> 7 --> 3
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */
public class MinNumOfJumps {
    // O(n) time | O(1) space
    public static int minNumberOfJumps(int[] array) {
        if(array.length == 1 ) return 0;
        int jumps = 0;
        int maxReach = array[0];
        int steps = array[0];
        for(int i = 1; i < array.length - 1; i++) {
            maxReach = Math.max(maxReach, array[i] + i);
            steps--;
            if (steps == 0) {
                jumps++;
                steps = maxReach - i;
            }
        }
        return jumps + 1;
    }
    // O(n ^ 2) time | O(n) space
    public static int minNumberOfJumpsDP(int[] array) {
        if (array.length == 0) return -1;
        int[] jumps = new int[array.length];
        int max = Integer.MAX_VALUE;
        jumps[0] = 0;
        for(int index = 1; index < array.length; index++) {
            jumps[index] = max;
            for(int j = 0; j < index ; j++) {
                if (array[j] >= index - j) {
                    jumps[index] = Math.min(jumps[index], jumps[j] + 1);
                    j++;
                }
            }
        }
        return jumps[jumps.length - 1];
    }

    /**
     * Easy to understand solution
     *
     * @param jumps
     * @return
     */
    // O(n ^ 2) time | O(n) space
    public static int countMinJumps(int[] jumps) {
        int[] dp = new int[jumps.length];

        //initialize with infinity, except the first index which should be zero as we start from there
        for (int i = 1; i < jumps.length; i++)
            dp[i] = Integer.MAX_VALUE;

        for (int start = 0; start < jumps.length - 1; start++) {
            for(int end = start + 1; end <= start + jumps[start] && end < jumps.length; end++)
                dp[end] = Math.min(dp[end], dp[start] + 1);
        }

        return dp[jumps.length-1];
    }

    public static void main(String[] args) {
        System.out.println(minNumberOfJumps(new int[]{3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}));
        System.out.println(minNumberOfJumpsDP(new int[]{3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}));
        System.out.println(countMinJumps(new int[]{3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}));
    }
}
