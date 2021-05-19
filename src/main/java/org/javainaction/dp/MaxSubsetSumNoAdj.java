package org.javainaction.dp;

/**
 * Same as house thief problem
 */
public class MaxSubsetSumNoAdj {
    public static int maxSubsetSumNoAdjacent(int[] array) {
        if(array.length == 0) return 0;
        else if(array.length == 1) return array[0];

        int p1 = array[0];
        int p2 = Math.max(array[1], p1);
        for(int i = 2; i < array.length; i++) {
            int currentMax = Math.max(p2, p1 + array[i]);
            p1 = p2;
            p2 = currentMax;
        }
        return p2;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(maxSubsetSumNoAdjacent(arr));
        arr = new int[] {2, 10, 14, 8, 1};
        System.out.println(maxSubsetSumNoAdjacent(arr));
    }
}
