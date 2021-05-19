package org.javainaction.dp.fibonacci;

public class MinNumOfJumps {

    public static void main(String[] args) {
        System.out.println(minNumberOfJumps(new int[]{3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}));
        System.out.println(minNumberOfJumpsDP(new int[]{3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}));
        System.out.println(countMinJumps(new int[]{3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}));
    }

    public static int minNumberOfJumps(int[] array) {
        // O(n) time | O(1) space
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

    public static int minNumberOfJumpsDP(int[] array) {
        // O(n ^ 2) time | O(n) space
        int[] jumps = new int[array.length];
        int max = Integer.MAX_VALUE;
        if (array.length < 0) return -1;

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
     * @param jumps
     * @return
     */
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
}
