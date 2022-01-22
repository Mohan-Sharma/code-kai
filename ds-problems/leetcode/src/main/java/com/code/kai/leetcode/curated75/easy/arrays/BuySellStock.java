package com.code.kai.leetcode.curated75.easy.arrays;

public class BuySellStock {

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices.length < 2) {
            return maxProfit;
        }
        int purchasedPrice = prices[0];
        for(int i=1; i<prices.length; i++) {
            int currDayPrice = prices[i];

            if(currDayPrice < purchasedPrice) {
                purchasedPrice = currDayPrice;
            } else {
                if ( (currDayPrice - purchasedPrice) > maxProfit) {
                    maxProfit = currDayPrice - purchasedPrice;
                }
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {7,2,5,1,6,4}));
    }
}
