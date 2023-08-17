package com.code.kai.leetcode.curated75.easy.trees;

/**
 * @author Mohan Sharma
 */
public class LCABinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val)
                root = root.left;
            else if (root.val < p.val && root.val < q.val)
                root = root.right;
            else
                return root;
        }
    }

    public TreeNode lowestCommonAncestorRec(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestorRec(root.left, p, q);
        else if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestorRec(root.right, p, q);
        else
            return root;
    }
}
