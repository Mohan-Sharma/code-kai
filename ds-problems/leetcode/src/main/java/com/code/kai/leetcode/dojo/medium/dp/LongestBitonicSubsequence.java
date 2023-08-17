package com.code.kai.leetcode.dojo.medium.dp;

/**
 * @author Mohan Sharma
 */
/*
    problem: Bitonic is a subsequence where it is either increasing or decreasing or increasing and then decreasing.
    e.g. Given {1, 11, 2, 10, 4, 5, 2, 1} one of -
    Increasing bitonic - {1, 2, 4, 5}
    Decreasing bitonic - {11, 10, 5, 2, 1}
    Increasing and then decreasing bitonic - {1, 2, 10, 5, 2, 1}

    Intuition: Answer may be the longest increasing subsequence, so maybe we can use LIS. But answer can be the longest decreasing as well,
    so we can find LIS from backside which is equivalent to decreasing from front side. But the answer can be increasing and then decreasing as well,
    so we know LIS dp gives us the max length of LIS till that index similarly decreasing LIS dp will give max length of decreasing LIS at that index
    so what if we subtract both the value at an index and take the max of it?
 */
public class LongestBitonicSubsequence {

    public int longestBitonicSequence(int[] arr) {
        int length = arr.length;
        int[] forwardDP = new int[length];
        for (int i = 0; i < length; i++) {
            forwardDP[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && 1 + forwardDP[j] > forwardDP[i])
                    forwardDP[i] = 1 + forwardDP[j];
            }
        }

        int[] backwardDP = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            backwardDP[i] = 1;
            for (int j = length - 1; j > i; j--) {
                if (arr[i] > arr[j] && 1 + backwardDP[j] > backwardDP[i])
                    backwardDP[i] = 1 + backwardDP[j];
            }
        }
        int result = 0;
        for (int i = 0; i < length; i++) {
            // till index i forward[i] gives increasing till i and reverse[i] gives decreasing from i till n, hence ith element is common and taken 2 times hence take only 1 time so do a minus 1
            result = Math.max(result, Math.abs(forwardDP[i] + backwardDP[i] - 1));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LongestBitonicSubsequence().longestBitonicSequence(new int[] {1, 11, 2, 10, 4, 5, 2, 1}));
    }
}
