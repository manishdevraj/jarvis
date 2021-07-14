package org.javainaction.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class DiskStacking {
    //O(n^2) time | O(n) space
    public static List<Integer[]> diskStacking(List<Integer[]> disks) {
        disks.sort((disk1, disk2) -> disk1[2].compareTo(disk2[2]));
        int[] heights = new int[disks.size()];
        for (int i = 0; i < disks.size(); i++) {
            heights[i] = disks.get(i)[2];
        }

        int[] sequences = new int[disks.size()];
        Arrays.fill(sequences, Integer.MIN_VALUE);
        int maxHeightIdx = 0;
        for (int i = 1; i < disks.size(); i++) {
            Integer[] currentDisk = disks.get(i);
            for (int j = 0; j < i; j++) {
                Integer[] prevDisk = disks.get(j);
                if (areValidDimensions.apply(prevDisk, currentDisk)) {
                    if (heights[i] <= currentDisk[2] + heights[j]) {
                        heights[i] = currentDisk[2] + heights[j];
                        sequences[i] = j;
                    }
                }
            }
            if (heights[i] >= heights[maxHeightIdx]) {
                maxHeightIdx = i;
            }
        }
        return buildSequence(disks, sequences, maxHeightIdx);
    }

    public static List<Integer[]> buildSequence(List<Integer[]> disks,
                                                int[] sequences,
                                                int currentIdx) {
        List<Integer[]> listSequences = new ArrayList<>();
        while (currentIdx != Integer.MIN_VALUE) {
            listSequences.add(0, disks.get(currentIdx));
            currentIdx = sequences[currentIdx];
        }
        return listSequences;
    }

    public static BiFunction<Integer[], Integer[], Boolean> areValidDimensions =
            ((a, b) -> a[0] < b[0] && a[1] < b[1] && a[2] < b[2]);

    public static void main(String[] args) {
        List<Integer[]> input = new ArrayList<Integer[]>();
        input.add(new Integer[] {2, 1, 2});
        input.add(new Integer[] {3, 2, 3});
        input.add(new Integer[] {2, 2, 8});
        input.add(new Integer[] {2, 3, 4});
        input.add(new Integer[] {2, 2, 1});
        input.add(new Integer[] {4, 4, 5});
        List<Integer[]> expected = new ArrayList<Integer[]>();
        expected.add(new Integer[] {2, 1, 2});
        expected.add(new Integer[] {3, 2, 3});
        expected.add(new Integer[] {4, 4, 5});
        System.out.println(compare(diskStacking(input), expected));
    }

    private static boolean compare(List<Integer[]> arr1, List<Integer[]> arr2) {
        if (arr1.size() != arr2.size()) {
            return false;
        }
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr1.get(i).length; j++) {
                if (!arr1.get(i)[j].equals(arr2.get(i)[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
