package com.code.kai.leetcode.dojo.easy.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
/*
There is a frog on the 1st step of an N stairs long staircase. The frog wants to reach the Nth stair.
HEIGHT[i] is the height of the (i+1)th stair.If Frog jumps from ith to jth stair, the energy lost in the jump
is given by |HEIGHT[i-1] - HEIGHT[j-1] |.In the Frog is on ith staircase, he can jump either to (i+1)th stair
or to (i+2)th stair. Your task is to find the minimum total energy used by the frog to reach from 1st stair to Nth stair.

For Example
If the given ‘HEIGHT’ array is [10,20,30,10], the answer 20 as the frog can jump from 1st stair
to 2nd stair (|20-10| = 10 energy lost) and then a jump from 2nd stair to last stair (|10-20| = 10 energy lost).
So, the total energy lost is 20.
 */
public class FrogJumps {

    public int frogJumpTopDown(int index, int[] heights) {
        if (index == 0)
            return 0;
        int left = frogJumpTopDown(index - 1, heights) + Math.abs(heights[index] - heights[index - 1]);
        int right = Integer.MAX_VALUE;
        if (index > 1)
            right = frogJumpTopDown(index - 2, heights) + Math.abs(heights[index] - heights[index - 2]);
        return Math.min(left, right);
    }

    public int frogJumpTopDownMemoization(int index, int[] heights, int[] dp) {
        if (index == 0)
            return 0;
        if (dp[index] > 0)
            return dp[index];
        int left = frogJumpTopDownMemoization(index - 1, heights, dp) + Math.abs(heights[index] - heights[index - 1]);
        int right = Integer.MAX_VALUE;
        if (index > 1)
            right = frogJumpTopDownMemoization(index - 2, heights, dp) + Math.abs(heights[index] - heights[index - 2]);
        int max = Math.min(left, right);
        dp[index] = max;
        return max;
    }

    public int frogJumpTabulation(int[] heights) {
        int[] dp = new int[heights.length];
        dp[0] = 0;
        for (int i = 1; i < heights.length; i++) {
            int left = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int right = Integer.MAX_VALUE;
            if (i > 1)
                right = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            dp[i] = Math.min(left, right);
        }
        return dp[heights.length - 1];
    }

    public int frogJumpSpaceOptimized(int[] heights) {
        int prevToAdj = 0;
        int adj = 0; // when at 0 step it requires 0 base case
        for (int i = 1; i < heights.length; i++) {
            int left = adj + Math.abs(heights[i] - heights[i - 1]);
            int right = Integer.MAX_VALUE;
            if (i > 1)
                right = prevToAdj + Math.abs(heights[i] - heights[i - 2]);
            int current = Math.min(left, right);
            prevToAdj = adj;
            adj = current;
        }
        return adj;
    }

    public int frogJumpTopDown(int[] heights) {
        int[] dp = new int[heights.length];
        Arrays.fill(dp , -1);
        return frogJumpTopDownMemoization(heights.length - 1, heights, dp);
    }

    public static void main(String[] args) {
        System.out.println(new FrogJumps().frogJumpSpaceOptimized(new int[] {30, 10, 60, 10, 60, 50}));
    }
}
