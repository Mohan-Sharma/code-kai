package com.code.kai.leetcode.curated75.medium.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class KthSmallestInBST {

    private static int result;

    public static int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return result;
    }

    private static int helper(TreeNode root, int k) {
        if (root == null)
            return k;
        if (k == 0)
            return k;
        k = helper(root.left, k);
        k -= 1;
        if (k == 0) {
            result = root.val;
            return k;
        }
        return helper(root.right, k);
    }

    public static int kthSmallestUsingSpace(TreeNode root, int k) {
        if (root == null)
            return -1;
        List<String> inOrderList = new ArrayList<>();
        getInOrderList(root, inOrderList);
        if (k <= inOrderList.size()) {
            return Integer.parseInt(inOrderList.get(k - 1));
        } else return -1;
    }

    private static void getInOrderList(TreeNode root, List<String> inOrderList) {
        if (root == null)
            return;
        getInOrderList(root.left, inOrderList);
        inOrderList.add(String.valueOf(root.val));
        getInOrderList(root.right, inOrderList);
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(3);
        r.right = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        one.right = new TreeNode(2);
        r.left = one;
        System.out.println(kthSmallest(r, 4));
    }
}
