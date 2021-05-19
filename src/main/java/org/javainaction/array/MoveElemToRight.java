package org.javainaction.array;

import java.util.Arrays;
import java.util.List;

/**
 * Move a certain element to right but preventing order
 *
 */
public class MoveElemToRight {
    public static void main(String[] args) {
        List<Integer> result = MoveElemToRight.moveElementToEnd(Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2 ), 2);
        System.out.println("{ 2, 1, 2, 2, 2, 3, 4, 2 } result after moving zeros to left: " + result);
    }

    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int left = 0;
        int right = 0;
        while (left < array.size()) {
            if (array.get(left) != toMove) {
                array.set(right, array.get(left));
                right++;
            }
            left++;
        }
        while(right < array.size()) array.set(right++, toMove);
        return array;
    }
}
