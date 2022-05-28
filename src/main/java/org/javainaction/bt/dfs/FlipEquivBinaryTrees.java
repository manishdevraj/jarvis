package org.javainaction.bt.dfs;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 *
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 *
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Flipped Trees Diagram
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * Example 2:
 *
 * Input: root1 = [], root2 = []
 * Output: true
 * Example 3:
 *
 * Input: root1 = [], root2 = [1]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in each tree is in the range [0, 100].
 * Each tree will have unique node values in the range [0, 99].
 */
public class FlipEquivBinaryTrees {

    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfsFlipEquivalent(root1, root2);
    }

    public static boolean dfsFlipEquivalent(TreeNode nodeOne, TreeNode nodeTwo) {
        if (nodeOne == null && nodeTwo == null) return true;
        if (nodeOne == null || nodeTwo == null) return false;

        boolean leftRightFlippedEquality = dfsFlipEquivalent(nodeOne.left, nodeTwo.right);
        boolean rightLeftFlippedEquality = dfsFlipEquivalent(nodeOne.right, nodeTwo.left);

        boolean leftNotFlippedEquality = dfsFlipEquivalent(nodeOne.left, nodeTwo.left);
        boolean rightNotFlippedEquality = dfsFlipEquivalent(nodeOne.right, nodeTwo.right);

        return nodeOne.val == nodeTwo.val
                && ((leftRightFlippedEquality && rightLeftFlippedEquality)
                || (leftNotFlippedEquality && rightNotFlippedEquality));
    }

    public static void main(String[] args) {
        TreeNode rootOne = new TreeNode(1);
        rootOne.left = new TreeNode(2);
        rootOne.right = new TreeNode(3);
        rootOne.left.left = new TreeNode(4);
        rootOne.left.right = new TreeNode(5);
        rootOne.right.left = new TreeNode(6);
        rootOne.left.right.left = new TreeNode(7);
        rootOne.left.right.right = new TreeNode(8);

        TreeNode rootTwo = new TreeNode(1);
        rootTwo.left = new TreeNode(3);
        rootTwo.right = new TreeNode(2);
        rootTwo.right.left = new TreeNode(4);
        rootTwo.right.right = new TreeNode(5);
        rootTwo.left.right = new TreeNode(6);
        rootTwo.right.right.left = new TreeNode(8);
        rootTwo.right.right.right = new TreeNode(7);

        System.out.println("Flip equality : " + flipEquiv(rootOne, rootTwo));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
