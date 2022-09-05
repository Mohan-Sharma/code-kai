package com.code.kai.leetcode.dojo.hard.arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author Mohan Sharma
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int end = 0, index = 0;
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<>();

        while (end < nums.length) {
            // remove from head the elements where size of queue > k
            while (!q.isEmpty() && q.peek() < end - k + 1) {
                q.poll();
            }
            // at any point of time maintain a decreasing order of at max k elements
            while (!q.isEmpty() && nums[q.peekLast()] < nums[end]) {
                q.pollLast();
            }
            q.add(end);
            // since the head contains the max get the head but dont remove since it might
            // be eligible for next window ok k as well where as if size of queue exceeds k
            // above loop will remove it anyway
            if (end >= k - 1) {
                result[index++] = nums[q.peek()];
            }
            end++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = new SlidingWindowMaximum().maxSlidingWindow(new int[] {1,3,1,2,0,5}, 3);
        System.out.println(Arrays.toString(result));
    }
}
