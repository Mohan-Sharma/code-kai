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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode subfour = new TreeNode(4);
        root.left = four;
        root.right = five;
        four.left = one;
        four.right = two;
        subfour.left = new TreeNode(1);
        subfour.right = new TreeNode(2);
        System.out.println(isSubtree(root, subfour));
    }
}
