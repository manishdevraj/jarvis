package org.javainaction.bt.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 */
public class PathSum3 {
    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        //this will be used as count  = 1 when sum is found in match
        map.put(0, 1);

        return findPathSum(root, 0, targetSum, map);
    }

    public static int findPathSum(TreeNode node, int sum, int targetSum,
                           Map<Integer, Integer> map) {

        if (node == null) return 0;

        sum += node.val;
        //valid paths ending at current node
        int countPath = map.getOrDefault( sum - targetSum, 0);

        map.put(sum, map.getOrDefault(sum, 0) + 1);

        int totalCount = countPath + findPathSum(node.left, sum, targetSum, map)
                + findPathSum(node.right, sum, targetSum, map);

        //reset at the end of resursive stack
        map.put(sum, map.get(sum) - 1);

        return totalCount;
    }

    /**
     * Space: O(n) due to recursion.
     * Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree)
     */
    public static int pathSumDFS(TreeNode root, int targetSum) {
        if (root == null) return 0;
        //ass path sum can start from non root too, we nee to make sure
        //root with its both child are processed into recursive excluding root
        return pathSumRecursive(root, targetSum)
                + pathSumRecursive(root.left, targetSum)
                + pathSumRecursive(root.right, targetSum);
    }

    public static  int pathSumRecursive(TreeNode node, int targetSum) {
        if (node == null) return 0;
        //either we found match at current node or with current value if it same as total target sum
        //as we are trying to find all path sums, we need to make sure we substracted current value from target expected to keep up with comparison
        return (targetSum == node.val ? 1 : 0)
                + pathSumRecursive(node.left, targetSum - node.val)
                + pathSumRecursive(node.right, targetSum - node.val);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);


        System.out.println("root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8 : " +
                 + pathSum(root, 8));

        System.out.println("root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8 : " +
                + pathSumDFS(root, 8));
    }
}
