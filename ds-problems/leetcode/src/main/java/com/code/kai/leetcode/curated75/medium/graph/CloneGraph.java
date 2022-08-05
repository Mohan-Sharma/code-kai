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

    Map<Integer, Node> visitedTracker = new HashMap<>();

    public Node cloneGraph(Node node) {
        //return cloneUsingDFS(node);
        return cloneUsingBFS(node);
    }

    private Node cloneUsingBFS(Node node) {
        if (node == null) {
            return null;
        }
        Map<Integer, Node> visitedTracker = new HashMap<>();
        Queue<Node> bfsQueue = new ArrayDeque<>();
        Node head = new Node(node.val, new ArrayList<>());
        visitedTracker.put(head.val, head);
        bfsQueue.add(node);
        while (!bfsQueue.isEmpty()) {
            Node parent = bfsQueue.poll();
            for (Node childNode : parent.neighbors) {
                if (!visitedTracker.containsKey(childNode.val)) {
                    visitedTracker.put(childNode.val, new Node(childNode.val, new ArrayList<>()));
                    bfsQueue.add(childNode);
                }
                visitedTracker.get(parent.val).neighbors.add(visitedTracker.get(childNode.val));
            }
        }
        return head;
    }

    private Node cloneUsingDFS(Node node) {
        if (node == null)
            return null;
        if (visitedTracker.containsKey(node.val))
            return visitedTracker.get(node.val);
        Node newNode = new Node(node.val, new ArrayList<>());
        visitedTracker.put(newNode.val, newNode);
        for (Node childNode : node.neighbors) {
            newNode.neighbors.add(cloneUsingDFS(childNode));
        }
        return newNode;
    }
}

