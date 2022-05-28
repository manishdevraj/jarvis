package org.javainaction.bt.dfs;

import java.util.ArrayDeque;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 */
public class DiameterBinaryTree {

    public static int diameterOfBinaryTree(BinaryTree root) {
        return findDiameter(root).diameter;
    }

    public static TreeInfo findDiameter(BinaryTree current) {
        if (current == null) return new TreeInfo(0, 0);

        TreeInfo leftInfo = findDiameter(current.left);
        TreeInfo rightInfo = findDiameter(current.right);

        //max height from both childs
        int maxHeight = leftInfo.height + rightInfo.height;
        //max diameter from either child
        int maxDiameter = Math.max(leftInfo.diameter, rightInfo.diameter);

        //current diameter is either max diameter from either child or their total height
        int currentDiameter = Math.max(maxHeight, maxDiameter);

        //current height would be self plus either left max or right max height
        int currentHeight = 1 + Math.max(leftInfo.height, rightInfo.height);

        return new TreeInfo(currentDiameter, currentHeight);
    }

    static class TreeInfo {
        private int diameter;
        private int height;
        public TreeInfo(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        TestBinaryTree input = new TestBinaryTree(1);
        input.insert(new int[] {2, 3, 4, 5}, 0);
        var expected = 3;
        var actual = diameterOfBinaryTree(input);
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
