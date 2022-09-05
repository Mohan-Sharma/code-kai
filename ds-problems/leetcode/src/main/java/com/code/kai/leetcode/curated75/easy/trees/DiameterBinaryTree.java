package com.code.kai.leetcode.curated75.easy.trees;

/**
 * @author Mohan Sharma
 */
public class DiameterBinaryTree {

    // diameter is basically sum of maxLeft and maxRight subtree excluding the root
    public int diameterOfBinaryTree(TreeNode root) {
        int[] result = new int[1];
        depthRecursively(root, result);
        return result[0];
    }

    private int depthRecursively(TreeNode root, int[] result) {
        if (root == null)
            return 0;
        int left = depthRecursively(root.left, result);
        int right = depthRecursively(root.right, result);
        result[0] = Math.max(result[0], left + right);
        return 1 + Math.max(left, right);
    }
}
