package org.javainaction.array;

import java.util.Arrays;
import java.util.List;

/**
 * Move a certain element to right but preventing order
 * @see MoveZeroToLeft
 */
public class MoveElemToRight {
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int right = 0;
        int left = 0;
        while (right < array.size()) {
            //move all non matching element to left
            //where left represents location to copy element that is not 'toMove'
            if (array.get(right) != toMove) {
                array.set(left, array.get(right));
                left++;
            }
            //write always move ahead irrespective of match
            right++;
        }
        //if we have spaces left, convert all elements between left to end to 'toMove' to mimic that we moved them to end
        while(left < array.size()) array.set(left++, toMove);
        return array;
    }

    public static void main(String[] args) {
        List<Integer> result = MoveElemToRight.moveElementToEnd(Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2 ), 2);
        System.out.println("{ 2, 1, 2, 2, 2, 3, 4, 2 } result after moving zeros to left: " + result);
    }
}
