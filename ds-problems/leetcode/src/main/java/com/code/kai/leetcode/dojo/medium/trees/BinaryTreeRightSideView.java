package com.code.kai.leetcode.dojo.medium.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class BinaryTreeRightSideView {

    // we can get no the elems of each level, take the last elem of each level
    // Add the first root to q, the size of the q is 1 and level is 1, process this level
    // While processing we add the children of that level to q, once complete level is process proceed to next
    public List<Integer> rightSideViewBFS(TreeNode root) {
        if (root == null)
            return List.of();
        final Queue<TreeNode> q = new ArrayDeque<>();
        final List<Integer> list = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode n = q.poll();
                if (size == 0)
                    list.add(n.val);
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
        }
        return list;
    }

    //At level 0, size is 0 add the element then move to right and increment level, if right not present take left
    public List<Integer> rightSideViewDFS(TreeNode root) {
        if (root == null)
            return List.of();
        final List<Integer> list = new ArrayList<>();
        generateSideViewDFS(root, list, 0);
        return list;
    }

    private void generateSideViewDFS(TreeNode root, List<Integer> list, int level) {
        if (root == null)
            return;
        if (level == list.size())
            list.add(root.val);
        generateSideViewDFS(root.right, list, level + 1);
        generateSideViewDFS(root.left, list, level + 1);
    }
}
