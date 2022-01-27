package com.code.kai.leetcode.curated75.easy.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> storage = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            if (storage.containsKey(target - num)) {
                return new int[] {storage.get(target - num), i};
            }
            storage.put(num, i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 18)));
    }
}
