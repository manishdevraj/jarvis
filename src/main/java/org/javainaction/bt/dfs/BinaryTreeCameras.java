package org.javainaction.bt.dfs;

/**
 * You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 *
 *
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * Node.val == 0
 * Accepted
 * 72,653
 * Submissions
 * 168,860
 */
public class BinaryTreeCameras {

    /**
     * Observations:
     * 1. Has camera - current node already has camerea
     * 2. Covered - because child of the node has camera so current node is covered
     * 3. Please cover - neight child has camera and there is not camera as #1 did not     * match, please install camera
     */
    private static int camera = 0;
    public enum Camera {HAS_CAMERA, COVERED, PLEASE_COVER};

    public static int minCameraCover(TreeNode root) {
        camera = 0;
        return cover(root) == Camera.PLEASE_COVER ? ++camera : camera;
    }

    private static Camera cover(TreeNode current) {
        if (current == null) return Camera.COVERED;

        Camera leftCamera = cover(current.left);
        Camera rightCamera = cover(current.right);

        if (leftCamera == Camera.PLEASE_COVER || rightCamera == Camera.PLEASE_COVER){
            camera++;
            return Camera.HAS_CAMERA;
        }

        if (leftCamera == Camera.HAS_CAMERA || rightCamera == Camera.HAS_CAMERA) {
            return Camera.COVERED;
        }

        return Camera.PLEASE_COVER;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        System.out.println("Min camera needed: " + minCameraCover(root));

        root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(0);
        System.out.println("Min camera needed: " + minCameraCover(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
