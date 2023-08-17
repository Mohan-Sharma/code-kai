package com.code.kai.leetcode.dojo.medium.dp;

/**
 * @author Mohan Sharma
 */
/*
    Problem given 2 strings print the shortest common sequence such that all characters of both string appears in result
    e.g. s1=brute s2=groot result=bgruoote
    Solution user print LCS logic from DP Array
 */
public class ShortestCommonSupersequence {

    public static String shortestSupersequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        populateLCSDpArray(text1, text2, dp);
        int i = 0, j = 0;
        StringBuilder str = new StringBuilder();
        while (i < len1 && j < len2) {
            // both chars are equal take anyone and diagonally
            if (text1.charAt(i) == text2.charAt(j)) {
                str.append(text1.charAt(i));
                i++;
                j++;
            }
            // move to the cell from where max value came and capture the char
            else if (dp[i+1][j] > dp[i][j+1]) {
                str.append(text1.charAt(i));
                i++;
            } else {
                str.append(text2.charAt(j));
                j++;
            }
        }
        while (i < len1) {
            str.append(text1.charAt(i));
            i++;
        }
        while (j < len2) {
            str.append(text2.charAt(j));
            j++;
        }
        return str.toString();
    }

    public static void populateLCSDpArray(String text1, String text2, int[][] dp) {
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) ==  text2.charAt(j))
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                    // or increment either index once to check which gives max count since there will be branch in recursion tree
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(shortestSupersequence("brute","groot"));
    }
}
