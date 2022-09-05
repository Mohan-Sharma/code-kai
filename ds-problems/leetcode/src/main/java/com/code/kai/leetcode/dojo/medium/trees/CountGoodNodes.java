package com.code.kai.leetcode.dojo.medium.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;

/**
 * @author Mohan Sharma
 */
public class CountGoodNodes {

    // basic all nodes traversal, count all nodes that are larger that previous node
    public int goodNodes(TreeNode root) {
        if (root == null)
            return 0;
        return countGoodNodes(root, root.val);
    }

    private int countGoodNodes(TreeNode root, int max) {
        if (root == null)
            return 0;
        int count = root.val >= max ? 1 : 0;
        count += countGoodNodes(root.left, Math.max(root.val, max));
        count += countGoodNodes(root.right, Math.max(root.val, max));
        return count;
    }
}
