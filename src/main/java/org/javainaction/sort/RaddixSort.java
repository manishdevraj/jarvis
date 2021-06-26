package org.javainaction.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implement Raddix sort
 *
 * Idea is that if we know some key information about our input array such as maximum number then we can improve sort
 * time complexity better than O(nlog(n))
 *
 * We perform counting sort meaning identify occurances of number to the base 10 then to 100 and then to 1000
 * While we are performing counting sort we make sure we do running some so that have space for numbers whose base
 * count is colliding with each other say 62 and 12 for base 10 as 2.
 *
 * We do this sort until all base are computed with counting sort
 *
 */
public class RaddixSort {
    //O(d * (n + b)) time | O(n + b) space where n is length and d is max number of
    //digits, and b is base of numbering systems
    public List<Integer> radixSort(List<Integer> array) {
        if (array == null || array.size() == 0) return array;

        int max = Collections.max(array);
        int digit = 0;
        while ((max / Math.pow(10, digit)) > 0) {
            countingSort(array, digit);
            digit++;
        }
        return array;
    }

    public void countingSort(List<Integer> array, int digit) {
        int[] sortedArray = new int[array.size()];
        int[] countArray = new int[10];
        int digitCol = (int) Math.pow(10, digit);
        //count array for each elements base
        for (int num : array) {
            int countIndex = (num / digitCol) % 10;
            countArray[countIndex]++;
        }

        //do running sum
        for (int i = 1; i < 10; i++) {
            //this ensures we have space for elements match with same digitcol
            countArray[i] += countArray[i - 1];
        }

        //place each element from right to left at position defined by count array
        //if there are move than one element keep track of total elements by removing occurence
        for (int i = array.size() - 1; i >= 0; i--) {
            int countIndex = (array.get(i) / digitCol) % 10;
            countArray[countIndex]--;
            int sortedIndex = countArray[countIndex];
            sortedArray[sortedIndex] = array.get(i);
        }

        for (int i = 0; i < array.size(); i++) {
            array.set(i, sortedArray[i]);
        }
    }

    public static void main(String[] arg) {
        var input =
                new ArrayList<Integer>(Arrays.asList(8762, 654, 3008, 345, 87, 65, 234, 12, 2));
        var expected =
                new ArrayList<Integer>(Arrays.asList(2, 12, 65, 87, 234, 345, 654, 3008, 8762));
        var actual = new RaddixSort().radixSort(input);
        System.out.println(actual);
    }
}
