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
Dijkstra's Algo:
1. Maintain visited set to avoid loop
2. MinHeap to get min value node to be processed. Initialise it with given node or any node(val = nodeVal, weight=0)
3. Maintain a distance array initially with +inf values
4. While PQ is empty:
    if not visited
        a. poll from pq;
        b. mark visited
        c. iterate adjacent node's
            a. if curNode distance + adjNode Distance < oldDistance update distance array else skip
            b. add to PQ node(val = adjNode, weight=urNode + adjNode Distance)
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adjMatrix = createAdjMatrix(n, times);
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        q.add(new int[] {k , 0});
        Set<Integer> visited = new HashSet<>(n);

        int delay = 0;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int nodeValue = node[0];
            if (!visited.contains(nodeValue)) {
                visited.add(nodeValue);
                delay = Math.max(delay, node[1]);
                for (int[] neighbor : adjMatrix.get(nodeValue)) {
                    if (!visited.contains(neighbor[0]))
                        q.add(new int[] {neighbor[0] , node[1] + neighbor[1]});
                }
            }
        }
        return visited.size() == n ? delay : -1;
    }

    private List<List<int[]>> createAdjMatrix(int n, int[][] times) {
        List<List<int[]>> ajdMatrix = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            ajdMatrix.add(i, new ArrayList<>());
        }

        for (int i = 0; i < times.length; i++) {
            ajdMatrix.get(times[i][0]).add(new int[]{ times[i][1], times[i][2] });
        }
        return ajdMatrix;
    }

    public static void main(String[] args) {
        System.out.println(new NetworkDelayTime().networkDelayTime(new int[][] {{1, 2, 1}}, 2, 1));
    }
}
