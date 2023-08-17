package com.code.kai.leetcode.dojo.hard.dp;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Given two strings return if they match. Conditions are - if a character contains ? it should match only
    1 char, if char is * it can match multiple chars
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        //return isMatchBottomUp(s, p, s.length() - 1, p.length() - 1);
        int len1 = s.length();
        int len2 = p.length();
        Boolean[][] dp = new Boolean[len1 + 1][len2 + 1];
        return isMatchBottomUpMemoizationIndexShifted(s, p, len1, len2, dp);
    }

    private boolean isMatchBottomUp(String s, String p, int i, int j) {
        if (j < 0 && i < 0) return true;
        if (j < 0) return false;
        if (i < 0) {
            for (int k = 0; k < j; k++) {
                if (p.charAt(k) != '*')
                    return false;
            }
            return true;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
            return isMatchBottomUp(s, p, i - 1, j - 1);
        else if (p.charAt(j) == '*') {
            return isMatchBottomUp(s, p, i - 1, j) || isMatchBottomUp(s, p, i, j - 1);
        } else {
            return false;
        }
    }

    private boolean isMatchBottomUpMemoization(String s, String p, int i, int j, Boolean[][] dp) {
        if (j < 0 && i < 0) return true;
        if (j < 0) return false;
        if (i < 0) {
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*')
                    return false;
            }
            return true;
        }

        if (dp[i][j] != null)
            return dp[i][j];
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
            return dp[i][j] = isMatchBottomUpMemoization(s, p, i - 1, j - 1, dp);
        else if (p.charAt(j) == '*') {
            return dp[i][j] = isMatchBottomUpMemoization(s, p, i - 1, j, dp) || isMatchBottomUpMemoization(s, p, i, j - 1, dp);
        } else {
            return dp[i][j] = false;
        }
    }

    private boolean isMatchBottomUpMemoizationIndexShifted(String s, String p, int i, int j, Boolean[][] dp) {
        if (j == 0 && i == 0) return true;
        if (j == 0) return false;
        if (i == 0) {
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*')
                    return false;
            }
            return true;
        }

        if (dp[i][j] != null)
            return dp[i][j];
        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
            return dp[i][j] = isMatchBottomUpMemoizationIndexShifted(s, p, i - 1, j - 1, dp);
        else if (p.charAt(j - 1) == '*') {
            return dp[i][j] = isMatchBottomUpMemoizationIndexShifted(s, p, i - 1, j, dp) || isMatchBottomUpMemoizationIndexShifted(s, p, i, j - 1, dp);
        } else {
            return dp[i][j] = false;
        }
    }

    private boolean isMatchTabulation(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;

        for (int i = 0; i <= len2; i++) {
            boolean flag = true;
            for (int k = 1; k <= i; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[0][i] = flag;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(new WildcardMatching().isMatchTabulation("aa", "**aa"));
    }
}
