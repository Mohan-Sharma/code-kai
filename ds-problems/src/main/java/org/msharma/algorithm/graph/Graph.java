package org.msharma.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class Graph<T> {

    private List<Node<T>> nodes = new ArrayList<>();

    public void addVertex(Node<T> n) {
        if(!nodes.contains(n)) {
            nodes.add(n);
        }
    }

    public void addEdge(Node<T> first , Node<T> second) {
        if(!nodes.contains(first)) {
            nodes.add(first);
        }
        if(!nodes.contains(second)) {
            nodes.add(second);
        }
        first.getChildren().add(second);
    }

    public Node<T> getOrCreateNode(T data) {
        Node<T> node = new Node<>(data);
        if(nodes.contains(node)) {
            node = nodes.get(nodes.indexOf(node));
        } else {
            nodes.add(node);
        }
        return node;
    }
}
