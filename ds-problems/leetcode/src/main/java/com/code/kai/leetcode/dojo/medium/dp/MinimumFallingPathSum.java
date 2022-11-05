package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class MinimumFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int cols = matrix[0].length;
        int rows = matrix.length;

        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < cols; i++) {
            min = Math.min(min, minFallingPathSumBottomUpMemoization(matrix, 0, i, dp));
        }
        return min;
    }

    public int minFallingPathSumBottomUp(int[][] matrix, int row, int col) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length)
            return Integer.MAX_VALUE;
        if (row == matrix.length - 1)
            return matrix[row][col];

        int current = matrix[row][col];
        int down = minFallingPathSumBottomUp(matrix, row + 1, col);
        if (down != Integer.MAX_VALUE)
            down += current;
        int leftDiag = minFallingPathSumBottomUp(matrix, row + 1, col - 1);
        if (leftDiag != Integer.MAX_VALUE)
            leftDiag += current;
        int rightDiag = minFallingPathSumBottomUp(matrix, row + 1, col + 1);
        if (rightDiag != Integer.MAX_VALUE)
            rightDiag += current;

        return Math.min(down, Math.min(leftDiag, rightDiag));
    }

    public int minFallingPathSumBottomUpMemoization(int[][] matrix, int row, int col, int[][] dp) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length)
            return Integer.MAX_VALUE;
        if (row == matrix.length - 1)
            return matrix[row][col];
        if (dp[row][col] != -1)
            return dp[row][col];

        int current = matrix[row][col];
        int down = minFallingPathSumBottomUpMemoization(matrix, row + 1, col, dp);
        if (down != Integer.MAX_VALUE)
            down += current;
        int leftDiag = minFallingPathSumBottomUpMemoization(matrix, row + 1, col - 1, dp);
        if (leftDiag != Integer.MAX_VALUE)
            leftDiag += current;
        int rightDiag = minFallingPathSumBottomUpMemoization(matrix, row + 1, col + 1, dp);
        if (rightDiag != Integer.MAX_VALUE)
            rightDiag += current;

        int min = Math.min(down, Math.min(leftDiag, rightDiag));
        dp[row][col] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumFallingPathSum().minFallingPathSum(new int[][]{{2,1,3}, {6,5,4}, {7,8,9}}));
    }
}
