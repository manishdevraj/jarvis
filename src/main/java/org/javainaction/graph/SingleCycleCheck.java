package org.javainaction.graph;


/**
 * Given an array with value represents a jump in array.
 * 2 represents jump forward by 2 positions
 * -3 represents jump backward by -3 positions
 * If jump spills past bounds then it needs to wrap to other side. -1 at 0 index sends it to end of array
 * and 1 at end of array sends it to index 0
 *
 * Find if single cycle exist in array, cycle occurs if it starts at any index and returns back to same index after
 * visiting all indices.
 */
public class SingleCycleCheck {
    // O(n) time | O(1) space
    public static boolean hasSingleCycle(int[] array) {
        int numElementsVisited = 0;
        int currentIdx = 0;
        while(numElementsVisited < array.length) {
            //if we reached at beginning before reaching end means it is not a single cycle
            if (numElementsVisited > 0 && currentIdx == 0) return false;
            numElementsVisited++;
            int jump = array[currentIdx];
            int nextIdx = (currentIdx + jump) % array.length;
            currentIdx = nextIdx >= 0 ? nextIdx : nextIdx + array.length;
        }
        //if we reached to beginning after traversing all elements that means we have single cycle
        return currentIdx == 0;
    }

    public static void main(String[] args) {
        System.out.println("{2, 3, 1, -4, -4, 2} has cycle " + hasSingleCycle(new int[]{ 2, 3, 1, -4, -4, 2 }));
        System.out.println("{2, 2, -1} has single cycle " + hasSingleCycle(new int[] { 2, 2, -1}));
        System.out.println("{ 2, 2, 2} has single cycle " + hasSingleCycle(new int[] { 2, 2, 2 }));
        System.out.println("{ -1, 2, 2 } has single cycle " + hasSingleCycle(new int[] { -1, 2, 2 }));
        System.out.println("{0, 1, 1, 1, 1} has single cycle : " + hasSingleCycle(new int[] { 0, 1, 1, 1, 1 }));
        System.out.println("{1, 1, 0, 1, 1} has single cycle : " + hasSingleCycle(new int[] {1, 1, 0, 1, 1}));
        System.out.println("{1, 1, 1, 1, 2} has single cycle : " + hasSingleCycle(new int[] {1, 1, 1, 1, 2}));

    }
}
