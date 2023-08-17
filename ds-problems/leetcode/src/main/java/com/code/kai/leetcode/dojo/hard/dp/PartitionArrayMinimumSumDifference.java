package com.code.kai.leetcode.dojo.hard.dp;

import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class PartitionArrayMinimumSumDifference {

    public int minimumDifference(int[] nums) {
        int total = IntStream.of(nums).sum();
        boolean[][] dp = new boolean[nums.length][total + 1];
        populateDPArray(nums, dp, total);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= total/2 ; i++) {
            if (dp[nums.length - 1][i]) {
                min = Math.min(min, Math.abs(i - (total - i)));
            }
        }
        return min;
    }

    private void populateDPArray(int[] nums, boolean[][] dp, int sum) {
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        if (nums[0] <= sum)
            dp[0][nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                boolean notPick = dp[i-1][j];
                boolean pick = false;
                if (j >= nums[i])
                    pick = dp[i - 1][j - nums[i]];
                dp[i][j] = pick || notPick;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new PartitionArrayMinimumSumDifference().minimumDifference(new int[] {4, 3, 2, 1}));
    }
}
