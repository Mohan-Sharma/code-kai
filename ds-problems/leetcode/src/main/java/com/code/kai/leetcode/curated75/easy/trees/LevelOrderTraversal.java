package com.code.kai.leetcode.curated75.easy.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class LevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) {
            return List.of();
        }
        List<List<Integer>> result = new ArrayList<>();
        final Queue<TreeNode> q = new ArrayDeque<>();
         q.add(root);
        while (!q.isEmpty()) {
            List<Integer> levelNodes = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                levelNodes.add(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            result.add(levelNodes);
        }
        return result;
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
        System.out.println(levelOrder(one));
    }
}
