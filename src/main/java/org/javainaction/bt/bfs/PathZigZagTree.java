package org.javainaction.bt.bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 *
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even
 * numbered rows (second, fourth, sixth,...), the labelling is right to left.
 *
 *
 *
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node
 * with that label.
 *
 *
 *
 * Example 1:
 *
 * Input: label = 14
 * Output: [1,3,4,14]
 * Example 2:
 *
 * Input: label = 26
 * Output: [1,2,6,10,26]
 */
public class PathZigZagTree {
    /**
     *
     * For the first number on level n, there are total 2 ^ n before it.
     *
     * depth = 0   |                            1
     * depth = 1   |                 3                     2
     * ...
     * depth = n   |    2^ n-1, ....                     2^n-k/2-1....
     * 				  /	    \						   /   \
     * depth = n+1 | 2^n,     2^n+1, ....           2^n+k, 2^n+(k+1)....
     *
     * We first get the current depth and position of the given number, then deduct (pos + 1) from the
     * label which will get last number on the upper level. For example from the image, the current position of
     * 14 is depth=3 and pos=6. We deduct 7 get 7. The next thing is to find the position of its parent,
     * which is pos / 2. For example, 14 position is 6, the position of its parent (4) is 3.
     * In this way, we get all the numbers. The time complexity is O(lgn)
     * @param label
     * @return
     */
    public static List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new ArrayList<>();
        if (label <= 0) return result;

        int level = 0;
        while (Math.pow(2, level) - 1 < label) level++;
        // calculate the depth, 0 indexed, 0 is odd
        level--; //
        while (level != 0) {
            result.add(0, label);
            //calculate position
            int pos = (int) (label - Math.pow(2, level));
            label = label - (pos + 1) - pos / 2;
            level--;
        }
        result.add(0 ,1);
        return result;
    }

    public static void main(String[] args) {
        //  Input: label = 14
        //  Output: [1,3,4,14]
        System.out.println("Path in zigzag tree for 14 label is : " + pathInZigZagTree(14));

        //  Input: label = 26
        //  Output: [1,2,6,10,26]
        System.out.println("Path in zigzag tree for 14 label is : " + pathInZigZagTree(26));
    }
}
