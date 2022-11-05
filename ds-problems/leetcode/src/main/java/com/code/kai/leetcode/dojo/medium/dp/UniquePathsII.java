package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //return uniquePathsBottomUp(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return uniquePathsBottomUpMemoization(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, dp);
    }

    public int uniquePathsBottomUp(int[][] obstacleGrid, int row, int col) {
        if (row < 0 || col < 0)
            return 0;
        if (obstacleGrid[row][col] == 1)
            return 0;
        if (row == 0 && col == 0)
            return 1;
        int up = uniquePathsBottomUp(obstacleGrid, row - 1, col);
        int left = uniquePathsBottomUp(obstacleGrid, row, col - 1);
        return up + left;
    }

    public int uniquePathsBottomUpMemoization(int[][] obstacleGrid, int row, int col, int[][] dp) {
        if (row < 0 || col < 0)
            return 0;
        if (obstacleGrid[row][col] == 1)
            return 0;
        if (row == 0 && col == 0)
            return 1;
        if (dp[row][col] != -1)
            return dp[row][col];
        int up = uniquePathsBottomUpMemoization(obstacleGrid, row - 1, col, dp);
        int left = uniquePathsBottomUpMemoization(obstacleGrid, row, col - 1, dp);
        int total = up + left;
        dp[row][col] = total;
        return total;
    }

    public int uniquePathsTabulation(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                int up = i > 0 ? dp[i-1][j] : 0;
                int left = j > 0 ? dp[i][j-1] : 0;

                dp[i][j] = up + left;
            }
        }
        return dp[rows - 1][cols - 1];
    }

    public int uniquePathsTabulationSpaceOptimized(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        int[] dp = new int[cols];

        for (int i = 0; i < rows; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    tempDP[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    tempDP[j] = 1;
                    continue;
                }
                int up = i > 0 ? dp[j] : 0;
                int left = j > 0 ? tempDP[j-1] : 0;
                tempDP[j] = up + left;
            }
            dp = tempDP;
        }
        return dp[cols - 1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePathsII().uniquePathsTabulationSpaceOptimized(new int[][]{{1,0}, {0,0}}));
    }
}
