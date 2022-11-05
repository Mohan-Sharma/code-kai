package com.code.kai.leetcode.curated75.easy.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
// Classic fibonacci problem
public class ClimbingStairs {

    /*
    Either we go 1 step or go 2 steps. Meaning two recursive branches. So let's start with top and go down
    when we reach 0 means that's 1 way to reach bottom, but sometimes we might reach less than 0, in that case
    it's over the bound hence return 0. Finally, return the sum of the two branches to count no. of ways
     */
    public int climbStairsTopBottom(int n) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        return climbStairsTopBottom(n - 1) + climbStairsTopBottom(n - 2);
    }

    public int climbStairsTopBottomMemoization(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return climbStairsTopBottomMemoization(n, dp);
    }

    public int climbStairsTabulation(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + (i > 1 ? dp[i - 2] : 0);
        }
        return dp[n];
    }

    /*
    After seeing tabulation we realised that we need the sum of previous 2 indexes of the array,
    which we can do using 2 variable we know at negative index it was 0 and for zero index was 1
    so temp = 0 + 1, then the adj element becomes prev and temp becomes adj for next iteration.
     */
    public int climbingStairsSpaceOptimized(int n) {
        int prevToAdj = 0;
        int adj = 1;
        for (int i = 1; i <= n; i++) {
            int temp = prevToAdj + adj;
            prevToAdj = adj;
            adj = temp;
        }
        return adj;
    }

    public int climbStairsTopBottomMemoization(int n, int[] dp) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        if (dp[n] > 0)
            return dp[n];
        int val = climbStairsTopBottomMemoization(n - 1, dp) + climbStairsTopBottomMemoization(n - 2, dp);
        dp[n] = val;
        return val;
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbingStairsSpaceOptimized(2));
    }
}
