package org.javainaction.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumberSum {
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        // Write your code here.
        List<Integer[]> threeSomeArray = new ArrayList<>();;
        Arrays.sort(array);
        return threeSome(array, targetSum, threeSomeArray);
        //return threeSomeArray;
        //[ -8, -6, 1, 2, 3, 5, 6, 12]
    }

    public static List<Integer[]> threeSome(int[] array, int targetSum,
                                            List<Integer[]> threeSomeArray) {
        for(int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;
            while(left < right) {
                int threeSum = array[i] + array[left] + array[right];
                if(targetSum == threeSum) {
                    Integer[] tripletArr = { array[i], array[left], array[right]};
                    threeSomeArray.add(tripletArr);
                    left++;
                    right--;
                } else if(threeSum < targetSum) {
                    left++;
                } else if(threeSum > targetSum) {
                    right--;
                }
            }
        }
        return threeSomeArray;
    }
}
