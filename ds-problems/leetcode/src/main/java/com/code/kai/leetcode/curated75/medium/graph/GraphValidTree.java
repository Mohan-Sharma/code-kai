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
// Cyclic can tested using 3 methods -
// 1. If no of nodes = no of edges + 1, it's cyclic 2. Using Union Find 3. Using visited set
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


    // even in case of cyclic graph we will get visited size equals no of nodes b/c the idea here
    // is to check if all nodes can be visited. If there is unconnected node then visited size
    // will be less
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


    // at first assume we have all disconnected elements meaning we have n components. Now using the edge join the components using union find
    // if already joined means have same parent already so cyclic. Whenever you join decrement component e.g. we have 1, 2, 3 initially we have 3 components, when 1 -> 2
    // joined there will be 2 components one with 1->2 and other 3. So at the end if not cyclic return components == 1
    public boolean validTreeUnionFind(int n, int[][] edges) {
        int[] rank = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = 1;
            parent[i] = i;
        }
        int connectedComponents = n;
        for (int[] edge : edges) {
            if (!unionFind(edge[0], edge[1], parent, rank)) {
                return false;
            } else
                connectedComponents--;
        }
        return connectedComponents == 1;
    }

    private boolean unionFind(int one, int two, int[] parent, int[] rank) {
        int parentOne = findParent(one, parent);
        int parentTwo = findParent(two, parent);
        if (parentOne == parentTwo)
            return false;
        if (rank[parentTwo] <= rank[parentOne]) {
            parent[parentTwo] = parentOne;
            rank[parentOne] += rank[parentTwo];
        } else {
            parent[parentOne] = parentTwo;
            rank[parentTwo] += rank[parentOne];
        }
        return true;
    }

    private int findParent(int node, int[] parent) {
        int p = node;
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // since graph is undirected we are creating list from both end b/c [0, 1] and [1, 0]
    // in this case is not cyclic unless we are give edges as [[0, 1][1, 0]] b/c here n = 2
    // and edges is 2 so 2+1 != 2 hence cyclic
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

    // Idea is to check that the graph is acyclic plus populate the visited set.
    // We know it is acyclic when a visited node does not come again and if visited size == n, means all
    // nodes can be reached hence connected so it is a tree.
    /*
             2
             |
        0 -- 1 -- 3
             |
             4
     */
    // To check acyclic? We can start with zero node and assume its parent is -1. Next do a dfs
    // for all adjacent nodes e.g. for node 1 parent is 0 so for all 1's adjacent node parent will still be 0
    // now in case of undirected graph 0's adj is 1 and 1's adj is 0 so how do we ignore this?
    // Now in the current recursion parent is 0 and current node is 1 so for all adjacent node of 1 parent is 0
    // so if parent == neighbor we continue assuming its due to undirected graph thing(we came down from this branch)
    // but if neighbor contains in visited set(could have reached from some other branch) means there is a cycle
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        // isNonCyclic && all nodes connected(if all nodes are connected no of visited nodes will be equal to n)
        return isNonCyclic(0, -1, visited, adjacencyList) && n == visited.size();
    }

    private boolean isNonCyclic(int current, int parent, Set<Integer> visited, List<List<Integer>> adjacencyList) {
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

    public static void main(String[] args) {
        System.out.println(new GraphValidTree(). validTree(5, new int[][]{{0, 1}, {1, 2}, {1, 3}, {1, 4}}));
    }
}
