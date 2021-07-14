package org.javainaction.bt.bfs;

import java.util.*;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value k.
 *
 * Return a list of the values of all nodes that have a distance k from the target node.
 * The answer can be returned in any order.
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 *
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 * Note:
 *
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= k <= 1000.
 */
public class FindNodesDistanceK {

    public static ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
        Map<Integer, BinaryTree> nodeParentMap = new HashMap<>();
        //populate a map of parents where key is node value and value is parent node
        populateParents(tree, nodeParentMap, null);

        //find target node by peeking into parent's of target value
        //either left or right value will be target
        BinaryTree targetNode = findNode(target, tree, nodeParentMap);

        //noe traverse BFS to find all nodes with K distance
        return bfsNodesDistanceK(targetNode, nodeParentMap, k);
    }

    public static ArrayList<Integer> bfsNodesDistanceK(BinaryTree targetNode, Map<Integer, BinaryTree> nodeParentMap,
                                                int k) {
        Queue<Pair<BinaryTree, Integer>> queue = new LinkedList<>();
        //start with 0 distance
        queue.offer(new Pair<>(targetNode, 0));

        //use this to avoid visiting same node again
        Set<Integer> seen = new HashSet<>();
        seen.add(targetNode.value);

        while (!queue.isEmpty()) {
            Pair<BinaryTree, Integer> pair = queue.poll();

            BinaryTree current = pair.first;
            int distance = pair.second;

            if (distance == k) { //we have match for k distance now get all values from queue
                ArrayList<Integer> nodeDistanceK = new ArrayList<>();
                for (Pair<BinaryTree, Integer> p : queue) {
                    nodeDistanceK.add(p.first.value);
                }
                //don't forget the current value
                nodeDistanceK.add(current.value);
                return nodeDistanceK;
            }

            //go in all directions
            List<BinaryTree> connectedNodes = new ArrayList<>();
            connectedNodes.add(current.left);
            connectedNodes.add(current.right);
            connectedNodes.add(nodeParentMap.get(current.value));

            for (BinaryTree node : connectedNodes) {
                if (node == null) continue; //if node is null

                //if it is already seen continue
                if (seen.contains(node.value)) continue;

                //mark seen and add distance + 1
                seen.add(node.value);
                queue.add(new Pair<>(node, distance + 1));
            }
        }

        return new ArrayList<>();
    }

    public static BinaryTree findNode(int target, BinaryTree tree,
                               Map<Integer, BinaryTree> nodeParentMap) {
        if (tree.value == target) return tree;
        BinaryTree parent = nodeParentMap.get(target);
        if (parent.left != null && parent.left.value == target) return parent.left;
        return parent.right;
    }

    public static void populateParents(BinaryTree node,
                                Map<Integer, BinaryTree> nodeParentMap,
                                BinaryTree parent) {
        if (node != null) {
            nodeParentMap.put(node.value, parent);
            populateParents(node.left, nodeParentMap, node);
            populateParents(node.right, nodeParentMap, node);
        }
    }

    static class Pair<U, V> {
        public final U first;
        public final V second;

        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args){
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.right.right = new BinaryTree(6);
        root.right.right.left = new BinaryTree(7);
        root.right.right.right = new BinaryTree(8);
        int target = 3;
        int k = 2;
        var expected = new ArrayList<Integer>(Arrays.asList(2, 7, 8));
        System.out.println(findNodesDistanceK(root, target, k));

        root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);

        root.right.left = new BinaryTree(5);
        root.right.right = new BinaryTree(6);

        root.right.right.right = new BinaryTree(7);
        root.right.right.right.right = new BinaryTree(8);
        target = 8;
        k = 6;
        expected = new ArrayList<Integer>(Arrays.asList(4));
        System.out.println(findNodesDistanceK(root, target, k));
        /*
        {
  "k": 6,
  "target": 8,
  "tree": {
    "nodes": [
      {"id": "1", "left": "2", "right": "3", "value": 1},
      {"id": "2", "left": "4", "right": null, "value": 2},
      {"id": "3", "left": "5", "right": "6", "value": 3},
      {"id": "4", "left": null, "right": null, "value": 4},
      {"id": "5", "left": null, "right": null, "value": 5},
      {"id": "6", "left": null, "right": "7", "value": 6},
      {"id": "7", "left": null, "right": "8", "value": 7},
      {"id": "8", "left": null, "right": null, "value": 8}
    ],
    "root": "1"
  }
}
         */
    }
}
