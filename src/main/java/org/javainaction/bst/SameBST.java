package org.javainaction.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given two arrays find if they would make same BST when constructed.
 *
 * Trick is we are not suppose to create BST
 *
 *
 */
public class SameBST {
    private static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
        if (arrayOne.size() != arrayTwo.size()) return false;
        if (arrayOne.size() == 0 && arrayTwo.size() == 0 ) return true;
        if (arrayOne.get(0).intValue() != arrayTwo.get(0).intValue()) return false;

        List<Integer> arrayOneSmall = getSmallElements(arrayOne);
        List<Integer> arrayTwoSmall = getSmallElements(arrayTwo);
        List<Integer> arrayOneGtEqual = getGtEqualElements(arrayOne);
        List<Integer> arrayTwoGtEqual = getGtEqualElements(arrayTwo);

        return sameBsts(arrayOneSmall, arrayTwoSmall) && sameBsts(arrayOneGtEqual, arrayTwoGtEqual);
    }

    private static List<Integer> getGtEqualElements(List<Integer> array) {
        return array.stream()
                .skip(1)
                .filter(a -> a >= array.get(0)).collect(Collectors.toList());
    }

    private static List<Integer> getSmallElements(List<Integer> array) {
        return array.stream()
                .skip(1)
                .filter(a -> a < array.get(0)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> arrayOne = new ArrayList<>(Arrays.asList(10, 15, 8, 12, 94, 81, 5, 2, 11));
        List<Integer> arrayTwo = new ArrayList<>(Arrays.asList(10, 8, 5, 15, 2, 12, 11, 94, 81));
        System.out.println(sameBsts(arrayOne, arrayTwo));
    }
}
