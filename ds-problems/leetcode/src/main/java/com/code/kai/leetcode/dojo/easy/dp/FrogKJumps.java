package com.code.kai.leetcode.dojo.easy.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
/*
Problem:
    This is a follow-up question to “Frog Jump”. In the previous question, the frog was allowed to jump either
    one or two steps at a time. In this question, the frog is allowed to jump up to ‘K’ steps at a time.
    If K=4, the frog can jump 1,2,3, or 4 steps at every index.
 */
public class FrogKJumps {


    public int frogJumpTopDown(int index, int k, int[] heights) {
        if (index == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if (index - i >= 0) {
                int val = frogJumpTopDown(index - i, k, heights) + Math.abs(heights[index] - heights[index - i]);
                min = Math.min(min, val);
            }
        }
        return min;
    }

    public int frogJumpTopDownMemoization(int index, int k, int[] heights, int[] dp) {
        if (index == 0)
            return 0;
        if (dp[index] > 0)
            return dp[index];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if (index - i >= 0) {
                int val = frogJumpTopDownMemoization(index - i, k, heights, dp) + Math.abs(heights[index] - heights[index - i]);
                min = Math.min(min, val);
            }
        }
        return dp[index] = min;
    }

    /*
    here space optimization cannot be done why? b/c there is no way we can keep track of k variable
    and swap their values in next iteration
     */
    public int frogJumpTopDownTabulation(int k, int[] heights) {
        int dp[] = new int[heights.length];
        dp[0] = 0;
        for (int i = 1; i < heights.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int val = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    min = Math.min(min, val);
                }
            }
            dp[i] = min;
        }
        return dp[heights.length - 1];
    }

    public int frogKJumps(int k, int[] heights) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp, -1);
        return frogJumpTopDownMemoization(heights.length - 1, k, heights, dp);
    }

    public static void main(String[] args) {
        System.out.println(new FrogKJumps().frogKJumps(5, new int[] {30, 10, 60, 10, 60, 50}));
    }
}
