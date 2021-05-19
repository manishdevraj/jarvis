package org.javainaction.graph;

import java.util.Arrays;

public class SingleCycleCheck {
    public static void main(String[] args) {
        System.out.println(hasSingleCycle(new int[]{ 2, 3, 1, -4, -4, 2 }));
    }
    // O(n) time | O(1) space
    public static boolean hasSingleCycle(int[] array) {
        int numElementsVisited = 0;
        int currentIdx = 0;
        while(numElementsVisited < array.length) {
            if (numElementsVisited > 0 && currentIdx == 0) return false;
            numElementsVisited++;
            int jump = array[currentIdx];
            int nextIdx = (currentIdx + jump) % array.length;
            currentIdx = nextIdx >= 0 ? nextIdx : nextIdx + array.length;
        }
        return currentIdx == 0;
    }
}
