package com.code.kai.leetcode.dojo.hard.dp;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Very similar to BuySellStockIII only difference is that there we were allowed to do 2 buy-sell
    here we can do k buy-sell

    Solution: Since we can do K buy-sell means we can do K*2 transactions e.g. t0, t1... tk-1, where t0 and t1 constitute
    one buy-sell transaction. So to do K buy-sell transactions we would need K*2 transactions. To difference buy-sell we
    would use transaction % 2 == 0 logic. Meaning every even transaction is eligible to buy and old are eligible to sell
 */
public class BuySellStockIV {

    public int maxProfit(int k, int[] prices) {
        return maxProfitBottomUp(k, prices, 0, 0);
    }

    private int maxProfitBottomUp(int k, int[] prices, int index, int transaction) {
        if (transaction >= k * 2 || index >= prices.length )
            return 0;
        // eligible to buy, then either i will buy it or skip and buy at next day price
        if (transaction % 2 == 0) {
            // when we do tran + 1 means it becomes odd now hence eligible to sell
            int buy = -prices[index] + maxProfitBottomUp(k, prices, index + 1, transaction + 1);
            int notBuy = maxProfitBottomUp(k, prices, index + 1, transaction);
            return Math.max(buy, notBuy);
        } else {
            // current trans is odd when we do tran + 1 means it becomes even now hence eligible to buy
            int sell = prices[index] + maxProfitBottomUp(k, prices, index + 1, transaction + 1);
            int notSell = maxProfitBottomUp(k, prices, index + 1, transaction);
            return Math.max(sell, notSell);
        }
    }

    private int maxProfitTabulation(int k, int[] prices) {
        int[][] dp = new int[prices.length + 1][k*2 + 1];
        for (int index = prices.length - 1; index >= 0; index--) {
            for (int transaction = k*2 - 1; transaction >= 0; transaction--) {
                // eligible to buy, then either index will buy it or skip and buy at next day price
                if (transaction % 2 == 0) {
                    // when we do tran + 1 means it becomes odd now hence eligible to sell
                    int buy = -prices[index] + dp[index + 1][transaction + 1];
                    int notBuy = dp[index + 1][transaction];
                    dp[index][transaction] = Math.max(buy, notBuy);
                } else {
                    // current trans is odd when we do tran + 1 means it becomes even now hence eligible to buy
                    int sell = prices[index] + dp[index + 1][transaction + 1];
                    int notSell = dp[index + 1][transaction];
                    dp[index][transaction] = Math.max(sell, notSell);
                }

            }
        }
        return dp[0][0];
    }

    private int maxProfitTabulationSpaceOptimized(int k, int[] prices) {
        int[] dp = new int[k*2 + 1];
        for (int index = prices.length - 1; index >= 0; index--) {
            int[] tempDP = new int[dp.length];
            for (int transaction = k*2 - 1; transaction >= 0; transaction--) {
                // eligible to buy, then either index will buy it or skip and buy at next day price
                if (transaction % 2 == 0) {
                    // when we do tran + 1 means it becomes odd now hence eligible to sell
                    int buy = -prices[index] + dp[transaction + 1];
                    int notBuy = dp[transaction];
                    tempDP[transaction] = Math.max(buy, notBuy);
                } else {
                    // current trans is odd when we do tran + 1 means it becomes even now hence eligible to buy
                    int sell = prices[index] + dp[transaction + 1];
                    int notSell = dp[transaction];
                    tempDP[transaction] = Math.max(sell, notSell);
                }
            }
            dp = tempDP;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new BuySellStockIV().maxProfitTabulationSpaceOptimized(2, new int[] {3,2,6,5,0,3}));
    }
}
