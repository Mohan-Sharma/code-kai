package com.code.kai.leetcode.curated75.hard.heap;

import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class MedianFinder {

    private final PriorityQueue<Integer> small;
    private final PriorityQueue<Integer> large;

    public MedianFinder() {
        small = new PriorityQueue<>((a, b) -> b - a);
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (large.isEmpty()) {
            small.add(num);
        } else {
            int maxSmall = small.peek();
            int minLarge = large.peek();
            if (num > maxSmall && num > minLarge)
                large.add(num);
            else
                small.add(num);
        }
        balancePQ(small, large);
    }

    private void balancePQ(PriorityQueue<Integer> small, PriorityQueue<Integer> large) {
        int smallSize = small.size();
        int largeSize = large.size();

        if (smallSize > largeSize && smallSize - largeSize > 1) {
            int maxSmall = small.poll();
            large.add(maxSmall);
        } else if (smallSize < largeSize && largeSize - smallSize > 1) {
            int minLarge = large.poll();
            small.add(minLarge);
        }
    }

    public double findMedian() {
        int smallSize = small.size();
        int largeSize = large.size();

        if (smallSize - largeSize == 0 && !small.isEmpty())
            return (double)(small.peek() + large.peek())/2;
        else if (smallSize > largeSize)
            return small.peek();
        else if (largeSize > smallSize)
            return large.peek();
        return 0d;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(3);
        medianFinder.addNum(2);
        medianFinder.addNum(7);
        medianFinder.addNum(4);
    }
}
