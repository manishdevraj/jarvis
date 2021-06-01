package org.javainaction.bt.dfs;

import java.util.ArrayDeque;

/**
 * Write a function that returns the diameter of the binary tree. A diameter is defined as length of its longest path
 * even if the path does not go through root.
 *
 * A path is a collection of connected nodes in a tree where no node is connected to more than 2 nodes.
 * Length of the path is number of edges between current not and its leaf.
 *
 *                  1
 *                3   2
 *              7  4
 *             8    5
 *            9      6
 *
 * Answer would be 6 as diameter of node 3 is 6 which is greater than the height of the root itself which is 5
 *
 */
public class BinaryTreeDiameter {

    public static int binaryTreeDiameter(BinaryTree tree) {
        // Write your code here.
        return findBinaryTreeDiameter(tree).diameter;
    }

    public static TreeInfo findBinaryTreeDiameter(BinaryTree parent) {
        if (parent == null) return new TreeInfo(0, 0);

        var leftTree = parent.left;
        var rightTree = parent.right;

        var leftTreeInfo = findBinaryTreeDiameter(leftTree);
        var rightTreeInfo = findBinaryTreeDiameter(rightTree);
        var maxDiameter = Math.max(leftTreeInfo.diameter, rightTreeInfo.diameter);
        var longestPathThroughRoot = leftTreeInfo.height + rightTreeInfo.height;

        var currentDiameter = Math.max(maxDiameter, longestPathThroughRoot);
        var currentHeight = 1 + Math.max(leftTreeInfo.height, rightTreeInfo.height);

        return new TreeInfo(currentDiameter, currentHeight);
    }

    private static class TreeInfo {
        public int diameter;
        public int height;

        public TreeInfo(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        TestBinaryTree input = new TestBinaryTree(1);
        input.insert(new int[] {2, 3, 4, 5, 6, 7}, 0);
        var expected = 4;
        var actual = binaryTreeDiameter(input);
        System.out.println(input);
        System.out.println("Diameter is " + actual);
    }

    private static class TestBinaryTree extends BinaryTree {
        public TestBinaryTree(int value) {
            super(value);
        }

        public void insert(int[] values, int i) {
            if (i >= values.length) {
                return;
            }
            ArrayDeque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
            queue.addLast(this);
            while (queue.size() > 0) {
                BinaryTree current = queue.pollFirst();
                if (current.left == null) {
                    current.left = new BinaryTree(values[i]);
                    break;
                }
                queue.addLast(current.left);
                if (current.right == null) {
                    current.right = new BinaryTree(values[i]);
                    break;
                }
                queue.addLast(current.right);
            }
            insert(values, i + 1);
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


}
