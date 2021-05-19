package org.javainaction.dp;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * You are given an array of non-negative integers that represents a two-dimensional elevation map where each element
 * is unit-width wall and the integer is the height. Suppose it will rain and all spots between two walls get filled up.
 *
 * Compute how many units of water remain trapped on the map in O(N) time and O(1) space.
 *
 * For example, given the input [2, 1, 2], we can hold 1 unit of water in the middle.
 *
 * Given the input [3, 0, 1, 3, 0, 5], we can hold 3 units in the first index, 2 in the second, and 3 in the fourth
 * index (we cannot hold 5 since it would run off to the left), so we can trap 8 units of water.
 */
public class WaterArea {
    public static void main(String[] args){
        System.out.println(Arrays.toString(new int[]{2, 1, 2})
                + " can store water area : " + waterArea(new int[]{2, 1, 2}));
        System.out.println(Arrays.toString(new int[]{3, 0, 1, 3, 0, 5})
                + " can store water area : " + waterArea(new int[]{3, 0, 1, 3, 0, 5}));

        System.out.println(Arrays.toString(new int[]{2, 1, 2})
                + " can store water area : " + waterAreaSpaceOne(new int[]{2, 1, 2}));
        System.out.println(Arrays.toString(new int[]{3, 0, 1, 3, 0, 5})
                + " can store water area : " + waterAreaSpaceOne(new int[]{3, 0, 1, 3, 0, 5}));
    }

    // O(n) time | O(1) space
    public static int waterAreaSpaceOne(int[] heights) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = heights.length - 1;
        int water = 0;
        while (left < right) {
            if (heights[left] > leftMax) leftMax = heights[left];
            if (heights[right] > rightMax) rightMax = heights[right];
            if (leftMax < rightMax) {
                water += Math.max(0, leftMax - heights[left]);
                left++;
            } else {
                water += Math.max(0, rightMax - heights[right]);
                right--;
            }
        }
        return water;
    }

    // O(n) time | O(n) space
    public static int waterArea(int[] heights) {
        // get all tallest pillar to left at index
        int[] maxes = new int[heights.length];
        int leftMax = 0;
        for(int i = 0; i < heights.length; i++) {
            int height = heights[i];
            maxes[i] = leftMax;
            leftMax = Math.max(leftMax, height);
        }

        // get all tallest pillar to right at index
        int rightMax = 0;
        for(int i = heights.length - 1; i >= 0; i--) {
            int height = heights[i];
            int minHeight = Math.min(maxes[i], rightMax);
            if(height < minHeight) {
                maxes[i] = minHeight - height;
            } else{
                maxes[i] = 0;
            }
            rightMax = Math.max(rightMax, height);
        }

        return IntStream.of(maxes).sum();
    }
}
