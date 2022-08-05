package com.code.kai.leetcode.curated75.medium.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class TopKFrequent {

    // If we have the frequency map, one idea is maybe create max heap to store the most frequent element at the top
    // Using similar idea can we use the most frequent element count as may be the index of an array, so even
    // if all elements are similar [1, 1, 1] most frequent count will be store at nums.length + 1, [0, 0, 0, 3]
    // e.g. [1, 2, 2, 3, 3, 3, 4, 4, 4] can be store in a bucket like at 3rd index store 3 and 4 at 2nd index store 2 at 1st index store 1 etc
    // [0, 1, 2, [3, 4], 0, 0, 0, 0, 0,0]
    public static int[] topKFrequentUsingBucketSort(int[] nums, int k) {
        int[] result = new int[k];
        if (k == 0)
            return result;
        // Step-1: create and populate frequency count dictionary.
        // Don't get intimidated by java 8 functions all I am doing is iterating over nums and
        // populate freqCount map with the condition if val already exists increment by 1 else put 1.
        /**
         * Equivalent simplified version
         * for (int num: nums) {
         *     if (freqCount.containsKey(num)) {
         *         freqCount.put(num, freqCount.get(num) + 1);
         *     } else {
         *         freqCount.put(num, 1);
         *     }
         * }
         */
        Map<Integer, Integer> freqCount = new HashMap<>();
        Arrays.stream(nums).forEach(num -> freqCount.compute(num, (key, val) -> val == null ? 1 : val + 1));
        // Step-2: Create bucket array and populate it
        // But why a list of bucket array suppose our nums=[1 1 2 2] and K is 2, freqCount = [(1:2), (2:2)]
        // If you check at 2nd index there are 2 elements now 1 and 2, there can be more as well so use a list
        // Don't get intimidated by java 8 functions all I am doing first initializing
        // bucket array with empty list at each index then populating bucket array in such a way that
        // bucket[2] = [1, 2]
        /**
         * Equivalent simplified way
         * for (int i = 0; i < bucket.length; i++) {
         *     bucket[i] = new ArrayList<>();
         * }
         * for (Map.Entry<Integer, Integer> entry: freqCount.entrySet()) {
         *     bucket[entry.getValue()].add(entry.getKey());
         * }
         */
        List<Integer>[] bucket = new List[nums.length + 1];
        IntStream.range(0 , bucket.length).forEach(i -> bucket[i] = new ArrayList<>());
        freqCount.forEach((key, val) -> bucket[val].add(key));

        // Step-3: Iterate over the bucket array from the end, populate result until k > 0
        int index = 0;
        for (int i = nums.length; i >= 0; i--) {
            if (k == 0)
                break;
            List<Integer> list = bucket[i];
            for (int j = 0; j < list.size(); j++) {
                result[index++] = list.get(j);
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
        System.out.println(Arrays.toString(topKFrequentUsingMaxHeap(new int[] {1,1,2,2,3,3}, 2)));
    }
}
