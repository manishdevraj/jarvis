package org.javainaction.bst;

public class BSTConstruction {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        // Average O(lon(n)) time | O(long(n)) space
        // Worst O(n) time | O(n) space
        public BST insert(int value) {
            if(value < this.value) {
                if(left == null) {
                    BST node = new BST(value);
                    left = node;
                } else {
                    left.insert(value);
                }
            } else {
                if(right == null) {
                    BST node = new BST(value);
                    right = node;
                } else {
                    right.insert(value);
                }
            }
            return this;
        }

        // Average O(lon(n)) time | O(long(n)) space
        // Worst O(n) time | O(n) space
        public boolean contains(int value) {
            if(value < this.value) {
                if(left == null) {
                    return false;
                } else {
                    return left.contains(value);
                }
            } else if(value > this.value) {
                if(right == null) {
                    return false;
                } else {
                    return right.contains(value);
                }
            } else {
                return true;
            }
        }

        // Average O(lon(n)) time | O(long(n)) space
        // Worst O(n) time | O(n) space
        public BST remove(int value) {
            remove(value, null);
            return this;
        }

        public void remove(int value, BST parent) {
            if(value < this.value) {
                if(left != null) {
                    left.remove(value, this);
                }
            } else if(value > this.value) {
                if(right != null) {
                    right.remove(value, this);
                }
            } else {
                //actual removal logic
                //both child exist for a node we are removing
                if (left != null && right != null) {
                    //smallest value of right subtree
                    this.value = right.getMinValue();
                    //pass current node as parent node
                    right.remove(this.value, this);
                } else if (parent == null) {
                    //root node doesn't have parent node
                    //swap children of either side
                    if(left != null){
                        this.value = left.value;
                        //stop overriding values
                        right = left.right;
                        left = left.left;
                    } else if(right != null) {
                        //stop overriding values
                        this.value = right.value;
                        left = right.left;
                        right = right.right;
                    } else {
                        // this is single node tree do nothing;
                    }
                } else if(parent.left == this) {
                    //current node is left child
                    //assign parent node to left or child depending on which is non null
                    parent.left = left != null ? left : right;
                } else if(parent.right == this) {
                    //current node is right child
                    //assign parent node to left or child depending on which is non null
                    parent.right = left != null ? left : right;
                }
            }
        }

        public int getMinValue(){
            if (left == null) {
                return this.value;
            } else {
                return left.getMinValue();
            }
        }
    }

    public static void main(String[] args) {
        var root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(5);
        root.right = new BST(15);
        root.right.left = new BST(13);
        root.right.left.right = new BST(14);
        root.right.right = new BST(22);

        root.insert(12);
        assert root.right.left.left.value == 12;

        root.remove(10);
        assert root.contains(10) == false;
        assert root.value == 12;
        assert root.contains(15);
    }
}
