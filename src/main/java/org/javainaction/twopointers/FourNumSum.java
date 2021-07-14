package org.javainaction.twopointers;

import java.util.*;

/**
 * Find the quadruplet of the target sum in a given array, {7, 6, 4, -1, 1, 2} with target sum 16
 * Answer would be {7, 6, 4, -1}, {7, 6, 1, 2}
 * @see QuadrupleSumToTarget
 */
public class FourNumSum {
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

    //Another way which does not worry about duplicate quadruplets
    public static List<List<Integer>> fourNumberSumBinarySearch(int[] array, int targetSum) {
        Arrays.sort(array);
        var result = new ArrayList<List<Integer>>();
        for (int i = 0; i < array.length - 3; i++) {
            for (int j = i + 1; j < array.length - 2; j++) {
                searchPairs(array, targetSum, i, j, result);
            }
        }
        return result;
    }

    private static void searchPairs(int[] array, int target, int first, int second, ArrayList<List<Integer>> result) {
        int left = second + 1;
        int right = array.length - 1;
        while (left < right) {
            int potentialMatch = array[first] + array[second] + array[left] + array[right];
            if (potentialMatch == target) {
                result.add(Arrays.asList(array[first], array[second], array[left], array[right]));
                left++;
                right--;
            } else if (potentialMatch < target) left++;
            else right--;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> output = fourNumberSum(new int[] {7, 6, 4, -1, 1, 2}, 16);
        System.out.println("Four num sum for {7, 6, 4, -1, 1, 2} with target sum of 16 are :");
        output.forEach(System.out::println);

        output = fourNumberSum(new int[] {2, 2, 2, 2, 2}, 8);
        System.out.println("Four num sum for {2, 2, 2, 2, 2} with target sum of 8 are :");
        output.forEach(System.out::println);

        output = fourNumberSumBinarySearch(new int[] {7, 6, 4, -1, 1, 2}, 16);
        System.out.println("quadruplets for {7, 6, 4, -1, 1, 2} with target sum of 16 are :");
        output.forEach(System.out::println);

        //this will not be unique so we need to add more checks if need unique quadruplets
        output = fourNumberSumBinarySearch(new int[] {2, 2, 2, 2, 2}, 8);
        System.out.println("quadruplets for {2, 2, 2, 2, 2} with target sum of 8 are :");
        output.forEach(System.out::println);
    }
}
