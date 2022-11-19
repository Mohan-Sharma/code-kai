package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class MinimumCoins {

    public static int minimumCoins(int p){
        int[] denominations = new int[] {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        int[][] dp = new int[denominations.length][p +1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        //int result = minimumCoinsBottomUpMemoization(denominations, p, denominations.length - 1, dp);
        int result = minimumCoinsTabulationSpaceOptimized(denominations, p);
        return result != Integer.MAX_VALUE ? result : -1;
    }

    private static int minimumCoinsBottomUp(int[] denominations, int amount, int index) {
        if (index == 0) {
            if (amount % denominations[0] == 0)
                return amount / denominations[0];
            return Integer.MAX_VALUE;
        }

        int notPick = minimumCoinsBottomUp(denominations, amount, index - 1);
        int pick = Integer.MAX_VALUE;
        if (amount >= denominations[index]) {
            int count = minimumCoinsBottomUp(denominations, amount - denominations[index], index);
            pick = count != Integer.MAX_VALUE ? 1 + count : count;
        }
        return Math.min(notPick, pick);
    }

    private static int minimumCoinsBottomUpMemoization(int[] denominations, int amount, int index, int[][] dp) {
        if (index == 0) {
            if (amount % denominations[0] == 0)
                return amount / denominations[0];
            return Integer.MAX_VALUE;
        }
        if (dp[index][amount] != -1)
            return dp[index][amount];
        int notPick = minimumCoinsBottomUpMemoization(denominations, amount, index - 1, dp);
        int pick = Integer.MAX_VALUE;
        if (amount >= denominations[index]) {
            int count = minimumCoinsBottomUpMemoization(denominations, amount - denominations[index], index, dp);
            pick = count != Integer.MAX_VALUE ? 1 + count : count;
        }
        return dp[index][amount] = Math.min(notPick, pick);
    }

    private static int minimumCoinsTabulation(int[] denominations, int amount) {
        int[][] dp = new int[denominations.length][amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % denominations[0] == 0)
                dp[0][i] = i / denominations[0];
        }
        for (int i = 1; i < denominations.length; i++) {
            for (int j = 0; j <= amount; j++) {
                int notPick = dp[i-1][j];
                int pick = Integer.MAX_VALUE;
                if (j >= denominations[i]) {
                    pick = 1 + dp[i][j - denominations[i]];
                }
                dp[i][j] = Math.min(notPick, pick);
            }
        }
        return dp[denominations.length - 1][amount];
    }

    private static int minimumCoinsTabulationSpaceOptimized(int[] denominations, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % denominations[0] == 0)
                dp[i] = i / denominations[0];
        }
        for (int i = 1; i < denominations.length; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j <= amount; j++) {
                int notPick = dp[j];
                int pick = Integer.MAX_VALUE;
                if (j >= denominations[i]) {
                    pick = 1 + tempDP[j - denominations[i]];
                }
                tempDP[j] = Math.min(notPick, pick);
            }
            dp = tempDP;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(MinimumCoins.minimumCoins(60));
    }
}
