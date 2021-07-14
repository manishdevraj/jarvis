package org.javainaction.dp;

import java.util.Stack;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 *
 *
 * Input: heights = [2,4]
 * Output: 4
 */
public class LargestRectHistogram {
    public static int maxRectAreaInHistogram(int[] histogram) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int topIdx;
        int maxArea = 0;
        int areaAtTop;

        /**
         * For any bar i the maximum rectangle is of width r - l - 1 where r - is the last coordinate of the bar to
         * the right with height h[r] >= h[i] and l - is the last coordinate of the bar to the left which height
         * h[l] >= h[i]
         *
         * So if for any i coordinate we know his utmost higher (or of the same height) neighbors to the right and
         * to the left, we can easily find the largest rectangle:
         */
        while ( i < histogram.length) {
            //until all we have rectangle of same of bigger height than current keep them adding
            //notice i, we have been increasing right after so we know  width from right to left
            if (stack.isEmpty() || histogram[stack.peek()] <= histogram[i]) {
                stack.push(i++);
            } else {
                //pop element
                topIdx = stack.pop();
                //width will be index on right - current rectangle index - 1
                //[2, 1, ....] we haven i = 1 since distance between 2 and 1 is 1 , rectangle height becomes area
                // area = 2 * (1 - 0 - 1) = 2
                //[...5, 6, 2, 3 ....] we have i = 4 at first we get area as 6 * (4 - 2 - 1) = 6
                //then when we know we have more rectangles that are bigger on left than right
                //area becomes for next element 5, area = 5 * (4 - 1 - 1) = 10
                areaAtTop = histogram[topIdx] * (stack.empty() ? i : i - stack.peek() - 1);
                if (maxArea < areaAtTop)
                    maxArea = areaAtTop;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        //it is possible we didn't finish all bars so do same until stack is same, i is always going to be pointing at
        // end
        while (!stack.isEmpty()) {
            topIdx = stack.pop();
            areaAtTop = histogram[topIdx] * (stack.empty() ? i : i - stack.peek() - 1);
            if (maxArea < areaAtTop) maxArea = areaAtTop;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("[2,1,5,6,2,3] area : " + maxRectAreaInHistogram(new int[]{2,1,5,6,2,3}));
        System.out.println("[1, 2, 3, 4, 5, 3, 3, 2] area : " + maxRectAreaInHistogram(new int[]{1, 2, 3, 4, 5, 3, 3, 2}));
        System.out.println("[6, 2, 5, 4, 5, 1, 6] area : " + maxRectAreaInHistogram(new int[]{6, 2, 5, 4, 5, 1, 6}));
        System.out.println("[2, 4] area : " + maxRectAreaInHistogram(new int[]{2, 4}));

    }
}
