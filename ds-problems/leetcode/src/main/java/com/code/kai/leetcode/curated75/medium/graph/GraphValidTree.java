package com.code.kai.leetcode.curated75.medium.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohan Sharma
 */
// Algo: all nodes are connected and non-cyclic
public class GraphValidTree {

    // easy way to check non-cyclic is no of nodes always equals no of edges + 1
    // and all nodes connected, if all nodes are connected no of visited nodes will be equal to n
    public boolean validTreeEasyWay(int n, int[][] edges) {
        if (n == 1)
            return true;
        if (n > 1 && edges.length == 0)
            return false;
        Map<Integer, List<Integer>> adjacencyList = adjacencyList(edges);
        Set<Integer> visited = new HashSet<>();
        // isNonCyclic && all nodes connected(if all nodes are connected no of visited nodes will be equal to n)
        // always passing 0 as we know 0 is the least node and will always be present since nodes will always be 0 to n-1
        populateVisitedNodes(0, visited, adjacencyList);
        return edges.length + 1 == n
                && n == visited.size();
    }

    private void populateVisitedNodes(int node, Set<Integer> visited, Map<Integer, List<Integer>> adjacencyList) {
        visited.add(node);
        for (int neighbor : adjacencyList.get(node)) {
            // basic idea is just to find all connected nodes, if already visited means no point to visit again.
            // If a node is not connected to any node it won't be visited in any way
            if (visited.contains(neighbor))
                continue;
            populateVisitedNodes(neighbor, visited, adjacencyList);
        }
    }

    public static boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = adjacencyList(edges);
        Set<Integer> visited = new HashSet<>();
        // isNonCyclic && all nodes connected(if all nodes are connected no of visited nodes will be equal to n)
        return isNonCyclic(0, -1, visited, adjacencyList) && n == visited.size();
    }

    private static boolean isNonCyclic(int current, int parent, Set<Integer> visited, Map<Integer, List<Integer>> adjacencyList) {
        visited.add(current);
        for (int neighbor : adjacencyList.get(current)) {
            if (neighbor == parent)
                continue;
            if (visited.contains(neighbor))
                return false;
            if (!isNonCyclic(neighbor, current, visited, adjacencyList))
                return false;
        }
        return true;
    }

    private static Map<Integer, List<Integer>> adjacencyList(int[][] edges) {
        Map<Integer, List<Integer>> space = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> directedEdges = space.getOrDefault(edge[0], new ArrayList<>());
            directedEdges.add(edge[1]);
            space.put(edge[0], directedEdges);

            directedEdges = space.getOrDefault(edge[1], new ArrayList<>());
            directedEdges.add(edge[0]);
            space.put(edge[1], directedEdges);
        }
        return space;
    }

    public static void main(String[] args) {
        System.out.println(new GraphValidTree(). validTreeEasyWay(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
    }
}
