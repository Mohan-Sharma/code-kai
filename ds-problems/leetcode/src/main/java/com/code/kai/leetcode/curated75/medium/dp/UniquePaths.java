package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class UniquePaths {

    public static int uniquePaths(int m, int n) {
        //return uniquePathsBottomUp(m-1, n-1);
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return uniquePathsBottomUpMemoization(m-1, n-1, dp);
    }

    private static int uniquePathsBottomUp(int row, int col) {
        if (row < 0 || col < 0)
            return 0;
        if (row == 0 && col == 0)
            return 1;
        int down = uniquePathsBottomUp(row - 1, col);
        int right = uniquePathsBottomUp(row, col - 1);
        return down + right;
    }

    private static int uniquePathsBottomUpMemoization(int row, int col, int[][] dp) {
        if (row < 0 || col < 0)
            return 0;
        if (row == 0 && col == 0)
            return 1;
        if (dp[row][col] > 0)
            return dp[row][col];
        int total = uniquePathsBottomUp(row - 1, col) + uniquePathsBottomUp(row, col - 1);
        dp[row][col] = total;
        return total;
    }

    private static int uniquePathsTabulation(int row, int col) {
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int down = i > 0 ? dp[i-1][j] : 0;
                int right = j > 0 ? dp[i][j-1] : 0;
                int total = down + right;
                dp[i][j] = total;
            }
        }
        return dp[row-1][col-1];
    }

    private static int uniquePathsTabulationSpaceOptimized(int row, int col) {
        int[] dp = new int[col];
        for (int i = 0; i < row; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    tempDP[j] = 1;
                    continue;
                }
                int down = i > 0 ? dp[j] : 0;
                int right = j > 0 ? tempDP[j  -1] : 0;
                int total = down + right;
                tempDP[j] = total;
            }
            dp = tempDP;
        }
        return dp[col -1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsTabulationSpaceOptimized(3, 2));
    }
}
