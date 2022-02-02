package com.code.kai.leetcode.curated75.medium.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class CombinationSum {
    public static int combinationSum4(int[] nums, int target) {
        return combinationSumBottomUp(nums, target, new HashMap<>());
    }

    private static int combinationSumBottomUp(int[] nums, int target, Map<Integer, Integer> dp ) {
        dp.put(0, 1);
        for (int i = 1; i < target + 1; i++) {
            int value = 0;
            for (int num : nums) {
                final int key = i - num;
                value  += dp.getOrDefault(key, 0);
            }
            dp.put(i, value);
        }
        return dp.get(target);
    }

    private static int combinationSumTopDown(int[] nums, int target, Map<Integer, Integer> dp ) {
        int count = 0;
        if (target == 0) {
            return 1;
        }
        if (dp.containsKey(target))
            return dp.get(target);
        for (int num : nums) {
            if (target - num >= 0) {
                count += combinationSumTopDown(nums, target-num, dp);
                dp.put(target, count);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1,2,3}, 4));
    }
}
