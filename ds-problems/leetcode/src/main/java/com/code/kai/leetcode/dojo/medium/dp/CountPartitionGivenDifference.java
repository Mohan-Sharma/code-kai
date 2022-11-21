package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class CountPartitionGivenDifference {

    public static int countPartitions(int n, int d, int[] arr) {
        /*
            given s1 - s2 = d
            using s1 + s2 = total
            total - s2 - s2 = d
            s2 = (total - d)/2
            we could have taken s1 - ( total - s1) = d => s1 = (d + total)/2
            which would mean that we would need a large size target array in dp
         */
        int sum = Arrays.stream(arr).sum();
        int s2 = (sum - d)/2;
        /*
            since every elements are >= 0 subset sum(s2) cannot be -ve hence (sum - d) cannot be -ve
            similarly s2 sum also cannot be fraction hence (sum - d) will be even always
         */
        return new CountSubsetSumK().countSubsetBottomUpTabulation(arr, s2);
    }

    private static void populateDP(int[] arr, int sum, boolean[][] dp, int n) {
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                boolean notPick = dp[i - 1][j];
                boolean pick = false;
                if (j >= arr[i])
                    pick = dp[i -1][j - arr[i]];
                dp[i][j] = pick || notPick;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(countPartitions(4, 0, new int[] {1, 1, 1, 1}));
    }
}
