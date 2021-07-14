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
    // O(n) time | O(n) space
    public static int waterArea(int[] heights) {
        //at any given point in time we can trap water between two tower such that
        //height of tower at i if it is less than either of the left or right tower then
        //whichever is smallest among two, tower at i can hold - maxTower - currentTower height value
        //but to solve it naively we need to try for all values of towers until we know two extreme boundaries

        //idea us to get all tallest pillar to left at index
        //then get difference between left tower max computer and right tower from right to left
        //that becomes min height tower between two, check if current tower is small enough and then store the difference

        int[] maxes = new int[heights.length];
        int leftMax = 0;
        //get all tallest pillar to left at index
        for(int i = 0; i < heights.length; i++) {
            int height = heights[i];
            maxes[i] = leftMax;
            leftMax = Math.max(leftMax, height);
        }

        // get all tallest pillar to right at index
        int rightMax = 0;
        for(int i = heights.length - 1; i >= 0; i--) {
            int height = heights[i];
            //get min among left and right tower
            int minHeight = Math.min(maxes[i], rightMax);
            //if current tower is small to hold water between left and right
            if (height < minHeight) {
                maxes[i] = minHeight - height;
            } else{
                maxes[i] = 0;
            }
            rightMax = Math.max(rightMax, height);
        }

        return IntStream.of(maxes).sum();
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(new int[]{2, 1, 2})
                + " can store water area : " + waterArea(new int[]{2, 1, 2}));
        System.out.println(Arrays.toString(new int[]{3, 0, 1, 3, 0, 5})
                + " can store water area : " + waterArea(new int[]{3, 0, 1, 3, 0, 5}));
        System.out.println(Arrays.toString(new int[]{1, 8,6,2,5,4,8,3,7})
                + " can store water area : " + waterArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
