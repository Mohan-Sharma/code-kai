package com.code.kai.leetcode.dojo.medium.graph;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class RedundantConnection {

    // Problem: There are n vertices and n nodes means it will always be cyclic. Since by removing the extra edge
    // it will become a tree in other words we can start constructing a tree with the edges provided. It is a problem
    // of union find, union to aggregate the nodes by the edges and use find to check if two nodes have same parent
    // If yes, it is the extra edge return it
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = IntStream.range(0, edges.length + 1).toArray();
        int[] rank = IntStream.range(0, edges.length + 1).map(i -> 1).toArray();
        for (int[] edge : edges) {
            if (!unionFind(edge[0], edge[1], rank, parent)) {
                return edge;
            }
        }
        System.out.println(Arrays.toString(parent));
        return new int[]{};
    }

    private boolean unionFind(int first, int second, int[] rank, int[] parent) {
        int p1 = findParent(first, parent);
        int p2 = findParent(second, parent);
        if (p1 == p2) {
            // already part of same group or already connected, so it is redundant and if introduced again would create a cycle
            return false;
        }
        if (rank[p1] >= rank[p2]) {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        } else {
            parent[p1] = p2;
            rank[p2] += rank[p1];
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

    public static void main(String[] args) {
       int[] outcast = new RedundantConnection().findRedundantConnection(new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}});
        System.out.println(Arrays.toString(outcast));
    }
}
