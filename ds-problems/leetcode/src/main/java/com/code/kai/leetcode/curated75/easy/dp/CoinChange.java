package com.code.kai.leetcode.curated75.easy.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class CoinChange {

    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        int maxIndex = coins.length - 1; //2
        int oldMaxIndex = maxIndex; //2
        int tempAmount = amount; //6
        int maxCoin = coins[maxIndex--]; //5
        while (tempAmount > 0) {
            if (tempAmount >= maxCoin) {
                tempAmount  -= maxCoin; //1
                count++;
            } else {
                if (maxIndex < 0) { //1
                    if (oldMaxIndex < 0)
                        return -1;
                    tempAmount = amount;
                    count = 0;
                    maxCoin = coins[--oldMaxIndex]; //1
                    maxIndex = oldMaxIndex - 1; //0
                } else {
                    maxCoin = coins[maxIndex--]; //0
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[] {2 , 4, 5}, 6));
    }
}
