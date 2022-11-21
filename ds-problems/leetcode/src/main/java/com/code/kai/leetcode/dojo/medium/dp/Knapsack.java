package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class Knapsack {

    int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return knapsackBottomUpMemoization(weight, value, n - 1, maxWeight, dp);
    }

    private int knapsackBottomUp(int[] weight, int[] value, int index, int maxWeight) {
        if (index == 0) {
            if (weight[index] <= maxWeight) {
                return value[index];
            } else return 0;
        }

        int notTake = knapsackBottomUp(weight, value, index - 1, maxWeight);
        int take = Integer.MIN_VALUE;
        if (weight[index] <= maxWeight)
            take = value[index] + knapsackBottomUp(weight, value, index - 1, maxWeight - weight[index]);
        return Math.max(take, notTake);
    }


    private int knapsackBottomUpMemoization(int[] weight, int[] value, int index, int maxWeight, int[][] dp) {
        if (index == 0) {
            if (weight[0] <= maxWeight) {
                return value[0];
            } else return 0;
        }
        if (dp[index][maxWeight] != -1)
            return dp[index][maxWeight];
        int notTake = knapsackBottomUpMemoization(weight, value, index - 1, maxWeight, dp);
        int take = Integer.MIN_VALUE;
        if (weight[index] <= maxWeight)
            take = value[index] + knapsackBottomUpMemoization(weight, value, index - 1, maxWeight - weight[index], dp);
        int max = Math.max(take, notTake);
        dp[index][maxWeight] = max;
        return max;
    }

    private int knapsackBottomUpTabulation(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];
        for (int i = weight[0]; i <= maxWeight; i++) {
            dp[0][i] = value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                int notTake = dp[i-1][j];
                int take = Integer.MIN_VALUE;
                if (weight[i] <= j)
                    take = value[i] + dp[i-1][j - weight[i]];
                int max = Math.max(take, notTake);
                dp[i][j] = max;
            }
        }
        return dp[n-1][maxWeight];
    }

    private int knapsackBottomUpTabulationSpaceOptimized(int[] weight, int[] value, int n, int maxWeight) {
        int[] dp = new int[maxWeight + 1];
        for (int i = weight[0]; i <= maxWeight; i++) {
            dp[i] = value[0];
        }
        for (int i = 1; i < n; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j <= maxWeight; j++) {
                int notTake = dp[j];
                int take = Integer.MIN_VALUE;
                if (weight[i] <= j)
                    take = value[i] + dp[j - weight[i]];
                int max = Math.max(take, notTake);
                tempDP[j] = max;
            }
            dp = tempDP;
        }
        return dp[maxWeight];
    }

    public static void main(String[] args) {
        System.out.println(new Knapsack().knapsackBottomUpTabulationSpaceOptimized(new int[] {1, 2, 4, 5}, new int[] {5, 4, 8, 6}, 4, 5));
    }
}
