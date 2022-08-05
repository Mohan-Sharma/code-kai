package com.code.kai.leetcode.curated75.easy.trees;

/**
 * @author Mohan Sharma
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return false;
        return checkDepth(root) != -1;
    }

    private int checkDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = checkDepth(root.left);
        int right = checkDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return 1 + Math.max(left, right);
    }

    public boolean isBalancedBruteForce(TreeNode root) {
        if (root == null)
            return true;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (Math.abs(left - right) > 1)
            return false;
        return isBalancedBruteForce(root.left) && isBalancedBruteForce(root.right);
    }

    private int getHeight(TreeNode n) {
        if (n == null)
            return 0;
        return 1 + Math.max(getHeight(n.left), getHeight(n.right));
    }
}
