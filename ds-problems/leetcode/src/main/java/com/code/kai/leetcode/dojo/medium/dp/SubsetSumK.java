package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class SubsetSumK {

    /*
        Constraints:
        1 <= N <= 10^3
        0 <= K <= 10^3
    */
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return subsetSumToKBottomUpMemoization(n-1, k , arr, dp);
    }

    private static boolean subsetSumToKBottomUpMemoization(int index, int target, int[] arr, int[][] dp) {
        if (target == 0)
            return true;
        if (index == 0)
            return target == arr[0];
        if (dp[index][target] != -1)
            return dp[index][target] != 0;
        boolean notPick = subsetSumToKBottomUpMemoization(index - 1, target, arr, dp);
        boolean pick = false;
        if (target >= arr[index])
            pick = subsetSumToKBottomUpMemoization(index - 1, target - arr[index], arr, dp);
        dp[index][target] = notPick || pick ? 1 : 0;
        return notPick || pick;
    }

    private boolean subsetSumToKBottomUp(int index, int target, int[] arr) {
        if (target == 0)
            return true;
        if (index == 0)
            return target == arr[index];
        if (target < arr[index])
            return false;
        boolean notPick = subsetSumToKBottomUp(index - 1, target, arr);
        boolean pick = subsetSumToKBottomUp(index - 1, target - arr[index], arr);
        return notPick || pick;
    }

    public static boolean subsetSumToKTabulation(int n, int k, int arr[]){
        boolean[][] dp = new boolean[n][k+1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (arr[0] <= k)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                boolean notPick = dp[i - 1][j];
                boolean pick = false;
                if (j >= arr[i])
                    pick = dp[i - 1][j - arr[i]];
                dp[i][j] = notPick || pick;
            }
        }
        return dp[n-1][k];
    }

    public static boolean subsetSumToKTabulationSpaceOptimized(int n, int k, int arr[]){
        boolean[] dp = new boolean[k+1];

        dp[0] = true;

        if (arr[0] <= k)
            dp[arr[0]] = true;

        for (int i = 1; i < n; i++) {
            boolean[] tempDP = new boolean[dp.length];
            tempDP[0] = true; // dp[i][0] = true; for every row 0th index is marked as true since if target is 0 row does not matter
            for (int j = 1; j <= k; j++) {
                boolean notPick = dp[j];
                boolean pick = false;
                if (j >= arr[i])
                    pick = dp[j - arr[i]];
                tempDP[j] = notPick || pick;
            }
            dp = tempDP;
        }
        return dp[k];
    }

    public static void main(String[] args) {
        System.out.println(subsetSumToKTabulation(4, 4, new int[] {3, 9, 7, 3}));
    }
}
