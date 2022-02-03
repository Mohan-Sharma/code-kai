package com.code.kai.leetcode.curated75.easy.trees;

/**
 * @author Mohan Sharma
 */
public class SubTreeChecker {

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return isSubtreeDFS(root, subRoot);
    }

    private static boolean isSubtreeDFS(TreeNode root, TreeNode subRoot) {
        if ((null == root || null == subRoot) && root != subRoot)
            return false;
        if (isSameTree(root, subRoot)) {
            return true;
        }
        return isSubtreeDFS(root.left, subRoot) || isSubtreeDFS(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null || q == null) && p != q)
            return false;
        if (p == q)
            return true;
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}