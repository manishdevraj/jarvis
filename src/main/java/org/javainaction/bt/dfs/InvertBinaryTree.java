package org.javainaction.bt.dfs;

import org.javainaction.bt.RightSiblingTree;
import org.javainaction.bt.bfs.ConnectLevelOrderSiblings;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Invert binary tree
 *
 *                   1
 *               2      3
 *            4   5   6   7
 *          8  9
 *
 * Output to be
 *
 *                   1
 *               3       2
 *            7   6   5    4
 *                       9   8
 *
 */
public class InvertBinaryTree {
    public static void invertBinaryTree(BinaryTree tree) {
        // O(n) time | O(d) space
        invertBinaryTreeRoot(tree);
    }

    public static void invertBinaryTreeRoot(BinaryTree root) {
        if (null == root) return;

        swap(root);
        invertBinaryTreeRoot(root.left);
        invertBinaryTreeRoot(root.right);
    }

    public static void swap(BinaryTree root) {
        BinaryTree temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static void main(String[] args) {
        TestBinaryTree tree = new TestBinaryTree(1);
        tree.insert(new int[] {2, 3, 4, 5, 6, 7, 8, 9}, 0);
        System.out.println(getDfsOrder(tree));

        System.out.println("--------Expected-------------");

        InvertedBinaryTree invertedTree = new InvertedBinaryTree(1);
        invertedTree.insert(new int[] {2, 3, 4, 5, 6, 7, 8, 9}, 0);
        System.out.println(invertedTree.getDfsOrder(invertedTree));

        System.out.println("--------Inverted-------------");
        invertBinaryTree(tree);
        System.out.println(getDfsOrder(tree));


    }

    private static boolean compareBT(BinaryTree tree1, InvertedBinaryTree tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 != null && tree2 != null) {
            return tree1.value == tree2.value
                    && compareBT(tree1.left, tree2.left)
                    && compareBT(tree1.right, tree2.right);
        }
        return false;
    }

    private static class InvertedBinaryTree {
        public int value;
        public InvertedBinaryTree left;
        public InvertedBinaryTree right;

        public InvertedBinaryTree(int value) {
            this.value = value;
        }

        public void insert(int[] values, int i) {
            if (i >= values.length) {
                return;
            }
            ArrayDeque<InvertedBinaryTree> queue = new ArrayDeque<InvertedBinaryTree>();
            queue.addLast(this);
            while (queue.size() > 0) {
                InvertedBinaryTree current = queue.pollFirst();
                if (current.right == null) {
                    current.right = new InvertedBinaryTree(values[i]);
                    break;
                }
                queue.addLast(current.right);
                if (current.left == null) {
                    current.left = new InvertedBinaryTree(values[i]);
                    break;
                }
                queue.addLast(current.left);
            }
            insert(values, i + 1);
        }

        public List<Integer> getDfsOrder(InvertedBinaryTree tree) {
            List<Integer> values = new ArrayList<Integer>();
            values.add(tree.value);
            if (tree.left != null) {
                values.addAll(getDfsOrder(tree.left));
            }
            if (tree.right != null) {
                values.addAll(getDfsOrder(tree.right));
            }
            return values;
        }
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

    private static List<Integer> getDfsOrder(BinaryTree tree) {
        List<Integer> values = new ArrayList<Integer>();
        values.add(tree.value);
        if (tree.left != null) {
            values.addAll(getDfsOrder(tree.left));
        }
        if (tree.right != null) {
            values.addAll(getDfsOrder(tree.right));
        }
        return values;
    }
    
    private static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
