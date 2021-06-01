package org.javainaction.bt.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a binary tree find a list of its branch sum ordered from left to right
 *
 * Branch sum is sum of all its node in a binary tree branch.
 *
 */
public class BranchSums {
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
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

    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> result = new ArrayList<>();
        findBranchSumPreorder(root, 0, result);
        return result;
    }

    public static void findBranchSumPreorder(BinaryTree node, int sum, List<Integer> result){
        if(node == null) return;

        int newSum = sum + node.value;

        if(node.left == null && node.right == null) {
            result.add(newSum);
            return;
        }

        findBranchSumPreorder(node.left, newSum, result);
        findBranchSumPreorder(node.right, newSum, result);
    }

    private static class TestBinaryTree extends BinaryTree {
        TestBinaryTree(int value) {
            super(value);
        }

        TestBinaryTree insert(List<Integer> values) {
            return insert(values, 0);
        }

        TestBinaryTree insert(List<Integer> values, int i) {
            if (i >= values.size()) return null;

            List<TestBinaryTree> queue = new ArrayList<TestBinaryTree>();
            queue.add(this);
            while (queue.size() > 0) {
                TestBinaryTree current = queue.get(0);
                queue.remove(0);
                if (current.left == null) {
                    current.left = new TestBinaryTree(values.get(i));
                    break;
                }
                queue.add((TestBinaryTree) current.left);
                if (current.right == null) {
                    current.right = new TestBinaryTree(values.get(i));
                    break;
                }
                queue.add((TestBinaryTree) current.right);
            }
            insert(values, i + 1);
            return this;
        }
    }

    public static void main(String[] args) {
        TestBinaryTree tree = new TestBinaryTree(1).insert(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> expected = new ArrayList<>(Arrays.asList(15, 16, 18, 10, 11));
        System.out.println(tree);
        System.out.println("Branch sum : " + branchSums(tree));
    }
}
