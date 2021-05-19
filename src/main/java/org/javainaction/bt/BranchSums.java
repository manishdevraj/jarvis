package org.javainaction.bt;

import java.util.ArrayList;
import java.util.List;

public class BranchSums {
    // This is the class of the input root. Do not edit it.
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> result = new ArrayList<>();
        traversePreorder(root, 0, result);
        return result;
    }

    public static void traversePreorder(BinaryTree node, int sum, List<Integer> result){
        if(node == null) return;

        int newSum = sum + node.value;

        if(node.left == null && node.right == null) {
            result.add(newSum);
            return;
        }

        traversePreorder(node.left, newSum, result);
        traversePreorder(node.right, newSum, result);

    }
}
