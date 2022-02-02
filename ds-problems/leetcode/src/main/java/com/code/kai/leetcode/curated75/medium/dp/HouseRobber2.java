package com.code.kai.leetcode.curated75.medium.dp;

/**
 * @author Mohan Sharma
 */
public class HouseRobber2 {

    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(robbingHelper(nums, 0, nums.length - 1), robbingHelper(nums, 1, nums.length));
    }

    private static int robbingHelper(int[] nums, int start, int end) {
        int prevToAdj = 0, adjHouse = 0;
        for (int i = start; i < end; i++) {
            int num = nums[i];
            int temp = Math.max(num + prevToAdj, adjHouse);
            prevToAdj = adjHouse;
            adjHouse = temp;
        }
        return adjHouse;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {1,2,3}));
    }
}
