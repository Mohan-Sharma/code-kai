package org.msharma.algorithm.trees;

import java.util.Objects;

/**
 * @author Mohan Sharma
 */
public class InorderSuccessor  {

    private class Node<T extends Comparable> {
        T data;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
    }

    public <T extends Comparable> Node<T> getInorderSuccessor(Node<T> node) {
        if(Objects.isNull(node))
            return null;
        if(node.right != null) {
            return getLeftMostNode(node.right);
        }

        Node<T> current = node;
        Node<T> parent = node.parent;
        while(Objects.nonNull(parent) && Objects.nonNull(parent.left) && !parent.left.data.equals(current.data)) {
            current = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private <T extends Comparable> Node<T> getLeftMostNode(Node<T> node) {
        if(Objects.isNull(node))
            return null;
        while (Objects.nonNull(node.left)) {
            node = node.left;
        }
        return node;
    }

    public <T extends Comparable> Node<T> getInorderSuccessorWithoutParent(Node<T> node, Node<T> root) {
        if(Objects.isNull(node))
            return null;
        if(node.right != null) {
            return getLeftMostNode(node.right);
        }

        Node<T> ancestor = root;
        Node<T> successor = null;
        while(!node.data.equals(ancestor.data)) {
           if(node.data.compareTo(ancestor.data) < 1) {
               successor = ancestor;
               ancestor = ancestor.left;
           } else {
               ancestor = ancestor.right;
           }
        }
        return successor;
    }
}
