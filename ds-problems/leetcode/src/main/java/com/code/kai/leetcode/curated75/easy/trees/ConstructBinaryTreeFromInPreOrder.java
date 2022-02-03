package com.code.kai.leetcode.curated75.easy.trees;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class ConstructBinaryTreeFromInPreOrder {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeRecursively(0, 0, inorder.length-1, preorder, inorder);
    }

    private static TreeNode buildTreeRecursively(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart>preorder.length || inStart > inEnd)
            return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int indexOfRoot = IntStream.of(inorder).boxed().collect(Collectors.toList()).indexOf(rootVal);
        root.left = buildTreeRecursively(preStart + 1, inStart, indexOfRoot - 1, preorder, inorder);
        root.right = buildTreeRecursively(preStart + indexOfRoot - inStart + 1,  indexOfRoot + 1, inEnd, preorder, inorder);
        return root;
    }

    private static TreeNode buildTreeRecursively(int[] preorder, int[] inorder) {
        if (preorder.length == 0 && inorder.length == 0) return null;
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);
        int indexOfRoot = IntStream.of(inorder).boxed().collect(Collectors.toList()).indexOf(rootVal);
        int rightLength = preorder.length - (indexOfRoot + 1);
        int[] leftPre = new int[indexOfRoot];
        int[] rightPre = new int[rightLength];
        int[] leftIn = new int[indexOfRoot];
        int[] rightIn = new int[rightLength];//since both array are same length we can take anyone to find len
        System.arraycopy(preorder, 1, leftPre, 0, indexOfRoot);
        System.arraycopy(preorder, indexOfRoot + 1, rightPre, 0, rightLength);
        System.arraycopy(inorder, 0, leftIn, 0, indexOfRoot);
        System.arraycopy(inorder, indexOfRoot + 1, rightIn, 0, rightLength);
        root.left = buildTreeRecursively(leftPre, leftIn);
        root.right = buildTreeRecursively(rightPre, rightIn);
        return root;
    }

    public static void main(String[] args) {
        System.out.println(buildTree(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7}));
    }
}
