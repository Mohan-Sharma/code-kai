package com.code.kai.leetcode.curated75.medium.dp;

/**
 * @author Mohan Sharma
 */
public class HouseRobber2 {

    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(robbingHelper(nums, 0, nums.length - 2), robbingHelper(nums, 1, nums.length - 1));
    }

    private static int robbingHelper(int[] nums, int start, int end) {
        int prevToPrev = 0, prev = nums[start];
        for (int i = start + 1; i <= end; i++) {
            int notTake = prev;
            int take = nums[i] + (i > 1 ? prevToPrev : 0);
            prevToPrev = prev;
            prev = Math.max(take, notTake);;
        }
        return prev;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {1,2,3,1}));
    }
}
