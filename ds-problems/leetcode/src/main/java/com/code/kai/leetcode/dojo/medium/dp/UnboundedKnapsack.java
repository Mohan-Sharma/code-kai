package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class UnboundedKnapsack {

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        //return unboundedKnapsackBottomUp(n - 1, w, profit, weight);
        int[][] dp = new int[n][w + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return unboundedKnapsackBottomUpMemoization(n - 1, w, profit, weight, dp);
    }

    private static int unboundedKnapsackBottomUp(int index, int maxWeight, int[] profit, int[] weight) {
        if (index == 0) {
            // since / operator always give int result e.g. mw is 8 and 0 index weight is 3 I can take 2 items of 3 weight means 8/3 = 2
            // if fraction had been allowed then I would have taken 8/3 + (8%3)/3 = 2.66
            return profit[0] * (maxWeight / weight[0]);
        }

        int notPick = unboundedKnapsackBottomUp(index - 1, maxWeight, profit, weight);
        int pick = Integer.MIN_VALUE;
        if (maxWeight >= weight[index]) {
            int max = unboundedKnapsackBottomUp(index, maxWeight - weight[index], profit, weight);
            pick = max != Integer.MIN_VALUE ? max + profit[index] : max;
        }
        return Math.max(pick, notPick);
    }

    private static int unboundedKnapsackBottomUpMemoization(int index, int maxWeight, int[] profit, int[] weight, int[][] dp) {
        if (index == 0) {
            if (maxWeight % weight[0] == 0)
                return profit[0] * (maxWeight / weight[0]);
            return Integer.MIN_VALUE;
        }

        if (dp[index][maxWeight] != -1)
            return dp[index][maxWeight];
        int notPick = unboundedKnapsackBottomUpMemoization(index - 1, maxWeight, profit, weight, dp);
        int pick = Integer.MIN_VALUE;
        if (maxWeight >= weight[index]) {
            int max = unboundedKnapsackBottomUpMemoization(index, maxWeight - weight[index], profit, weight, dp);
            pick = max != Integer.MIN_VALUE ? max + profit[index] : max;
        }
        return dp[index][maxWeight] = Math.max(pick, notPick);
    }

    private static int unboundedKnapsackTabulation(int len, int maxWeight, int[] profit, int[] weight) {
        int[][] dp = new int[len][maxWeight + 1];
        for (int i = 0; i <= maxWeight; i++) {
            if (i % weight[0] == 0)
                dp[0][i] = profit[0] * (i / weight[0]);
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                int notPick = dp[i - 1][j];
                int pick = Integer.MIN_VALUE;
                if (j >= weight[i]) {
                    pick= dp[i][j - weight[i]]+ profit[i];
                }
                dp[i][j]= Math.max(pick, notPick);
            }
        }
        return dp[len - 1][maxWeight];
    }

    private static int unboundedKnapsackTabulationSpaceOptimized(int len, int maxWeight, int[] profit, int[] weight) {
        int[] dp = new int[maxWeight + 1];
        for (int i = 0; i <= maxWeight; i++) {
            dp[i] = profit[0] * (i / weight[0]);
        }
        for (int i = 1; i < len; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 0; j <= maxWeight; j++) {
                int notPick = dp[j];
                int pick = Integer.MIN_VALUE;
                if (j >= weight[i]) {
                    int max = tempDP[j - weight[i]];
                    pick = max != Integer.MIN_VALUE ? max + profit[i] : max;
                }
                tempDP[j]= Math.max(pick, notPick);
            }
            dp = tempDP;
        }
        return dp[maxWeight];
    }

    /*
        If we analyse further we will see that tempDP[col] depends on dp[same col as tempDP] and tempDP[some col before]
        hence we can maintain 1 array to solve this
        e.g. dp = [col1, col2, col3] so tempDP[col2] depends on dp[col2] and tempDP[some col before col2]
     */
    private static int unboundedKnapsackTabulationSpaceOptimizedFurther(int len, int maxWeight, int[] profit, int[] weight) {
        int[] dp = new int[maxWeight + 1];
        for (int i = 0; i <= maxWeight; i++) {
            dp[i] = profit[0] * (i / weight[0]);
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                int notPick = dp[j];
                int pick = Integer.MIN_VALUE;
                if (j >= weight[i]) {
                    int max = dp[j - weight[i]];
                    pick = max != Integer.MIN_VALUE ? max + profit[i] : max;
                }
                dp[j]= Math.max(pick, notPick);
            }
        }
        return dp[maxWeight];
    }

    public static void main(String[] args) {
        System.out.println(unboundedKnapsackTabulation(5, 13, new int[] {7, 9, 1, 3, 10}, new int[] {5, 5, 7, 7, 7}));
    }
}
