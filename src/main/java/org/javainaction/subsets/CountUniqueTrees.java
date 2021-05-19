package org.javainaction.subsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Count of Structurally Unique Binary Search Trees (hard) #
 * Given a number ‘n’, write a function to return the count of structurally unique Binary Search Trees (BST) that can
 * store values 1 to ‘n’.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: As we saw in the previous problem, there are 2 unique BSTs storing numbers from 1-2.
 * Example 2:
 *
 * Input: 3
 * Output: 5
 * Explanation: There will be 5 unique BSTs that can store numbers from 1 to 5.
 */
public class CountUniqueTrees {
    public int countTrees(int n) {
        List<Integer> count = new ArrayList<>();
        count.add(1);
        for (int m = 1; m < n + 1; m++) {
            int numberOfTrees = 0;
            for (int leftTreeSize = 0; leftTreeSize < m; leftTreeSize++) {
                int rightTreeSize = m - 1 - leftTreeSize;
                int noOfLeftTrees = count.get(leftTreeSize);
                int noOfRightTrees = count.get(rightTreeSize);
                numberOfTrees += noOfLeftTrees * noOfRightTrees;
            }
            count.add(numberOfTrees);
        }
        return count.get(n);
    }

    static Map<Integer, Integer> map = new HashMap<>();
    public int countTreesRecursion(int n) {
        if (map.containsKey(n))
            return map.get(n);

        if (n <= 1)
            return 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // making 'i' root of the tree
            int countOfLeftSubtrees = countTrees(i - 1);
            int countOfRightSubtrees = countTrees(n - i);
            count += (countOfLeftSubtrees * countOfRightSubtrees);
        }
        map.put(n, count);
        return count;
    }

    public static void main(String[] args) {
        CountUniqueTrees ct = new CountUniqueTrees();
        int count = ct.countTrees(3);
        System.out.print("Total trees: " + count);
    }
}
