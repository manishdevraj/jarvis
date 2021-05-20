package org.javainaction.twopointers;

import java.util.Arrays;

/**
 * You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats
 * where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time,
 * provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 *
 *
 * Example 1:
 *
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 * Example 2:
 *
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 * Example 3:
 *
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 * Explanation: 4 boats (3), (3), (4), (5)
 */
public class BoatToSavePeople {
    public static void main(String[] arg) {
        System.out.println("{1, 2} with 3 limit needs these many boats: "
                + numRescueBoats(new int[] {1, 2}, 3));
        System.out.println("{3,2,2,1} with 3 limit needs these many boats: "
                + numRescueBoats(new int[] {3,2,2,1}, 3));
        System.out.println("{3,5,3,4} with 5 limit needs these many boats: "
                + numRescueBoats(new int[] {3,5,3,4}, 3));
    }

    /**
     * If the heaviest person can share a boat with the lightest person, then do so.
     * Otherwise, the heaviest person can't pair with anyone, so they get their own boat.
     *
     * The reason this works is because if the lightest person can pair with anyone,
     * they might as well pair with the heaviest person.
     * @param people
     * @param limit
     * @return
     */
    public static int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) return 0;
        Arrays.sort(people);
        int boat = 0;
        int left = 0, right = people.length - 1;

        while (left <= right) {
            boat++;
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
        }
        return boat;
    }
}
