package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class BuySellStocksWithCooldown {

    public int maxProfit(int[] prices) {
        //return maxProfitBottomUp(prices, 0, 1);
        int[][] dp = new int[prices.length][2];
        for (int[] row : dp) {
            Arrays.fill(row,  -1);
        }
        return maxProfitBottomUpMemoization(prices, 0, 1, dp);
    }

    private int maxProfitBottomUp(int[] prices, int index, int eligibleToBuy) {
        if (index >= prices.length)
            return 0;
        if (eligibleToBuy == 1) {
            int buy = -prices[index] + maxProfitBottomUp(prices, index + 1, 0);
            int notBuy = maxProfitBottomUp(prices, index + 1, 1);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfitBottomUp(prices, index + 2,  1);
            int notSell = maxProfitBottomUp(prices, index + 1, 0);
            return Math.max(sell, notSell);
        }
    }

    private int maxProfitBottomUpMemoization(int[] prices, int index, int eligibleToBuy, int[][] dp) {
        if (index >= prices.length)
            return 0;
        if (dp[index][eligibleToBuy] != -1)
            return dp[index][eligibleToBuy];
        if (eligibleToBuy == 1) {
            int buy = -prices[index] + maxProfitBottomUpMemoization(prices, index + 1, 0, dp);
            int notBuy = maxProfitBottomUpMemoization(prices, index + 1, 1, dp);
            return dp[index][eligibleToBuy] = Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfitBottomUpMemoization(prices, index + 2,  1, dp);
            int notSell = maxProfitBottomUpMemoization(prices, index + 1, 0, dp);
            return dp[index][eligibleToBuy] = Math.max(sell, notSell);
        }
    }

    private int maxProfitTabulation(int[] prices) {
        int[][] dp = new int[prices.length + 2][2];
        for (int index = prices.length - 1; index >= 0 ; index--) {
            for (int eligibleToBuy = 0; eligibleToBuy < 2; eligibleToBuy++) {
                if (eligibleToBuy == 1) {
                    int buy = -prices[index] + dp[index + 1][0];
                    int notBuy = dp[index + 1][1];
                    dp[index][eligibleToBuy] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[index] + dp[index + 2][1];
                    int notSell = dp[index + 1][0];
                    dp[index][eligibleToBuy] = Math.max(sell, notSell);
                }
            }
        }
        return dp[0][1];
    }

    private int maxProfitTabulationOptimized(int[] prices) {
        int prevBuy = 0, prevOfPrevBuy = 0, prevNotBuy = 0, curBuy = 0, curNotBuy = 0;
        for (int index = prices.length - 1; index >= 0 ; index--) {
            curBuy = Math.max(-prices[index] + prevNotBuy, prevBuy);
            curNotBuy = Math.max(prices[index] + prevOfPrevBuy, prevNotBuy);
            prevOfPrevBuy = prevBuy;
            prevBuy = curBuy;
            prevNotBuy = curNotBuy;
        }
        return prevBuy;
    }

    public static void main(String[] args) {
        System.out.println(new BuySellStocksWithCooldown().maxProfitTabulationOptimized(new int[] {1,2,3,0,2}));
    }
}
