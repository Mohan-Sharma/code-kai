package com.code.kai.leetcode.curated75.medium.dp;

/**
 * @author Mohan Sharma
 */
public class HouseRobber {

    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length + 1];
        for (int i = nums.length - 1; i >= 0 ; i--) {
            int value = 0;
            for (int j = i + 2; j < nums.length; j++) {
                value = Math.max(value, dp[j]);
            }
            dp[i] = nums[i] + value;
        }
        return Math.max(dp[0], dp[1]);
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,2,3,1}));
    }
}
