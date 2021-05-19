package org.javainaction.dp;

import java.util.Stack;

public class LargestRectHistogram {

    public static void main(String[] args) {
        int[] historgram = new int[]{1, 2, 3, 4, 5, 3, 3, 2};
        int hist[] = { 6, 2, 5, 4, 5, 1, 6 };

        System.out.println("Area : " + maxRectAreaInHistogram(historgram));
        System.out.println("Area : " + maxRectAreaInHistogram(hist));
    }

    public static int maxRectAreaInHistogram(int[] histogram) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int topIdx;
        int maxArea = 0;
        int areaAtTop;
        while ( i < histogram.length) {
            if (stack.isEmpty() || histogram[stack.peek()] <= histogram[i]) {
                stack.push(i++);
            } else {
                topIdx = stack.pop();
                areaAtTop = histogram[topIdx] * (stack.empty() ? i : i - stack.peek() - 1);
                if (maxArea < areaAtTop) maxArea = areaAtTop;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (!stack.isEmpty()) {
            topIdx = stack.pop();
            areaAtTop = histogram[topIdx] * (stack.empty() ? i : i - stack.peek() - 1);
            if (maxArea < areaAtTop) maxArea = areaAtTop;
        }
        return maxArea;
    }
}
