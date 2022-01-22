package org.msharma.algorithm.graph;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class CheckIfPathExistsBetweenNodes {

    public boolean checkIfPathExists(Node<Integer> source, Node<Integer> destination) {
        if(source.equals(destination))
            return true;
        LinkedList<Node<Integer>> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.removeFirst();
            if(Objects.nonNull(node)) {
                node.setVisited(true);
                for (Node<Integer> child : node.getChildren()) {
                    if (!child.isVisited()) {
                        if (child.equals(destination))
                            return true;
                        else
                            queue.add(child);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        Node<Integer> zero = graph.getOrCreateNode(0);
        Node<Integer> one = graph.getOrCreateNode(1);
        Node<Integer> two = graph.getOrCreateNode(2);
        Node<Integer> three = graph.getOrCreateNode(3);

        graph.addVertex(graph.getOrCreateNode(3));
        graph.addEdge(zero, one);
        graph.addEdge(zero, two);
        graph.addEdge(one, two);
        graph.addEdge(two, zero);
        graph.addEdge(two, three);
        graph.addEdge(three, three);

        CheckIfPathExistsBetweenNodes obj = new CheckIfPathExistsBetweenNodes();
        System.out.println("Path Exists: " + obj.checkIfPathExists(zero, one));
    }
}
