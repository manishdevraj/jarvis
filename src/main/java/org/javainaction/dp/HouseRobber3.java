package org.javainaction.dp;


/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that all houses in this place form a binary tree.
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 *
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobber3 {
    public int rob(TreeNode root) {
        int[] result = findMaxSteal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] findMaxSteal(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] stealFromLeft = findMaxSteal(node.left);
        int[] stealFromRight = findMaxSteal(node.right);
        //if we rob current house then we cannot rob other houses, but their child houses if any
        int rob = node.val + stealFromLeft[1] + stealFromRight[1];
        //we are free to choose from either of it's children's sub tree recursively applying same rule
        int notRob = Math.max(stealFromLeft[0], stealFromLeft[1]) + Math.max(stealFromRight[0], stealFromRight[1]);

        return new int[]{rob, notRob};
    }

    static  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        System.out.println(new HouseRobber3().rob(root));
    }
}
