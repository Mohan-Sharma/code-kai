package com.code.kai.leetcode.dojo.medium.heap;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : tasks) {
            freq.compute(ch, (k,v) -> v == null ? 1 : v + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(freq.size(), Comparator.reverseOrder());
        pq.addAll(freq.values());
        Queue<int[]> q = new ArrayDeque<>();

        int uot = 0;
        while (!pq.isEmpty() || !q.isEmpty()) {
            uot++;
            if (!pq.isEmpty()) {
                int maxOccurringTask = pq.poll() - 1;
                if (maxOccurringTask > 0)
                    q.add(new int[]{maxOccurringTask, uot + n});
            }

            if (!q.isEmpty() && q.peek()[1] == uot) {
                int[] task = q.poll();
                pq.add(task[0]);
            }
        }
        return uot;
    }

    public static void main(String[] args) {
        System.out.println(new TaskScheduler().leastInterval(new char[] {'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
    }
}
