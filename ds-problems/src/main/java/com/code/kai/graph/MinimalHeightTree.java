package com.code.kai.graph;

import com.code.kai.trees.Node;

/**
 * @author Mohan Sharma
 */
public class MinimalHeightTree {

    public com.code.kai.trees.Node<Integer> getMinimalHeightTree(int[] array) {
        return constructMinimalHeightTree(array, 0, array.length);
    }

    private com.code.kai.trees.Node<Integer> constructMinimalHeightTree(int[] array, int start, int end) {
        if(start > end)
            return null;
        int mid = ( start + end ) / 2;
        com.code.kai.trees.Node<Integer> parent = new Node<>();
        parent.setData(array[mid]);
        parent.setLeft(constructMinimalHeightTree(array, start, mid - 1));
        parent.setLeft(constructMinimalHeightTree(array, mid + 1, end));
        return parent;
    }

}
