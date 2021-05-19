package org.javainaction.bt;

/**
 * Implement locking in a binary tree. A binary tree node can be locked or unlocked only if all of its descendants or
 * ancestors are not locked.
 *
 * Design a binary tree node class with the following methods:
 *
 * is_locked, which returns whether the node is locked
 * lock, which attempts to lock the node. If it cannot be locked, then it should return false. Otherwise,
 * it should lock it and return true.
 * unlock, which unlocks the node. If it cannot be unlocked, then it should return false. Otherwise,
 * it should unlock it and return true.
 * You may augment the node to add parent pointers or any other property you would like.
 * You may assume the class is used in a single-threaded program, so there is no need for actual locks or mutexes.
 * Each method should run in O(h), where h is the height of the tree.
 */
public class BinaryTreeWithLock {
    //O(h) time
    private static class BinaryTree {
        int value;
        boolean locked;
        BinaryTree left;
        BinaryTree right;
        BinaryTree parent;
        int lockedDescendant;
    }

    public boolean isLocked(BinaryTree node) {
        return node.locked;
    }

    public boolean lock(BinaryTree node) {
        if (isLocked(node)) {
            return true;
        }

        if (canLockUnlock(node)) return false;

        node.locked = true;

        BinaryTree parent = node.parent;
        while (parent != null) {
            parent.lockedDescendant += 1;
            parent = parent.parent;
        }
        return true;
    }

    public boolean unLock(BinaryTree node) {
        if (!isLocked(node)) {
            return true;
        }

        if (canLockUnlock(node)) return false;

        node.locked = false;

        BinaryTree parent = node.parent;
        while (parent != null) {
            parent.lockedDescendant -= 1;
            parent = parent.parent;
        }
        return true;
    }

    private boolean canLockUnlock(BinaryTree node) {
        if (node.lockedDescendant > 0) return true;

        BinaryTree parent = node.parent;
        while (parent != null) {
            if (parent.locked) return true;
            parent = parent.parent;
        }
        return false;
    }
}
