package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Buy and sell stocks to make max profit. Constraint is that you cannot buy unless you sell if you already bought.
    Solution: Use a flag to track bought or not. If bought == 1 means eligible to buy we have two options, either I buy at the
    current price and move the index to next and bought becomes 0 now, so it's eligible to sell, or I don't buy and just increment the index
    When bought == 0, I have two options, either I sell at the current price and bought becomes 1 again, or I tend to sell at the next index
 */
public class BuySellStockII {
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
            int notBuy = maxProfitBottomUp(prices, index + 1, eligibleToBuy);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfitBottomUp(prices, index + 1, 1);
            int notSell = maxProfitBottomUp(prices, index + 1, eligibleToBuy);
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
            int notBuy = maxProfitBottomUpMemoization(prices, index + 1, eligibleToBuy, dp);
            return dp[index][eligibleToBuy] = Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + maxProfitBottomUpMemoization(prices, index + 1, 1, dp);
            int notSell = maxProfitBottomUpMemoization(prices, index + 1, eligibleToBuy, dp);
            return dp[index][eligibleToBuy] = Math.max(sell, notSell);
        }
    }


    private int maxProfitTabulation(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len + 1][2];

        for (int i = len - 1; i >= 0; i--) {
            for (int eligibleToBuy : List.of(0, 1)) {
                if (eligibleToBuy == 1) {
                    int buy = -prices[i] + dp[i + 1][0];
                    int notBuy = dp[i + 1][1];
                    dp[i][eligibleToBuy] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] + dp[i + 1][1];
                    int notSell = dp[i + 1][0];
                    dp[i][eligibleToBuy] = Math.max(sell, notSell);
                }
            }
        }
        return dp[0][1];
        // why not dp[0][0]? since the first purchase will happen when it is 1 not 0 meaning for the 0th indexed price purchased happened only
        // when eligible to buy is 1 so return the result from dp[0][1]. dp[0][0] is something like you did not even trade yet how will profit happen
    }

    private int maxProfitTabulationSpaceOptimized(int[] prices) {
        int len = prices.length;
        int[] dp = new int[2];

        for (int i = len - 1; i >= 0; i--) {
            int[] tempDP = new int[dp.length];
            for (int eligibleToBuy : List.of(0, 1)) {
                if (eligibleToBuy == 1) {
                    int buy = -prices[i] + dp[0];
                    int notBuy = dp[1];
                    tempDP[eligibleToBuy] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] + dp[1];
                    int notSell = dp[0];
                    tempDP[eligibleToBuy] = Math.max(sell, notSell);
                }
            }
            dp = tempDP;
        }
        return dp[1];
        // why not dp[0][0]? since the first purchase will happen when it is 1 not 0 meaning for the 0th indexed price purchased happened only
        // when eligible to buy is 1 so return the result from dp[0][1]. dp[0][0] is something like you did not even trade yet how will profit happen
    }


    private int maxProfitTabulationSpaceOptimizedFurther(int[] prices) {
        int len = prices.length;
        //int[] dp = new int[2]; create 2 vars
        int prevNotBuy = 0; // equivalent to dp[0]
        int prevBuy = 0; // equivalent to dp[1]
        int curNotBuy, curBuy;
        for (int i = len - 1; i >= 0; i--) {
            // not required for loop b/c there are only 2 iteration and each gets called for each value of i
            // so for each i let's directly compute 2 values
            //for (int eligibleToBuy : List.of(0, 1)) {
                // simulate buy scenario
                curBuy = Math.max(-prices[i] + prevNotBuy, prevBuy);
                /*if (eligibleToBuy == 1) {
                    int buy = -prices[i] + dp[0];
                    int notBuy = dp[1];
                    tempDP[eligibleToBuy] = Math.max(buy, notBuy);
                }*/
                /*else {
                    int sell = prices[i] + dp[1];
                    int notSell = dp[0];
                    tempDP[eligibleToBuy] = Math.max(sell, notSell);
                }*/
                // simulate not buy or sell scenario
                curNotBuy = Math.max(prices[i] + prevBuy, prevNotBuy);
            //}
            prevBuy = curBuy;
            prevNotBuy = curNotBuy;
        }
        return prevBuy;
    }


    public static void main(String[] args) {
        System.out.println(new BuySellStockII().maxProfitTabulationSpaceOptimizedFurther(new int[] {7,1,5,3,6,4}));
    }
}
