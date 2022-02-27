package com.code.kai.leetcode.curated75.medium.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class ConnectedComponentsGraph {

    // Why union find: initially we have n node, and we need to check how many are connected
    // which means we need to do union of nodes but union can be done on some criteria that's
    // where find comes in to check the parent of the 2 nodes on which union will be done
    // if parent of the 2 nodes are not same means they are not connected yet and hence union
    // can be done based on rank of the parent
    public int countComponentsUsingUnionFind(int n, int[][] edges) {
        int[] parent = IntStream.range(0, n).toArray();
        int[] rank = IntStream.range(0, n).map(i -> 1).toArray();
        int count = n;
        for (int[] edge : edges) {
            if (union(edge[0], edge[1], parent, rank)) {
                count--;
            }
        }
        return count;
    }

    private boolean union(int one, int two, int[] parent, int[] rank) {
        int parentOne = findParent(one, parent);
        int parentTwo = findParent(two, parent);
        if (parentOne == parentTwo)
            return false;
        if (rank[parentTwo] > rank[parentOne]) {
            parent[parentOne] = parentTwo;
            rank[parentTwo] += rank[parentOne];
        } else {
            parent[parentTwo] = parentOne;
            rank[parentOne] += rank[parentTwo];
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

    // Algo: maintain visit boolean array initially none of the nodes will be visited.
    // When 0 is processed, 0th index in visit arr is false so increment count.
    // When 0th node been visited all connected nodes will be marked as visited true.
    // So the next iteration will process only if unconnected node is found
    public int countComponentsUsingVisitSet(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = adjacencyList(edges);
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                visitNeighbors(i, visited, adjacencyList);
            }
        }
        return count;
    }

    private void visitNeighbors(int node, boolean[] visited, Map<Integer, List<Integer>> adjacencyList) {
        visited[node] = true;
        for (int child : adjacencyList.get(node)) {
            if (!visited[child]) {
                visitNeighbors(child, visited, adjacencyList);
            }
        }
    }


    private Map<Integer, List<Integer>> adjacencyList(int[][] edges) {
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
        System.out.println(new ConnectedComponentsGraph().countComponentsUsingUnionFind(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
    }
}
