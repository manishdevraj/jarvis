package org.javainaction.graph;

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

        while (diff > 0) {
            diff--;
            lowerDescendant = lowerDescendant.ancestor;
        }

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
}
