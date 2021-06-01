package org.javainaction.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given non empty list of non empty sorted array of integers, return a merged list of all those arrays.
 *
 * {
 * {1, 5, 9, 21},
 * {-1, 0},
 * {-124, 81, 121},
 * {3, 6, 12, 20, 150}
 * }
 *
 *
 *    OUTPUT {-124, -1, 0, 1, 3, 5, 6, 9, 12, 20, 21, 81, 121, 150}
 */
public class MergeSortedArrays {
    // O(nlog(k) + k) time | O(n + k) space where n is the total number of
    // array elements and k is the number of arrays
    public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
        List<Integer> sortedList = new ArrayList<>();
        PriorityQueue<Element> priorityQueue = new PriorityQueue<>((a, b) -> a.number - b.number);

        for(int arrayIndex = 0; arrayIndex < arrays.size(); arrayIndex++) {
            priorityQueue.offer(new Element(arrayIndex, 0, arrays.get(arrayIndex).get(0)));
        }

        while (!priorityQueue.isEmpty()) {
            Element small = priorityQueue.poll();
            sortedList.add(small.number);
            if (small.elementIndex == arrays.get(small.arrayIndex).size() - 1) {
                continue;
            }
            priorityQueue.offer(new Element(small.arrayIndex, small.elementIndex + 1,
                    arrays.get(small.arrayIndex).get(small.elementIndex  + 1)));
        }

        return sortedList;
    }

    static class Element {
        int arrayIndex;
        int elementIndex;
        int number;

        public Element(int arrayIndex, int elementIndex, int number) {
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
            this.number = number;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> arrays = new ArrayList<>();
        arrays.add(Arrays.asList(1, 5, 9, 21));
        arrays.add(Arrays.asList(-1, 0));
        arrays.add(Arrays.asList(-124, 81, 121));
        arrays.add(Arrays.asList(3, 6, 12, 20, 150));
        var actual = mergeSortedArrays(arrays);
        /*var expected =
                Arrays.asList(new Integer[] {-124, -1, 0, 1, 3, 5, 6, 9, 12, 20, 21, 81, 121, 150});*/
        System.out.println("Merged list : " + actual);

    }
}
