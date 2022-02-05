package com.code.kai.leetcode.curated75.medium.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;

/**
 * @author Mohan Sharma
 */
public class ValidBST {

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if (root == null)
            return true;
        return root.val > minValue && root.val < maxValue
                && isValidBST(root.left,minValue, root.val)
                && isValidBST(root.right, root.val, maxValue);

    }

    public static boolean isValidBSTBruteForce(TreeNode root) {
        if (root == null)
            return true;
        return  isLeftSubTreeLess(root.left, root.val)
                && isRightSubTreeGreater(root.right, root.val)
                && isValidBSTBruteForce(root.left)
                && isValidBSTBruteForce(root.right);
    }

    private static boolean isRightSubTreeGreater(TreeNode right, int val) {
        if (right == null)
            return true;
        return right.val > val && isRightSubTreeGreater(right.left, val) && isRightSubTreeGreater(right.right, val);
    }

    private static boolean isLeftSubTreeLess(TreeNode left, int val) {
        if (left == null)
            return true;
        return left.val < val && isLeftSubTreeLess(left.left, val) && isLeftSubTreeLess(left.right, val);
    }
}
