package com.code.kai.leetcode.curated75.hard.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;

/**
 * @author Mohan Sharma
 */
public class SerializeDeserializeBinaryTree {

    private int index = 0;
    public String serialize(TreeNode root) {
        if (root == null)
            return null;
        return getPreOrderString(root);
    }

    private String getPreOrderString(TreeNode root) {
        if (root == null)
            return  "X";
        return root.val + "," + getPreOrderString(root.left) + "," +  getPreOrderString(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null)
            return null;
        return buildTreeRecursively(data.split(","));
    }

    private TreeNode buildTreeRecursively(String[] preorder) {
        if (index == preorder.length )
            return null;
        String rootVal = preorder[index++];
        if (rootVal.equals("X"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        root.left = buildTreeRecursively(preorder);
        root.right = buildTreeRecursively(preorder);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        r.left = new TreeNode(4);
        r.right = new TreeNode(5);
        root.right = r;
        SerializeDeserializeBinaryTree sd = new SerializeDeserializeBinaryTree();
        String serialize = sd.serialize(root);
        System.out.println(serialize);
        System.out.println(sd.deserialize(serialize));
    }
}
