package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        //return minPathSumBottomUp(grid, grid.length - 1, grid[0].length - 1);
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return minPathSumBottomUpMemoization(grid, grid.length - 1, grid[0].length - 1, dp);
    }

    public int minPathSumBottomUp(int[][] grid, int row, int col) {
        if (row == 0 && col == 0)
            return grid[row][col];
        int up = row > 0 ? minPathSumBottomUp(grid, row - 1, col) + grid[row][col] : Integer.MAX_VALUE;
        int left = col > 0 ? minPathSumBottomUp(grid, row, col - 1) + grid[row][col] : Integer.MAX_VALUE;
        return Math.min(up, left);
    }

    public int minPathSumBottomUpMemoization(int[][] grid, int row, int col, int[][] dp) {
        if (row == 0 && col == 0)
            return grid[row][col];
        if (dp[row][col] != -1)
            return dp[row][col];
        int up = row > 0 ? minPathSumBottomUpMemoization(grid, row - 1, col, dp) + grid[row][col] : Integer.MAX_VALUE;
        int left = col > 0 ? minPathSumBottomUpMemoization(grid, row, col - 1, dp) + grid[row][col] : Integer.MAX_VALUE;
        int min = Math.min(up, left);
        dp[row][col] = min;
        return min;
    }

    public int minPathSumTabulation(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j]= grid[i][j];
                    continue;
                }
                int up = i > 0 ? dp[i - 1][j] + grid[i][j] : Integer.MAX_VALUE;
                int left = j > 0 ? dp[i][j - 1] + grid[i][j] : Integer.MAX_VALUE;
                int min = Math.min(up, left);
                dp[i][j] = min;
            }
        }
        return dp[rows - 1][cols - 1];
    }

    public int minPathSumTabulationSpaceOptimized(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];

        for (int i = 0; i < rows; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    tempDP[j]= grid[i][j];
                    continue;
                }
                int up = i > 0 ? dp[j] + grid[i][j] : Integer.MAX_VALUE;
                int left = j > 0 ? tempDP[j - 1] + grid[i][j] : Integer.MAX_VALUE;
                int min = Math.min(up, left);
                tempDP[j] = min;
            }
            dp = tempDP;
        }
        return dp[cols - 1];
    }

    public static void main(String[] args) {
        System.out.println(new MinimumPathSum().minPathSumTabulationSpaceOptimized(new int[][] {{1,3,1}, {1,5,1}, {4,2,1}}));
    }
}
