package com.code.kai.leetcode.curated75.medium.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class SubArraySumK {

    //formula runningsum - someprefixarraysum = k
    //[1, -1, 1, 1, 1] running sum at i=4 is 3 so 3 - prefixsum = 3 or 3 - 3 = prefixsum
    // 3-3 = 0 so find all subarrays with prefixsum 0
    // or if you remove i.e. [], [1, -1] from runningsum arr [1, -1, 1, 1, 1] you will get [1, -1, 1, 1, 1], [1, 1, 1] as result
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int count = 0;
        int runningSum = 0;
        for (int num: nums) {
            runningSum += num;
            count += prefixSum.getOrDefault(runningSum - k, 0);
            prefixSum.compute(runningSum, (oldKey,oldValue) -> (oldValue == null) ? 1 : oldValue + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[] {1, 2, 3}, 3));
    }
}
