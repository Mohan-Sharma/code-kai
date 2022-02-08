package com.code.kai.leetcode.curated75.medium.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class TopKFrequent {

    public static int[] topKFrequentUsingBucketSort(int[] nums, int k) {
        Map<Integer, Integer> storage = new HashMap<>();
        for (int num : nums) {
            storage.compute(num, (key,val) -> val == null ? 1 : val + 1);
        }
        int maxCount = nums.length + 1;
        List<Integer>[] bucket = new List[maxCount];
        for (int i = 0; i < maxCount; i++) {
            bucket[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> entry: storage.entrySet()) {
            bucket[entry.getValue()].add(entry.getKey());
        }
        int[] result = new int[k];
        int index = 0;
        for (int i = maxCount - 1; i > 0; i--) {
            if (k == 0)
                break;
            for (int j = 0; j < bucket[i].size(); j++) {
                result[index++] = bucket[i].get(j);
                k--;
                if (k == 0)
                    break;
            }
        }
        return result;
    }

    public static int[] topKFrequentUsingMaxHeap(int[] nums, int k) {
        Map<Integer, Integer> storage = new HashMap<>();
        for (int num : nums) {
            storage.compute(num, (key,val) -> val == null ? 1 : val + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(storage.entrySet());

        int[] result = new int[k];
        int index = 0;
        while (!pq.isEmpty() && k-- > 0) {
            result[index++] = pq.poll().getKey();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequentUsingBucketSort(new int[] {1,1,2,2,3}, 2)));
    }
}
