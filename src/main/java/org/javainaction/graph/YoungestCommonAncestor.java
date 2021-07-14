package org.javainaction.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an AncestralTree class object that points its youngest ancestor. When give top ancestor
 * and two descendant A and B, find out youngest common ancestor of A and B
 *
 *             A
 *        B          C
 *     D     E     F   G
 *  H   I
 *
 *  Given A as top ancestor and two descendant E, I
 *
 *  Output: B
 */
public class YoungestCommonAncestor {
    // O(d) time | O(1) space where d is depth of AncestralTree
    public static AncestralTree getYoungestCommonAncestor(
            AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
        int depthOne = getDescendantDepth(descendantOne, topAncestor);
        int depthTwo = getDescendantDepth(descendantTwo, topAncestor);

        if (depthOne > depthTwo) {
            return backtrackAncestralTree(descendantOne, descendantTwo, depthOne - depthTwo);
        } else {
            return backtrackAncestralTree(descendantTwo, descendantOne, depthTwo - depthOne);
        }
    }

    public static AncestralTree backtrackAncestralTree(AncestralTree lowerDescendant,
                                                       AncestralTree higherDescendant,
                                                       int diff) {

        //traverse extra depth from longest child
        while (diff > 0) {
            diff--;
            lowerDescendant = lowerDescendant.ancestor;
        }
        //until their ancestor do not match or they reach at top ancestor
        while (lowerDescendant != higherDescendant) {
            lowerDescendant = lowerDescendant.ancestor;
            higherDescendant = higherDescendant.ancestor;
        }
        return lowerDescendant;
    }

    public static int getDescendantDepth(AncestralTree descendant, AncestralTree topAncestor) {
        int depth = 0;
        while (descendant != topAncestor) {
            descendant = descendant.ancestor;
            depth++;
        }
        return depth;
    }

    static class AncestralTree {
        public char name;
        public AncestralTree ancestor;

        AncestralTree(char name) {
            this.name = name;
            this.ancestor = null;
        }

        // This method is for testing only.
        void addAsAncestor(AncestralTree[] descendants) {
            for (AncestralTree descendant : descendants) {
                descendant.ancestor = this;
            }
        }
    }

    public static Map<Character, AncestralTree> getTrees() {
        var trees = new HashMap<Character, AncestralTree>();
        var alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char a : alphabet.toCharArray()) {
            trees.put(a, new AncestralTree(a));
        }

        trees
                .get('A')
                .addAsAncestor(
                        new AncestralTree[] {
                                trees.get('B'), trees.get('C'), trees.get('D'), trees.get('E'), trees.get('F')
                        });
        return trees;
    }

    public static void main(String[] args) {
        var trees = getTrees();
        trees.get('A').addAsAncestor(new AncestralTree[] {trees.get('B'), trees.get('C')});
        trees.get('B').addAsAncestor(new AncestralTree[] {trees.get('D'), trees.get('E')});
        trees.get('D').addAsAncestor(new AncestralTree[] {trees.get('H'), trees.get('I')});
        trees.get('C').addAsAncestor(new AncestralTree[] {trees.get('F'), trees.get('G')});

        var yca = getYoungestCommonAncestor(trees.get('A'), trees.get('E'), trees.get('I'));
        System.out.println("Youngest common ancestor is : " + yca.name);
    }
}
