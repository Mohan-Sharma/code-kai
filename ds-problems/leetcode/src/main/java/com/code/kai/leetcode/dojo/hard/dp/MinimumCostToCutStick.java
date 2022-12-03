package com.code.kai.leetcode.dojo.hard.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class MinimumCostToCutStick {

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        return minCostBottomUpMemoization(0, n, 0, cuts.length - 1, cuts, new int[n][n]);
    }

    private int minCostBottomUp(int minBound, int maxBound, int start, int end, int[] cuts) {
        if (start > end)
            return 0;
        int minCost = Integer.MAX_VALUE;
        for (int k = start; k <= end; k++) {
            int cost = (maxBound - minBound) + minCostBottomUp(minBound, cuts[k], start, k - 1, cuts) + minCostBottomUp(cuts[k], maxBound, k + 1, end, cuts);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    private int minCostBottomUpMemoization(int minBound, int maxBound, int start, int end, int[] cuts, int[][] dp) {
        if (start > end)
            return 0;
        if (dp[start][end] != 0)
            return dp[start][end];
        int minCost = Integer.MAX_VALUE;
        for (int k = start; k <= end; k++) {
            int cost = (maxBound - minBound) + minCostBottomUpMemoization(minBound, cuts[k], start, k - 1, cuts, dp) + minCostBottomUpMemoization(cuts[k], maxBound, k + 1, end, cuts, dp);
            minCost = Math.min(minCost, cost);
        }
        return dp[start][end] = minCost;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumCostToCutStick().minCost(7, new int[] {1, 3, 4, 5}));
    }
}
