package com.code.kai.leetcode.dojo.easy.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        IntStream.of(stones).forEach(pq::add);
        while (pq.size() > 1) {
            int y = pq.poll();
            int x = pq.poll();
            if (y > x)
                pq.add(y-x);
        }
        return pq.isEmpty() ? 0 : pq.peek();
    }
}
