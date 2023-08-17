package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class CountSubsetSumK {

    public int countSubsetSumK(int[] arr, int k) {
        int[][] dp = new int[arr.length][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return countSubsetBottomUp(arr.length - 1, k, arr);
    }

    private int countSubsetBottomUp(int index, int target, int[] arr) {
        if (index == 0) {
            if (target == 0 && arr[0] == 0)
                return 2;
            else if (target == 0 || target == arr[0])
                return 1;
            else
                return 0;
        }
        return countSubsetBottomUp(index - 1, target, arr) +
                countSubsetBottomUp(index - 1, target - arr[index], arr);
    }

    /* If arr is {0, 0, 1} k =1 output should be 4 {1}, {0, 1}, {0, 1} and {0, 0, 1} these will give only 1
        if (target ==  0)
            return true;
        if (index == 0)
            return target == arr[0];
        so probably we need to go to last index always to find the base case
 */
    private int countSubsetBottomUpMemoization(int index, int target, int[] arr, int[][] dp) {
        if (index == 0) {
            if (target == 0 && arr[0] == 0)
                return 2;
            else if (target == 0 || target == arr[0])
                return 1;
            else
                return 0;
        }
        if (dp[index][target] != -1)
            return dp[index][target];
        int notPick = countSubsetBottomUp(index - 1, target, arr);
        int pick = 0;
        if (target >= arr[index])
            pick = countSubsetBottomUp(index - 1, target - arr[index], arr);
        int count = pick + notPick;
        dp[index][target] = count;
        return count;
    }

    public int countSubsetBottomUpTabulation(int[] arr, int target) {
        int rows = arr.length;
        int[][] dp = new int[rows][target + 1];
        if (arr[0] == 0)
                dp[0][0] = 2;
        else
            dp[0][0] = 1;
        if (arr[0] <= target && arr[0] != 0)
            dp[0][arr[0]] = 1;
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j <= target; j++) {
                int notPick = dp[i-1][j];
                int pick = 0;
                if (j >= arr[i])
                    pick = dp[i-1][j - arr[i]];
                int count = pick + notPick;
                dp[i][j] = count;
            }
        }
        return dp[rows - 1][target];
    }

    private int countSubsetBottomUpTabulationSpaceOptimized(int[] arr, int target) {
        int rows = arr.length;
        int[] dp = new int[target + 1];
        if (arr[0] == 0)
            dp[0] = 2;
        else
            dp[0] = 1;
        if (arr[0] <= target && arr[0] != 0)
            dp[arr[0]] = 1;
        for (int i = 1; i < rows; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j <= target; j++) {
                int notPick = dp[j];
                int pick = 0;
                if (j >= arr[i])
                    pick = dp[j - arr[i]];
                int count = pick + notPick;
                tempDP[j] = count;
            }
            dp = tempDP;
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(new CountSubsetSumK().countSubsetSumK(new int[] {0, 0, 1}, 1));
    }
}
