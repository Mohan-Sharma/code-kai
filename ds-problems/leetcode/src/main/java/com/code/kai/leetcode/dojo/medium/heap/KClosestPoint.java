package com.code.kai.leetcode.dojo.medium.heap;

import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
// Basic Idea: since we need kth smallest maintain max heap of size K. Comparator based on the euclidean distance,
// hence smallest will be at the tail. On insertion if size > k remove large ones from head.
public class KClosestPoint {

    public int[][] kClosest(int[][] points, int k) {
        final PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > k)
                pq.poll();

        }
        int[][] result = new int[k][2];
        while (k-- > 0) {
            result[k] = pq.poll();
        }
        return result;
    }
}
