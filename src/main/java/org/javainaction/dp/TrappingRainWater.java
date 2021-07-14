package org.javainaction.dp;

import java.util.Arrays;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
 * water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * @see WaterArea
 */
public class TrappingRainWater {
    /**
     * To do so, we have to realize that, if we just look at the two extremities of the input array, the smaller of
     * the two values gives us information about water to be trapped in the middle. For example, consider the
     * following simple array:
     *
     * heights = [4, 0, 6, 0, 10]
     * Since the leftmost value 4 is smaller than the rightmost value  10, we know that, assuming all values in
     * between are smaller than this leftmost value, we'll trap water equal to the difference between  4 and those
     * values.
     *
     * Now of course, middle values won't always be smaller than the leftmost value, like the middlemost value in
     * the array above which is 6. In those cases, we update the leftmost value, making the leftmost value effectively
     * contain the greatest most recently visited value to the left.
     *
     * Broadly speaking, the algorithm works by setting up two pointers—a left and right pointer—at the extremities of
     * the input array and progressively pushing the one that points to the smallest value inward. At each value in
     * between the pointers, we update the relevant left-or-right max value (this depends on which pointer we moved
     * inward), and we add to our final surface area the difference between the relevant left-or-right max value and
     * the current value. We repeat this until the left and right pointers pass each other.
     *
     * Since we only need to store five values, this algorithm only requires constant space.
     */

    // O(n) time | O(1) space
    public static int waterAreaSpaceOne(int[] heights) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = heights.length - 1;
        int water = 0;
        while (left < right) {
            //because only left pillar with max value can trap water at current index
            if (heights[left] > leftMax) leftMax = heights[left];
            //because only right pillar with max value can trap water at current index
            if (heights[right] > rightMax) rightMax = heights[right];
            //as their is water slippage from higher pillar we only need to consider smallest of two sides
            if (leftMax < rightMax) {
                //if current pillar value is bigger then two ends max won't matter
                //or else negate the current pillar from two sides smaller max
                water += Math.max(0, leftMax - heights[left]);
                left++;
            } else {
                //if current pillar value is bigger then two ends max won't matter
                //or else negate the current pillar from two sides smaller max
                water += Math.max(0, rightMax - heights[right]);
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(new int[]{2, 1, 2})
                + " can store water area : " + waterAreaSpaceOne(new int[]{2, 1, 2}));
        System.out.println(Arrays.toString(new int[]{3, 0, 1, 3, 0, 5})
                + " can store water area : " + waterAreaSpaceOne(new int[]{3, 0, 1, 3, 0, 5}));
        System.out.println(Arrays.toString(new int[]{1, 8,6,2,5,4,8,3,7})
                + " can store water area : " + waterAreaSpaceOne(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
