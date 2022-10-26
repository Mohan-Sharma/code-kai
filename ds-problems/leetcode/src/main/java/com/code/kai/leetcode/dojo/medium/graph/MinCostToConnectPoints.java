package com.code.kai.leetcode.dojo.medium.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Mohan Sharma
 */
/*
Problem: Given every node is connected to every other node. Convert it to a minimum spanning tree.
Minimum spanning tree: Given weighted graph, when we can convert the graph into a tree such that there are n nodes
and n-1 edges and every node is reachable by any other node(all nodes are connected) and the cost of edge weight(sum of all weighted edges) is minimal it is called MST
Solution: Prims Algo

Minimum Spanning Tree vs Single Source Shortest Path: refer - MSTvSSSP.png
 */
public class MinCostToConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        int cost = 0;
        int nodes = points.length;
        List<List<int[]>> adjMatrix = createAdjMatrix(nodes, points);

        Set<Integer> visited = new HashSet<>(nodes);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {0, 0});
        while (visited.size() < nodes) {
            int[] node = pq.poll();
            int nodeValue = node[0];
            if (!visited.contains(nodeValue)) {
                int weight = node[1];
                visited.add(nodeValue);
                cost += weight;
                for (int[] neighbor : adjMatrix.get(nodeValue)) {
                    nodeValue = neighbor[0];
                    if (!visited.contains(nodeValue)) {
                        pq.add(neighbor);
                    }
                }
            }
        }
        return cost;
    }

    private List<List<int[]>> createAdjMatrix(int nodes, int[][] points) {
        List<List<int[]>> adjMatrix = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adjMatrix.add(i, new ArrayList<>());
        }

        for (int i = 0; i < nodes; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j <nodes; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int cost = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                adjMatrix.get(i).add(new int[] {j, cost});
                adjMatrix.get(j).add(new int[] {i, cost});
            }
        }
        return adjMatrix;
    }

    public static void main(String[] args) {
        System.out.println(new MinCostToConnectPoints().minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
    }
}
