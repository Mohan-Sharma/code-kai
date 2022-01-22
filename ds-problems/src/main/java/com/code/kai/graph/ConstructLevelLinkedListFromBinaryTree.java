package com.code.kai.graph;

import com.code.kai.trees.BinarySearchTree;
import com.code.kai.trees.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mohan Sharma
 */
public class ConstructLevelLinkedListFromBinaryTree {

    public List<LinkedList<com.code.kai.trees.Node<Integer>>> getArrayOfLinkedListFromEachLevelOfTree(com.code.kai.trees.Node<Integer> root) {
        List<LinkedList<com.code.kai.trees.Node<Integer>>> array = new ArrayList<>();
        LinkedList<com.code.kai.trees.Node<Integer>> current = new LinkedList<>();
        current.add(root);

        while(!current.isEmpty()) {
            array.add(current);

            LinkedList<com.code.kai.trees.Node<Integer>> parents  = current;
            current = new LinkedList<>();

            for(com.code.kai.trees.Node<Integer> parent : parents) {
                if(Objects.nonNull(parent.getLeft()))
                    current.add(parent.getLeft());
                if(Objects.nonNull(parent.getRight()))
                    current.add(parent.getRight());
            }
        }
        return array;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(15, bst.getRoot());
        bst.insert(12, bst.getRoot());
        bst.insert(18, bst.getRoot());
        bst.insert(9, bst.getRoot());
        bst.insert(10, bst.getRoot());
        bst.insert(16, bst.getRoot());
        bst.insert(17, bst.getRoot());
        bst.insert(1, bst.getRoot());
        bst.insert(22, bst.getRoot());
        bst.insert(26, bst.getRoot());
        bst.insert(21, bst.getRoot());

        ConstructLevelLinkedListFromBinaryTree obj = new ConstructLevelLinkedListFromBinaryTree();
        List<LinkedList<Node<Integer>>> array = obj.getArrayOfLinkedListFromEachLevelOfTree(bst.getRoot());
        System.out.println(array);
    }
}
