package com.code.kai.leetcode.curated75.hard.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;

/**
 * @author Mohan Sharma
 */
public class BinaryTreeMaximumPathSum {

    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        int result = helper(root);
        return Math.max(result, maxSum);
    }

    private static int helper(TreeNode root) {
        if (root == null)
            return 0;
        int rootVal = root.val;
        int left = helper(root.left);
        int right = helper(root.right);

        maxSum = Math.max(maxSum, rootVal + left + right);
        int rootChildSum = Math.max(rootVal +  left, rootVal + right);
        rootChildSum = Math.max(rootChildSum, rootVal);
        maxSum = Math.max(maxSum, rootChildSum);
        return rootChildSum;
    }
}
