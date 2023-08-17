package com.code.kai.leetcode.dojo.hard.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Given two strings s1 and s2 check how many subsequence of s2 occurs in s1
    Solution: Compare character by character using recursion. If at some index both characters matches we have
    2 choices either take the character and move index of both strings or ignore to find similar char in future and
    move the index of s1 to next index. If they don't match proceed to next index of both strings
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return numDistinctBottomUpMemoization(s, t, s.length() - 1, t.length() - 1, dp);
    }

    private int numDistinctBottomUp(String s, String t, int i, int j) {
        if (j < 0) // second string all chars matched and overbound now
            return 1;
        if (i < 0) // second string is not exhausted but first string is exhausted
            return 0;
        if (s.charAt(i) ==  t.charAt(j)) {
            // since both chars matched we will pick the char and move both index
            int pick =  numDistinctBottomUp(s, t, i - 1, j - 1);
            // since both chars matched we will not pick the char and see if in future some char exists which matches
            int notPick = numDistinctBottomUp(s, t, i - 1, j);
            return pick + notPick; // since sum of all subsequences
        } else
            return numDistinctBottomUp(s, t, i - 1, j);
    }

    private int numDistinctBottomUpMemoization(String s, String t, int i, int j, int[][] dp) {
        if (j < 0) // second string all chars matched and overbound now
            return 1;
        if (i < 0) // second string is not exhausted but first string is exhausted
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int count = 0;
        if (s.charAt(i) ==  t.charAt(j)) {
            // since both chars matched we will pick the char and move both index
            int pick =  numDistinctBottomUp(s, t, i - 1, j - 1);
            // since both chars matched we will not pick the char and see if in future some char exists which matches
            int notPick = numDistinctBottomUp(s, t, i - 1, j);
            count = pick + notPick; // since sum of all subsequences
        } else
            count = numDistinctBottomUp(s, t, i - 1, j);
        dp[i][j] = count;
        return count;
    }

    private int numDistinctTabulation(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (i == 0)
                    continue;
                if (s.charAt(i - 1) ==  t.charAt(j - 1)) {
                    // since both chars matched we will pick the char and move both index
                    int pick =  dp[i - 1][j - 1];
                    // since both chars matched we will not pick the char and see if in future some char exists which matches
                    int notPick = dp[i - 1][j];
                    dp[i][j] = pick + notPick; // since sum of all subsequences
                } else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[len1][len2];
    }

    // if we analyse the code above we see that a present col value depends either on sum of present col dp[j] + preceeding col dp[j - 1]
    // or present col. Hence, with one array it will work
    private int numDistinctTabulationSpaceOptimized(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int[] dp = new int[len2 + 1];
        dp[0] = 1;
        for (int i = 1; i <= len1; i++) {
            for (int j = len2; j > 0; j--) {
                if (s.charAt(i - 1) ==  t.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequences().numDistinctTabulation("babgbag", "bag"));
    }
}
