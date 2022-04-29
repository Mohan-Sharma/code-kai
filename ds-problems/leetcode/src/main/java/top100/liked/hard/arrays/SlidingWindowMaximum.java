package top100.liked.hard.arrays;

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
            while (!q.isEmpty() && q.peek() < end - k + 1) {
                q.poll();
            }
            while (!q.isEmpty() && nums[q.peekLast()] < nums[end]) {
                q.pollLast();
            }
            q.add(end);
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
