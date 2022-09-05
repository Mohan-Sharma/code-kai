package com.code.kai.leetcode.dojo.easy.heap;

import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
// Basic Idea: Since we need kth largest element let's maintain a min heap of size K such that
// head always gives the Kth largest. So on insertion if size > k remove from head
public class KthLargestInStream {

    private PriorityQueue<Integer> pq;

    private int k;

    public KthLargestInStream(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>(k);
        pq.addAll(IntStream.of(nums).boxed().collect(Collectors.toList()));
    }

    public int add(int val) {
        pq.add(val);
        while (pq.size() > k)
            pq.poll();
        return pq.peek();
    }
    public static void main(String[] args) {
        KthLargestInStream k = new KthLargestInStream(3, new int[] {4, 5, 8, 2});
        System.out.println(k.add(3));
        System.out.println(k.add(5));
        System.out.println(k.add(10));
        System.out.println(k.add(9));
        System.out.println(k.add(4));
    }
}
