package com.code.kai.leetcode.curated75.medium.trees;

import com.code.kai.leetcode.curated75.easy.trees.TreeNode;
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
        if (preStart >= preorder.length || inStart > inEnd)
            return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int indexOfRoot = IntStream.of(inorder).boxed().collect(Collectors.toList()).indexOf(rootVal);
        // pre + 1 the next immediate val in pre sub array end does not matter whereas the inorder arr for left will be between in-start to index - 1
        root.left = buildTreeRecursively(preStart + 1, inStart, indexOfRoot - 1, preorder, inorder);
        // we know where to cut the in-order array, so we know pre array start for right will be (pre + 1)  + (length of left in-arr)
        // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7] e.g. when 20 is the root
        // for left pre will always be 15 means pre + 1, but right pre we know [9, 3, 15] goes to left and [7] goes to right tree so pre = pre + 1 (length of left tree i.e. index - IStart)
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
