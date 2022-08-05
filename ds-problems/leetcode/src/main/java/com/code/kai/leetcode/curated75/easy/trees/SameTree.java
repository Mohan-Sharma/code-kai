package com.code.kai.leetcode.curated75.easy.trees;

/**
 * @author Mohan Sharma
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private boolean isSameTreeDFS(TreeNode p, TreeNode q) {
        if ((p == null || q == null) && p != q)
            return false;
        if (p == q)
            return true;
        return (p.val == q.val) && isSameTreeDFS(p.left, q.left) && isSameTreeDFS(p.right, q.right);
    }
}
