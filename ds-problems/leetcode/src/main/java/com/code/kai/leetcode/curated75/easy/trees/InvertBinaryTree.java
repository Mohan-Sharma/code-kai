package com.code.kai.leetcode.curated75.easy.trees;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        return invertTreeBFSRecursively(root);
    }

    private static TreeNode invertTreeBFSRecursively(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTreeBFSRecursively(root.left);
        invertTreeBFSRecursively(root.right);
        return root;
    }

    private static TreeNode invertTreeBFS(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (q.size() > 0) {
            TreeNode node = q.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            if (right != null)
                q.add(right);
            if (left != null)
                q.add(left);
        }
        return root;
    }
}
