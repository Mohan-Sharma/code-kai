package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class PrintLongestIncreasingSubsequence {

    public void printLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] hash = new int[dp.length];
        Arrays.fill(dp, 1);
        Arrays.fill(hash, -1);
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && 1 + dp[j] > dp[i]) {
                    dp[i] = 1 + dp[j];
                    hash[i] = j;
                    maxIndex = i;
                }
            }
        }
        // can also reverse and print
        while (maxIndex != -1) {
            System.out.print(nums[maxIndex] + ", ");
            maxIndex = hash[maxIndex];
        }
    }

    public static void main(String[] args) {
        new PrintLongestIncreasingSubsequence().printLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18});
    }
}
