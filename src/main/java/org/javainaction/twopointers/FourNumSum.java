package org.javainaction.twopointers;

import java.util.*;

/**
 * Find the quadruplet of the target sum in a given array, {7, 6, 4, -1, 1, 2} with target sum 16
 * Answer would be {7, 6, 4, -1}, {7, 6, 1, 2}
 * @see QuadrupleSumToTarget
 */
public class FourNumSum {
    public static void main(String[] args) {
        List<List<Integer>> output = fourNumberSum(new int[] {7, 6, 4, -1, 1, 2}, 16);
        System.out.println("Four num sum for {7, 6, 4, -1, 1, 2} with target sum of 16 are :");
        output.stream().forEach( a -> {
            a.stream().forEach(System.out::print);
        });

        output = fourNumberSum(new int[] {2, 2, 2, 2, 2}, 8);
        System.out.println("Four num sum for {2, 2, 2, 2, 2} with target sum of 8 are :");
        output.stream().forEach( a -> {
            a.stream().forEach(System.out::print);
        });
    }
    // Average: O(n^2) time  | O(n^2) space
    // Worst: O(n^3) time  | O(n^2) space
    public static List<List<Integer>> fourNumberSum(int[] array, int targetSum) {
        Map<Integer, List<List<Integer>>> allPairSum = new HashMap<>();
        List<List<Integer>> quadraplets = new ArrayList<>();
        for(int i = 1; i < array.length - 1; i++) {
            for(int j = i + 1; j < array.length; j++){
                int currentSum = array[i] + array[j];
                int difference = targetSum - currentSum;
                if(allPairSum.containsKey(difference)){
                    for(List<Integer> pair : allPairSum.get(difference)){
                        List<Integer> q = new ArrayList<>(pair);
                        q.add(array[i]);
                        q.add(array[j]);
                        System.out.println(Arrays.asList(q) + " " + quadraplets.contains(q));
                        if (!quadraplets.contains(q)) quadraplets.add(q);
                    }
                }
            }

            for(int k = 0; k < i; k++) {
                int currentSum = array[i] + array[k];
                List<Integer> pair = Arrays.asList(array[i], array[k]);
                if(allPairSum.containsKey(currentSum)){
                    allPairSum.get(currentSum).add(pair);
                } else {
                    List<List<Integer>> pairsGroup = new ArrayList<>();
                    pairsGroup.add(pair);
                    allPairSum.put(currentSum, pairsGroup);
                }
            }
        }
        return quadraplets;
    }
}
