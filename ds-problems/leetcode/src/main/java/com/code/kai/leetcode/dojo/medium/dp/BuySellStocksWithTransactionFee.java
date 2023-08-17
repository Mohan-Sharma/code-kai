package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class BuySellStocksWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        //return maxProfitBottomUp(prices, fee, 0, 1);
        int[][] dp = new int[prices.length][2];
        for (int[] row : dp) {
            Arrays.fill(row,  -1);
        }
        return maxProfitBottomUpMemoization(prices, fee, 0, 1, dp);
    }

    private int maxProfitBottomUp(int[] prices, int fee, int index, int eligibleToBuy) {
        if (index >= prices.length)
            return 0;
        if (eligibleToBuy == 1) {
            return Math.max(-prices[index] + maxProfitBottomUp(prices, fee, index + 1, 0),
                    maxProfitBottomUp(prices, fee, index + 1, 1));
        } else {
            return Math.max(prices[index] - fee + maxProfitBottomUp(prices, fee, index + 1, 1),
                    maxProfitBottomUp(prices, fee, index + 1, 0));
        }
    }

    private int maxProfitBottomUpMemoization(int[] prices, int fee, int index, int eligibleToBuy, int[][] dp) {
        if (index >= prices.length)
            return 0;
        if (dp[index][eligibleToBuy] != -1)
            return dp[index][eligibleToBuy];
        if (eligibleToBuy == 1) {
            return dp[index][eligibleToBuy] =
                    Math.max(-prices[index] + maxProfitBottomUpMemoization(prices, fee, index + 1, 0, dp),
                            maxProfitBottomUpMemoization(prices, fee, index + 1, 1, dp));
        } else {
            return dp[index][eligibleToBuy] =
                    Math.max(prices[index] - fee + maxProfitBottomUpMemoization(prices, fee, index + 1, 1, dp),
                            maxProfitBottomUpMemoization(prices, fee, index + 1, 0, dp));
        }
    }

    private int maxProfitTabulation(int[] prices, int fee) {
        int[][] dp = new int[prices.length + 1][2];
        for (int index = prices.length - 1; index >= 0; index--) {
            for (int eligibleToBuy = 0; eligibleToBuy <2; eligibleToBuy++) {
                if (eligibleToBuy == 1) {
                    dp[index][eligibleToBuy] =
                            Math.max(-prices[index] + dp[index + 1][0],
                                    dp[index + 1][1]);
                } else {
                    dp[index][eligibleToBuy] =
                            Math.max(prices[index] - fee + dp[index + 1][1],
                                    dp[index + 1][0]);
                }
            }
        }
        return dp[0][1];
    }

    private int maxProfitTabulationOptimized(int[] prices, int fee) {
        int prevNotBuy = 0, prevBuy = 0, curNotBuy = 0, curBuy = 0;
        for (int index = prices.length - 1; index >= 0; index--) {
            curBuy = Math.max(-prices[index] + prevNotBuy, prevBuy);
            curNotBuy = Math.max(prices[index] - fee + prevBuy, prevNotBuy);
            prevBuy = curBuy;
            prevNotBuy = curNotBuy;
        }
        return prevBuy;
    }

    public static void main(String[] args) {
        System.out.println(new BuySellStocksWithTransactionFee().maxProfitTabulationOptimized(new int[] {1,3,2,8,4,9}, 2));
        System.out.println(new BuySellStocksWithTransactionFee().maxProfitTabulationOptimized(new int[] {1,3,7,5,10,3}, 3));
    }
}
