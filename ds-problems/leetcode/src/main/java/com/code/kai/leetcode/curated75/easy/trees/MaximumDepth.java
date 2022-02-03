package com.code.kai.leetcode.curated75.easy.trees;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class MaximumDepth {

    public static int maxDepth(TreeNode root) {
        return maxDepthBFS(root);
    }

    private static int maxDepthDFS(TreeNode root, Map<Integer, Integer> dp) {
        if (root == null)
            return 0;
        if (dp.containsKey(root.val)) {
            dp.get(root.val);
        }
        int left = 1  + maxDepthDFS(root.left, dp);
        int right = 1 + maxDepthDFS(root.right, dp);
        int max = Math.max(left, right);
        dp.put(root.val, max);
        return max;
    }

    private static int maxDepthBFS(TreeNode root) {
        int level = 0;
        if (root == null)
            return level;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            for (int i = 0; i < q.size(); i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(9);
        TreeNode three = new TreeNode(20);
        TreeNode four = new TreeNode(15);
        TreeNode five = new TreeNode(7);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;
        System.out.println(maxDepth(one));
    }
}
