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

    public static void main(String[] args) {
        System.out.println(countPartitions(4, 0, new int[] {1, 1, 1, 1}));
    }
}
