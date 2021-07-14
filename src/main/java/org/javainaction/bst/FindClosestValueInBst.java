package org.javainaction.bst;

/**
 * In a BST tree given a target value, find the closed value in BST
 *
 *                 10
 *            5         15
 *         2    6    13      22
 *       1            14
 *
 *       Target = 12 Output would be 13
 */
public class FindClosestValueInBst {
    //Average O(log(n)) time | O(1) space
    //Worst O(n) time | O(1) space
    public static int findClosestValueLowSpace(BST tree, int target) {
        double closest = Double.MAX_VALUE;
        BST node = tree;
        while (node != null) {
            if (Math.abs(target - closest) > Math.abs(target - node.value)) closest = node.value;

            if (target < node.value) node = node.left;
            else if (target > node.value) node = node.right;
            else break;
        }
        return (int) closest;
    }

    //Average O(log(n)) time | O(log(n)) space
    //Worst O(n) time | O(n) space
    public static int findClosestValueInBst(BST tree, int target) {
        return findClosestValueRecursive(tree, target, Double.MAX_VALUE);
    }

    private static int findClosestValueRecursive(BST tree, int target, double closest) {
        if (Math.abs(target - closest) > Math.abs(target - tree.value)) closest =  tree.value;

        if (tree.left != null && target < tree.value) return findClosestValueRecursive(tree.left, target, closest);
        else if (tree.right != null && target > tree.value) return findClosestValueRecursive(tree.right, target, closest);
        return (int) closest;
    }

    public static void main(String[] args) {
        var root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(6);
        root.right = new BST(15);
        root.right.left = new BST(13);
        root.right.left.right = new BST(14);
        root.right.right = new BST(22);

        var expected = 13;
        var actual = findClosestValueInBst(root, 12);
        System.out.println("Target value 12 is close to " + actual);
        System.out.println("Target value 12 is close to " + findClosestValueLowSpace(root, 12));
    }
    
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
