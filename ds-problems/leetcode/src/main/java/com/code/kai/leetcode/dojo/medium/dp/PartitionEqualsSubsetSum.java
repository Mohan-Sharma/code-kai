package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class PartitionEqualsSubsetSum {

    public boolean canPartition(int[] nums) {
        int totalSum = IntStream.of(nums).sum();
        if (totalSum % 2 != 0)
            return false;
        int targetSum = totalSum / 2;
        int[][] dp = new int[nums.length][targetSum + 1];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return subsetSumEqualsKTabulation(nums, targetSum, nums.length - 1);
    }

    public boolean subsetSumEqualsKBottomUp(int[] arr, int target, int index) {
        if (target ==  0)
            return true;
        if (index == 0)
            return target == arr[0];

        boolean notPick = subsetSumEqualsKBottomUp(arr, target, index - 1);
        boolean pick = false;
        if (target >= arr[index])
            pick = subsetSumEqualsKBottomUp(arr, target - arr[index], index - 1);
        return pick || notPick;
    }

    public boolean subsetSumEqualsKBottomUpMemoization(int[] arr, int target, int index, int[][] dp) {
        if (target ==  0)
            return true;
        if (index == 0)
            return target == arr[0];

        if (dp[index][target] != -1)
            return dp[index][target] != 0;

        boolean notPick = subsetSumEqualsKBottomUpMemoization(arr, target, index - 1, dp);
        boolean pick = false;
        if (target >= arr[index])
            pick = subsetSumEqualsKBottomUpMemoization(arr, target - arr[index], index - 1, dp);
        dp[index][target]  = pick || notPick ? 1 : 0;
        return pick || notPick;
    }


    public boolean subsetSumEqualsKTabulation(int[] arr, int n, int target) {
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (arr[0] <= target)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean notPick = dp[i - 1][j];
                boolean pick = false;
                if (j >= arr[i])
                    pick = dp[i - 1][j - arr[i]];
                dp[i][j] = notPick || pick;
            }
        }
        return dp[n-1][target];
    }

    public static void main(String[] args) {
        System.out.println(new PartitionEqualsSubsetSum().canPartition(new int[]{1,2,3,5}));
    }
}
