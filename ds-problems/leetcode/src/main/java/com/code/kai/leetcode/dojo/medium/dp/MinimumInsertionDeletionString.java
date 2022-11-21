package com.code.kai.leetcode.dojo.medium.dp;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Given two strings, we are allowed to insert and deleted only 1 character at a time. Return
    minimum number of insertions/deletions required to convert string1 to string2.

    Solution: We know Longest Common Subsequence will give the matching subsequence between both string
    Let's say s1 length is 5 s2 length is 4 and LCS is 3. From this we can make out that we need to delete
    2 characters from s1 and insert 1 character to s1 to make it s2. e.g s1 = abcde s2 = abcf LCS = abc
    so deleted d, e from s1 and insert f to make s1 == s2
 */
public class MinimumInsertionDeletionString {

    public static int canYouMake(String str, String ptr) {
        int len1 = str.length();
        int len2 = ptr.length();
        int lcs = LCS(str, ptr, len1, len2);
        return len1 - lcs + len2 - lcs;
    }

    private static int LCS(String text1, String text2, int len1, int len2) {
        int[] dp = new int[len2 + 1];
        for (int i = 1; i <= len1; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) ==  text2.charAt(j - 1))
                    tempDP[j] = 1 + dp[j - 1];
                else
                    tempDP[j] = Math.max(dp[j], tempDP[j - 1]);
            }
            dp = tempDP;
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        System.out.println(canYouMake("aa", "aaa"));
    }
}
