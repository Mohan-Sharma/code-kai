package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class CoinChangeII {

    public int change(int amount, int[] coins) {
        int rows = coins.length;
        int[][] dp = new int[rows][amount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return changeBottomUpMemoization(coins, rows - 1, amount, dp);
    }

    private int changeBottomUp(int[] coins, int index, int amount) {
        if (index == 0) {
            if (amount % coins[0] == 0)
                return 1;
            return 0;
        }
        int notPick = changeBottomUp(coins, index - 1, amount);
        int pick = 0;
        if (amount >= coins[index])
            pick = changeBottomUp(coins, index, amount - coins[index]);
        return pick + notPick;
    }


    private int changeBottomUpMemoization(int[] coins, int index, int amount, int[][] dp) {
        if (index == 0) {
            if (amount % coins[0] == 0)
                return 1;
            return 0;
        }
        if (dp[index][amount] != -1)
            return dp[index][amount];
        int notPick = changeBottomUp(coins, index - 1, amount);
        int pick = 0;
        if (amount >= coins[index])
            pick = changeBottomUp(coins, index, amount - coins[index]);
        return dp[index][amount] = pick + notPick;
    }

    private int changeTabulation(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[0][i] = 1;
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                int notPick = dp[i - 1][j];
                int pick = 0;
                if (j >= coins[i])
                    pick = dp[i][j - coins[i]];
                dp[i][j] = pick + notPick;
            }
        }
        return dp[coins.length - 1][amount];
    }

    private int changeTabulationSpaceOptimized(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[i] = 1;
        }
        for (int i = 1; i < coins.length; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j <= amount; j++) {
                int notPick = dp[j];
                int pick = 0;
                if (j >= coins[i])
                    pick = tempDP[j - coins[i]];
                tempDP[j] = pick + notPick;
            }
            dp = tempDP;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChangeII().changeTabulationSpaceOptimized(5, new int[] {1, 2, 5}));
    }
}
