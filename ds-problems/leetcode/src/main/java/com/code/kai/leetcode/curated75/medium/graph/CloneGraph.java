package com.code.kai.leetcode.curated75.medium.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class CloneGraph {

    public Node cloneGraph(Node node) {
        final Map<Integer, Node> visited = new HashMap<>();
        //return cloneUsingDFS(node, visited);
        return cloneUsingBFS(node, visited);
    }

    private Node cloneUsingBFS(Node node, Map<Integer, Node> visited) {
        if (node == null) {
            return null;
        }
        Queue<Node> bfsQueue = new ArrayDeque<>();
        Node head = new Node(node.val, new ArrayList<>());
        visited.put(head.val, head);
        bfsQueue.add(node);
        while (!bfsQueue.isEmpty()) {
            Node parent = bfsQueue.poll();
            for (Node childNode : parent.neighbors) {
                if (!visited.containsKey(childNode.val)) {
                    visited.put(childNode.val, new Node(childNode.val, new ArrayList<>()));
                    bfsQueue.add(childNode);
                }
                visited.get(parent.val).neighbors.add(visited.get(childNode.val));
            }
        }
        return head;
    }

    private Node cloneUsingDFS(Node node, Map<Integer, Node> visited) {
        if (node == null)
            return null;
        if (visited.containsKey(node.val))
            return visited.get(node.val);
        Node newNode = new Node(node.val, new ArrayList<>());
        visited.put(newNode.val, newNode);
        for (Node childNode : node.neighbors) {
            newNode.neighbors.add(cloneUsingDFS(childNode, visited));
        }
        return newNode;
    }
}

