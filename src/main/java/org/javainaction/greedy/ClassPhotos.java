package org.javainaction.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * In school photo events you are given two ipnut
 * Students wearing red shirt with their height
 * Students wearing blue shirt with their height
 *
 * Rules of photo are
 * 1. All students of same color should seat in same row
 * 2. Students with more height should seat in back row than small height students
 * 3. All inputs are equal half blue and half red shirts
 *
 * Find if you can take such photo
 */
public class ClassPhotos {
    //O(nlog(n)) time | O(1) space
    private boolean classPhotos(ArrayList<Integer> redShirtHeights, ArrayList<Integer> blueShirtHeights) {
        //sort them in reverse order so we know which one to pick first or last
        redShirtHeights.sort(Collections.reverseOrder());
        blueShirtHeights.sort(Collections.reverseOrder());

        //we need to find out which would be taken first, certainly we need to take color whose tallest is smaller
        //than other color, otherwise rule that small student seat in earlier row and ll of them seating in same color
        //will not work
        boolean isRedShirtInFirst = blueShirtHeights.get(0) > redShirtHeights.get(0);
        int i = 0;

        while(i < blueShirtHeights.size() && i < redShirtHeights.size()) {
            //at any given point in time, any student in a same column is conflicting our rule of isRedShirtInFirst
            //meaning when isRedShirtInFirst => !isRedShirtInFirst will violate the rule for other students in the row
            if (isRedShirtInFirst) {
                if (redShirtHeights.get(i) >= blueShirtHeights.get(i)) return false;
            } else {
                if (blueShirtHeights.get(i) >= redShirtHeights.get(i)) return false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        var redShirtHeights = new ArrayList<Integer>(Arrays.asList(5, 8, 1, 3, 4));
        var blueShirtHeights = new ArrayList<Integer>(Arrays.asList(6, 9, 2, 4, 5));
        boolean expected = true;
        boolean actual = new ClassPhotos().classPhotos(redShirtHeights, blueShirtHeights);
        System.out.println(actual);
    }


}
