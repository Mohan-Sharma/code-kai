package com.code.kai.leetcode.curated75.easy.trees;

/**
 * @author Mohan Sharma
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeDFS(p, q);
    }

    private boolean isSameTreeDFS(TreeNode p, TreeNode q) {
        if ((p == null || q == null) && p != q)
            return false;
        if (p == q)
            return true;
        return (p.val == q.val) && isSameTreeDFS(p.left, q.left) && isSameTreeDFS(p.right, q.right);
    }
}
