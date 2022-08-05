package com.code.kai.leetcode.curated75.hard.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;

/**
 * @author Mohan Sharma
 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        int[] result = new int[] {-1001}; // -1000 <= Node.val <= 1000
        helper(root, result);
        return result[0];
    }

    // for a root, the max sum can be either max(rv + ls + rs, max(rv, max(ls + rv, rs + rv)))
    // but since the sum should be of a path valid path sum will be max(rv, max(rv + ls, rv + rs)) to be returned from any root node
    // r + ls + rs cannot be a path to the previous root
    private int helper(TreeNode root, int[] result) {
        if (root == null)
            return 0;
        int rv = root.val;
        int ls = helper(root.left, result);
        int rs = helper(root.right, result);
        int cRS = Math.max(rv, Math.max(ls+rv, rs+rv));
        result[0] = Math.max(result[0], Math.max(rv + ls + rs, cRS));
        return cRS;
    }

    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSumWithGlobalVar(TreeNode root) {
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
