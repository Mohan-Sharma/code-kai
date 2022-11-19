package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
/*
Subsequences : Can/cannot be consecutive but should maintain the order of chars as the original string
e.g. abc - subsequences can be: ac, bc but not ca etc

Problem: Longest common subsequence
Solution of any pattern matching of 2 strings -  If character matches in both, shrink both string else shrink
first string then second string
 */
public class LongestCommonSubsequence {

    public int lcs(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return lcsBottomUPMemoizationIndexShifted(text1, text2, text1.length(), text2.length(), dp);
    }

    public static int lcsBottomUP(String text1, String text2, int i1, int i2) {
        if(i1 < 0 || i2 < 0)
            return 0;
        // case can be either both chars are equals at the index if yes both index will increment
        if (text1.charAt(i1) ==  text2.charAt(i2))
            return 1 + lcsBottomUP(text1, text2, i1 - 1, i2 - 1);
        // or increment either index once to check which gives max count since there will be branch in recursion tree
        else
            return Math.max(lcsBottomUP(text1, text2, i1 - 1, i2), lcsBottomUP(text1, text2, i1, i2 - 1));
    }

    public static int lcsBottomUPMemoization(String text1, String text2, int i1, int i2, int[][] dp) {
        if(i1 < 0 || i2 < 0)
            return 0;
        if (dp[i1][i2] != -1)
            return dp[i1][i2];
        // case can be either both chars are equals at the index if yes both index will increment
        if (text1.charAt(i1) ==  text2.charAt(i2))
            return dp[i1][i2] = 1 + lcsBottomUPMemoization(text1, text2, i1 - 1, i2 - 1, dp);
            // or increment either index once to check which gives max count since there will be branch in recursion tree
        else
            return dp[i1][i2] =
                    Math.max(lcsBottomUPMemoization(text1, text2, i1 - 1, i2, dp), lcsBottomUPMemoization(text1, text2, i1, i2 - 1, dp));
    }

    /*
        why shifted? b/c -ve index cannot be handled via array, so let's pass length instead of (length - 1) than tackle (index - 1) instead of index
        how to call this?
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return lcsBottomUPMemoizationIndexShifted(text1, text2, text1.length(), text2.length(), dp);
     */
    public static int lcsBottomUPMemoizationIndexShifted(String text1, String text2, int i1, int i2, int[][] dp) {
        if(i1 == 0 || i2 == 0)
            return 0;
        if (dp[i1][i2] != -1)
            return dp[i1][i2];
        // case can be either both chars are equals at the index if yes both index will increment
        if (text1.charAt(i1 - 1) ==  text2.charAt(i2 - 1))
            return dp[i1][i2] = 1 + lcsBottomUPMemoizationIndexShifted(text1, text2, i1 - 1, i2 - 1, dp);
            // or increment either index once to check which gives max count since there will be branch in recursion tree
        else
            return dp[i1][i2] =
                    Math.max(lcsBottomUPMemoizationIndexShifted(text1, text2, i1 - 1, i2, dp), lcsBottomUPMemoizationIndexShifted(text1, text2, i1, i2 - 1, dp));
    }

    // Use index shifting to convert to tabulation, since cannot handle negative index of memoization
    public int lcsTabulation(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // not required since already it's default value is 0
/*
        for (int i = 0; i < len1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < len2; i++) {
            dp[0][i] = 0;
        }
*/

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) ==  text2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    // or increment either index once to check which gives max count since there will be branch in recursion tree
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[len1][len2];
    }

    public int lcsTabulationSpaceOptimized(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[] dp = new int[len2 + 1];

        for (int i = 1; i <= len1; i++) {
            int[] tempDP = new int[dp.length];
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) ==  text2.charAt(j - 1))
                    tempDP[j] = 1 + dp[j - 1];
                    // or increment either index once to check which gives max count since there will be branch in recursion tree
                else
                    tempDP[j] = Math.max(dp[j], tempDP[j - 1]);
            }
            dp = tempDP;
        }
        return dp[len2];
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = 1 + dp[i+1][j+1];
                else {
                    dp[i][j] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().lcs("acebd", "abe"));
    }
}
