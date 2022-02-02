package com.code.kai.leetcode.curated75.easy.trees;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Mohan Sharma
 */
@NoArgsConstructor
@AllArgsConstructor
//public fields to simulate LC tree
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
