package com.code.kai.leetcode.curated75.easy.arrays;

public class BuySellStock {

    /*
        Problem: You can buy or sell only once. Meaning we should try to buy at min price and sell at highest price and find the max profit
        Solution: Let's say we purchased on the first day. Profit can be made only if selling price is greater than min purchased price.
        And as we move along keep track of min price
     */
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0; // same day bought and sold
        }
        int maxProfit = 0;
        int minPrice = prices[0];
        for(int i=1; i < prices.length; i++) {
            int currDayPrice = prices[i];
            maxProfit = Math.max(maxProfit, currDayPrice - minPrice); // check if profit can be maximized using today's price and last min purchased price
            minPrice = Math.min(minPrice, currDayPrice); // update min purchased price if found any
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {7,6,4,3,1}));
    }
}
