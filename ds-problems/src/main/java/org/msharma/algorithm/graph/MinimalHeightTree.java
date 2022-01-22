package org.msharma.algorithm.graph;

import org.msharma.algorithm.trees.Node;

/**
 * @author Mohan Sharma
 */
public class MinimalHeightTree {

    public Node<Integer> getMinimalHeightTree(int[] array) {
        return constructMinimalHeightTree(array, 0, array.length);
    }

    private Node<Integer> constructMinimalHeightTree(int[] array, int start, int end) {
        if(start > end)
            return null;
        int mid = ( start + end ) / 2;
        Node<Integer> parent = new Node<>();
        parent.setData(array[mid]);
        parent.setLeft(constructMinimalHeightTree(array, start, mid - 1));
        parent.setLeft(constructMinimalHeightTree(array, mid + 1, end));
        return parent;
    }

}
