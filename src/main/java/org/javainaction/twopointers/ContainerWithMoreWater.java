package org.javainaction.twopointers;

import java.util.Arrays;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines,
 * which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * Example 3:
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 * Example 4:
 *
 * Input: height = [1,2,1]
 * Output: 2
 */
public class ContainerWithMoreWater {

    /**
     * Variables left and right define the container under consideration. We initialize them to first and last line,
     * meaning the widest container. Variable water will keep track of the highest amount of water we managed so far.
     * We compute right - left, the width of the current container,
     * and min(height[left], height[right]), the water level that this container can support.
     * Multiply them to get how much water this container can hold, and update water accordingly.
     * Next remove the smaller one of the two lines from consideration.
     * Continue until there is nothing left to consider, then return the result.
     */

    public static void main(String[] args){
        System.out.println(Arrays.toString(new int[]{1,8,6,2,5,4,8,3,7})
                + " can store water area : " + maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(Arrays.toString(new int[]{1, 1})
                + " can store water area : " + maxArea(new int[]{1, 1}));

        System.out.println(Arrays.toString(new int[]{4,3,2,1,4})
                + " can store water area : " + maxArea(new int[]{4,3,2,1,4}));
        System.out.println(Arrays.toString(new int[]{1, 2, 1})
                + " can store water area : " + maxArea(new int[]{1, 2, 1}));
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int waterArea = 0;
        while (left < right) {
            //the width of the current container
            int widhtOfContainer = right - left;
            //the water level that this container can support
            int waterLevel = Math.min(height[left], height[right]);
            waterArea = Math.max(waterArea, widhtOfContainer * waterLevel);

            if (height[left] == height[right]) {
                left++;
                right --;
            } else if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return waterArea;
    }
}
