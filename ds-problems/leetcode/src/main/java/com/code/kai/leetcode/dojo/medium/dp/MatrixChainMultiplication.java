package com.code.kai.leetcode.dojo.medium.dp;

/**
 * @author Mohan Sharma
 */
public class MatrixChainMultiplication {

    public static int matrixMultiplication(int[] arr , int N) {
        return matrixMultiplicationBottomUpMemoization(1, N - 1, arr, new int[N][N]);
    }

    private static int matrixMultiplicationBottomUp(int i, int j, int[] arr) {
        if (i == j)
            return 0;
        int minSteps = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int steps = arr[i - 1] * arr[k] * arr[j] +
                    matrixMultiplicationBottomUp(i, k, arr) +
                    matrixMultiplicationBottomUp(k + 1, j, arr);
            minSteps = Math.min(minSteps, steps);
        }
        return minSteps;
    }

    private static int matrixMultiplicationBottomUpMemoization(int i, int j, int[] arr, int[][] dp) {
        if (i == j)
            return 0;
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int minSteps = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int steps = arr[i - 1] * arr[k] * arr[j] +
                    matrixMultiplicationBottomUpMemoization(i, k, arr, dp) +
                    matrixMultiplicationBottomUpMemoization(k + 1, j, arr, dp);
            minSteps = Math.min(minSteps, steps);
        }
        return dp[i][j] = minSteps;
    }

    private static int matrixMultiplicationTabulation(int[] arr, int N) {
        int[][] dp = new int[N][N];
        for (int i = N-1; i >= 1; i--) {
            for (int j = i + 1; j < N ; j++) {
                int minSteps = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
                    minSteps = Math.min(minSteps, steps);
                }
                dp[i][j] = minSteps;
            }
        }
        return dp[1][N-1];
    }

    public static void main(String[] args) {
        System.out.println(MatrixChainMultiplication.matrixMultiplicationTabulation(new int[] {10, 20, 30, 40, 50}, 5));
    }
}
