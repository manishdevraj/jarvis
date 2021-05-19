package org.javainaction.array;

import java.util.HashMap;
import java.util.Map;

public class LargestRange {
    // O(n) time | O(n) space
    public static int[] largestRange(int[] array) {
        int[] bestRange = new int[2];
        int longest = 0;
        Map<Integer, Boolean> rangeMap = new HashMap<>();
        int min = array[0];
        for (int num : array) {
            rangeMap.put(num, true);
        }

        for (int num : array) {
            if (!rangeMap.get(num)) {
                continue;
            }
            rangeMap.put(num, false);
            int current = 1;
            int left = num - 1;
            int right = num + 1;
            while (rangeMap.containsKey(left)) {
                rangeMap.put(left, false);
                left--;
                current++;
            }
            while (rangeMap.containsKey(right)) {
                rangeMap.put(right, false);
                right++;
                current++;
            }

            if (current > longest) {
                longest = current;
                bestRange = new int[] {left + 1, right - 1};
            }
        }
        return bestRange;
    }
}
