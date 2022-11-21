package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
/*
Problem: Very similar to maximum sum of elements of an array without picking adjacent
Solution: First thing comes to mind when working on max sum of sequences is get all sequence then return the one with the max sum?
Finding all sequence? Recursion with pick and not pick logic. Is there any repetition of sub-problem? then memoization. Then
convert to tabulation(bottom up) using base case of recursion and dp array instead of recursion function call
then space optimization by elimination of the dp array
 */
public class HouseRobber {

    public int robTopDownMemoization(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return robTopDownMemoization(nums, dp, nums.length - 1);
    }

    public int robBottomUpTabulation(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // take the current element plus to next to adjacent
            int pick = nums[i];
            if (i > 1)
                pick += dp[i - 2];
            // not taking current element means can take adjacent
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[nums.length - 1];
    }

    public int robSpaceOptimizationOfTabulation(int[] nums) {
        int prevToAdj = 0, adj = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int pick = prevToAdj + nums[i];
            int notPick = adj;
            int max = Math.max(pick, notPick);
            prevToAdj = adj;
            adj = max;
        }
        return adj;
    }

    public int robTopDownMemoization(int[] nums, int[] dp, int index) {
        if (index == 0)
            return nums[index];
        if (index < 0)
            return 0;
        if (dp[index] != -1)
            return dp[index];
        int pick = nums[index] + robTopDownMemoization(nums, dp, index - 2);
        int notPick = robTopDownMemoization(nums, dp, index - 1);
        int max = Math.max(pick, notPick);
        dp[index] = max;
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new HouseRobber().robSpaceOptimizationOfTabulation(new int[]{2, 7, 9, 3, 1}));
    }
}
