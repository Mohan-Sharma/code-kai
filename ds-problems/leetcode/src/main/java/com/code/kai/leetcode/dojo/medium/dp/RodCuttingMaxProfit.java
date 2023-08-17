package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class RodCuttingMaxProfit {

    public static int cutRod(int price[], int n) {
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        //return cutRodBottomUp(price, n - 1, 5);
        return cutRodBottomUpMemoization(price, n - 1, n, dp);
    }

    private static int cutRodBottomUp(int[] price, int index, int rodLen) {
        if (index == 0) {
            return price[0] * rodLen;
        }

        int notCut = cutRodBottomUp(price, index - 1, rodLen);
        int cut = Integer.MIN_VALUE;
        if (rodLen >= index + 1)
            cut = price[index] + cutRodBottomUp(price, index, rodLen - (index + 1));
        return Math.max(cut, notCut);
    }

    private static int cutRodBottomUpMemoization(int[] price, int index, int rodLen, int[][] dp) {
        if (index == 0) {
            return price[0] * (rodLen);
        }
        if (dp[index][rodLen] != -1)
            return dp[index][rodLen];
        int notCut = cutRodBottomUpMemoization(price, index - 1, rodLen, dp);
        int cut = Integer.MIN_VALUE;
        if (rodLen >= index + 1)
            cut = price[index] + cutRodBottomUpMemoization(price, index, rodLen - (index + 1), dp);
        return dp[index][rodLen] = Math.max(cut, notCut);
    }

    private static int cutRodTabulation(int[] price, int n) {
        int[][] dp = new int[n][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[0][i] = price[0] * i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                int notCut = dp[i - 1][j];
                int cut = Integer.MIN_VALUE;
                if (j >= i + 1)
                    cut = price[i] + dp[i][j - (i + 1)];
                dp[i][j] = Math.max(cut, notCut);
            }
        }
        return dp[n - 1][n];
    }

    private static int cutRodTabulationSpaceOptimized(int[] price, int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = price[0] * i;
        }

        for (int i = 1; i < n; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j <= n; j++) {
                int notCut = dp[j];
                int cut = Integer.MIN_VALUE;
                if (j >= i + 1)
                    cut = price[i] + tempDP[j - (i + 1)];
                tempDP[j] = Math.max(cut, notCut);
            }
            dp = tempDP;
        }
        return dp[n];
    }

    private static int cutRodTabulationSpaceOptimizedFurther(int[] price, int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = price[0] * i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                int notCut = dp[j];
                int cut = Integer.MIN_VALUE;
                if (j >= i + 1)
                    cut = price[i] + dp[j - (i + 1)];
                dp[j] = Math.max(cut, notCut);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(cutRodTabulationSpaceOptimizedFurther(new int[] {3, 5, 8, 9, 10, 17, 17, 20}, 8));
    }
}
