package org.javainaction.sort;

import java.util.Arrays;

/**
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
 *
 * You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).
 *
 * Return the number of indices where heights[i] != expected[i].
 *
 *
 *
 * Example 1:
 *
 * Input: heights = [1,1,4,2,1,3]
 * Output: 3
 * Explanation:
 * heights:  [1,1,4,2,1,3]
 * expected: [1,1,1,2,3,4]
 * Indices 2, 4, and 5 do not match.
 * Example 2:
 *
 * Input: heights = [5,1,2,3,4]
 * Output: 5
 * Explanation:
 * heights:  [5,1,2,3,4]
 * expected: [1,2,3,4,5]
 * All indices do not match.
 * Example 3:
 *
 * Input: heights = [1,2,3,4,5]
 * Output: 0
 * Explanation:
 * heights:  [1,2,3,4,5]
 * expected: [1,2,3,4,5]
 * All indices match.
 */
public class HeightChecker {
    public static int heightChecker(int[] heights) {
        int[] count = new int[101];

        for(int h : heights) {
            count[h]++;
        }

        //running sum of all numbers before current index which tells us
        //how many number say before 5 index are available
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }

        int result = 0;
        for (int height : heights) {
            //take running sum count before current number to know smallest before x
            if (height != heights[count[height] - 1]) result++;
            count[height]--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Height checker "+ heightChecker(new int[]{1,1,4,2,1,3}));
    }
}
