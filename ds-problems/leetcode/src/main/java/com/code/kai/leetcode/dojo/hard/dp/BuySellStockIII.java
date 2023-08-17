package com.code.kai.leetcode.dojo.hard.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Similar to BuySellStockII additional constraint is that only 2 transaction allowed means only 2 times
    buy-sell allowed
    Solution: Similar to BuySellStockII except that now there should be a cap of max 2
 */
public class BuySellStockIII {

    public int maxProfit(int[] prices) {
        int[][][] dp = new int[prices.length][2][2];
        for (int[][] row : dp) {
            for (int[] cols : row) {
                Arrays.fill(cols, -1);
            }
        }
        return maxProfitBottomUpMemoization(prices, 0, 1, 0, dp);
        //return maxProfitBottomUpUsingTransactions(prices, 0, 0);
        //return maxProfitBottomUp(prices, 0, 1, 0);
    }

    private int maxProfitBottomUp(int[] prices, int index, int eligibleToBuy, int cap) {
        if (index >= prices.length || cap >= 2)
            return 0;
        if (eligibleToBuy == 1) {
            int buy = -prices[index] + maxProfitBottomUp(prices, index + 1, 0, cap);
            int notBuy = maxProfitBottomUp(prices, index + 1, 1, cap);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfitBottomUp(prices, index + 1, 1, cap + 1);
            int notSell = maxProfitBottomUp(prices, index + 1, 0, cap);
            return Math.max(sell, notSell);
        }
    }

    private int maxProfitBottomUpMemoization(int[] prices, int index, int eligibleToBuy, int cap, int[][][] dp) {
        if (index >= prices.length || cap >= 2)
            return 0;
        if (dp[index][eligibleToBuy][cap] != -1)
            return dp[index][eligibleToBuy][cap];
        if (eligibleToBuy == 1) {
            int buy = -prices[index] + maxProfitBottomUpMemoization(prices, index + 1, 0, cap, dp);
            int notBuy = maxProfitBottomUpMemoization(prices, index + 1, 1, cap, dp);
            return dp[index][eligibleToBuy][cap] = Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfitBottomUpMemoization(prices, index + 1, 1, cap + 1, dp);
            int notSell = maxProfitBottomUpMemoization(prices, index + 1, 0, cap, dp);
            return dp[index][eligibleToBuy][cap] = Math.max(sell, notSell);
        }
    }

    private int maxProfitTabulation(int[] prices) {
        int[][][] dp = new int[prices.length + 1][2][3];
        for (int i = prices.length-1; i >= 0 ; i--) {
            for (int eligibleToBuy : List.of(0, 1)) {
                for (int cap : List.of(0, 1)) { // 2 transactions 0 based index
                    if (eligibleToBuy == 1) {
                        int buy = -prices[i] + dp[i + 1][0][cap];
                        int notBuy = dp[i + 1][1][cap];
                        dp[i][eligibleToBuy][cap] = Math.max(buy, notBuy);
                    } else {
                        int sell = prices[i] + dp[i + 1][1][cap + 1];
                        int notSell = dp[i + 1][0][cap];
                        dp[i][eligibleToBuy][cap] = Math.max(sell, notSell);
                    }
                }
            }
        }
        return dp[0][1][0];
    }

    private int maxProfitTabulationSpaceOptimized(int[] prices) {
        int[][] prev = new int[2][3];
        for (int i = prices.length-1; i >= 0 ; i--) {
            int[][] cur = new int[prev.length][prev[0].length];
            for (int eligibleToBuy : List.of(1, 0)) {
                for (int cap : List.of(1, 2)) {
                    if (eligibleToBuy == 1) {
                        int buy = -prices[i] + prev[0][cap];
                        int notBuy = prev[1][cap];
                        cur[eligibleToBuy][cap] = Math.max(buy, notBuy);
                    } else {
                        int sell = prices[i] + prev[1][cap - 1];
                        int notSell = prev[0][cap];
                        cur[eligibleToBuy][cap] = Math.max(sell, notSell);
                    }
                }
            }
            prev = cur;
        }
        return prev[1][2];
    }

    /*
        If we think since the cap is 2 buy-sell there can be 4 transactions one buy one sell then one buy one sell that means we can have
        a dp[prices.length][4]
     */
    private int maxProfitBottomUpUsingTransactions(int[] prices, int index, int transaction) {
        // since 2 buy-sell allowed means t0, t1, t2, t3 allowed. How do I know if transaction is buy or sell that we can decide by the rule
        // that if transaction % 2 == 0 then it is buy otherwise sell. Meaning t0, t2 are buy and t1, t3 are sell
        if (transaction >= 4 || index >= prices.length)
            return 0;

        if (transaction % 2 == 0) {
            int buy = -prices[index] + maxProfitBottomUpUsingTransactions(prices, index + 1, transaction + 1);
            int notBuy = maxProfitBottomUpUsingTransactions(prices, index + 1, transaction);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfitBottomUpUsingTransactions(prices, index + 1, transaction + 1);
            int notSell = maxProfitBottomUpUsingTransactions(prices, index + 1, transaction);
            return Math.max(sell, notSell);
        }
    }
    private int maxProfitTabulationUsingTransactions(int[] prices) {
        // since 2 buy-sell allowed means t0, t1, t2, t3 allowed. How do I know if transaction is buy or sell that we can decide by the rule
        // that if transaction % 2 == 0 then it is buy otherwise sell. Meaning t0, t2 are buy and t1, t3 are sell
        int[][] dp = new int[prices.length + 1][5];

        for (int index = prices.length - 1; index >= 0 ; index--) {
            for (int transaction = 3; transaction >= 0; transaction--) {
                if (transaction % 2 == 0) {
                    int buy = -prices[index] + dp[index + 1][transaction + 1];
                    int notBuy = dp[index + 1][transaction];
                    dp[index][transaction] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[index] + dp[index + 1][transaction + 1];
                    int notSell = dp[index + 1][transaction];
                    dp[index][transaction] =  Math.max(sell, notSell);
                }
            }
        }
        return dp[0][0];
    }

    private int maxProfitTabulationUsingTransactionsSpaceOptimized(int[] prices) {
        // since 2 buy-sell allowed means t0, t1, t2, t3 allowed. How do I know if transaction is buy or sell that we can decide by the rule
        // that if transaction % 2 == 0 then it is buy otherwise sell. Meaning t0, t2 are buy and t1, t3 are sell
        int[] dp = new int[5];

        for (int index = prices.length - 1; index >= 0 ; index--) {
            int[] tempDP = new int[dp.length];
            for (int transaction = 3; transaction >= 0; transaction--) {
                if (transaction % 2 == 0) {
                    int buy = -prices[index] + dp[transaction + 1];
                    int notBuy = dp[transaction];
                    tempDP[transaction] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[index] + dp[transaction + 1];
                    int notSell = dp[transaction];
                    tempDP[transaction] =  Math.max(sell, notSell);
                }
            }
            dp = tempDP;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new BuySellStockIII().maxProfitTabulation(new int[] {3,3,5,0,0,3,1,4}));
    }
}
