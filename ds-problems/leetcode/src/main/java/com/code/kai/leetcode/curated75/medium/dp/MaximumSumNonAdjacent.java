package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class MaximumSumNonAdjacent {

    public static int maximumNonAdjacentSum(List<Integer> nums) {
        int[] dp = new int[nums.size()];
        Arrays.fill(dp , -1);
        return memoization(nums.size() - 1, nums, dp);
    }

    private static int bottomUP(int i, List<Integer> nums) {
        if (i == 0)
            return nums.get(0);
        int notTake = bottomUP(i - 1, nums);
        int take = Integer.MIN_VALUE;
        if (i > 1)
            take = nums.get(i) + bottomUP(i - 2, nums);
        return Math.max(take, notTake);
    }

    private static int memoization(int i, List<Integer> nums, int[] dp) {
        if (i == 0)
            return nums.get(0);
        if (dp[i] != -1)
            return dp[i];
        int notTake = memoization(i - 1, nums, dp);
        int take = nums.get(i) + (i > 1 ? memoization(i - 2, nums, dp) : 0);
        return dp[i] = Math.max(take, notTake);
    }

    private static int tabulation(List<Integer> nums) {
        int size = nums.size();
        int[] dp = new int[size];
        dp[0] = nums.get(0);
        for (int i = 1; i < size; i++) {
            int notTake = dp[i - 1];
            int take = nums.get(i) + (i > 1 ? dp[i - 2] : 0);
            dp[i] = Math.max(take, notTake);
        }
        return dp[size -1];
    }

    private static int tabulationOptimized(List<Integer> nums) {
        int size = nums.size();
        int prev = nums.get(0), prevToPrev = 0;
        for (int i = 1; i < size; i++) {
            int notTake = prev;
            int take = nums.get(i) + (i > 1 ? prevToPrev : 0);
            prevToPrev = prev;
            prev = Math.max(take, notTake);
        }
        return prev;
    }
}
