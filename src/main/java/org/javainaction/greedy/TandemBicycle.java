package org.javainaction.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Tandem bicycle is one with two riders. The maximum speed of such bike is based on maximum speed of either of the rider
 * We are given riders in blue shirts with their max speed and riders in red shirts with their max speeds.
 *
 * We are required to compute maximum speed of tandem bicycle if we need to combine each (red | blue) riders together
 * from the list. If flag is passes to find fastest then give maximum total speed if flag is false then return min
 * total speed.
 *
 * Input : red shirt speeds {5, 5, 3, 9, 2}
 * Blue shirt speeds {3, 6, 7, 2, 1}
 *
 * Output 32
 *
 *
 *
 */
public class TandemBicycle {
    //O(nlog(n)) time | O(1) space
    public int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
        Arrays.sort(blueShirtSpeeds);
        Arrays.sort(redShirtSpeeds);

        if (!fastest) {
            reverseInPlace(redShirtSpeeds);
        }

        int left = 0 ;
        int right = blueShirtSpeeds.length - 1;
        int totalSpeed = 0;
        while (left < redShirtSpeeds.length && right >= 0) {
            totalSpeed += Math.max(redShirtSpeeds[left++], blueShirtSpeeds[right--]);
        }

        return totalSpeed;
    }

    private void reverseInPlace(int[] speedArray) {
        int left = 0;
        int right = speedArray.length - 1;
        while (left < right) {
            int temp = speedArray[left];
            speedArray[left] = speedArray[right];
            speedArray[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        var actual = new TandemBicycle().tandemBicycle(new int[] {5, 5, 3, 9, 2}, new int[] {3, 6, 7, 2, 1},
                true);
        System.out.println("Maximum total speed " + actual);

        actual = new TandemBicycle().tandemBicycle(new int[] {5, 5, 3, 9, 2}, new int[] {3, 6, 7, 2, 1},
                false);
        System.out.println("Minimum total speed " + actual);
    }
}
