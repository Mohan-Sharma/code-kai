package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class LongestCommonSubstring {

    /*
        Taking the logic from Longest Common subsequence we find that if we create the dp array and plot
        the two strings along x and y axis
          0    1  2  3  4
               a  b  z  d
          1  a 1  0  0  0
          3  b 0  2  0  0
          4  c 0  0  0  0
          5  d 0  0  0  1

          If you see the matrix we realise that if char at ith and jth index are not same then we simply ignore
          the previous state(or matched substring) since we need to re-start the count again since there was a mismatch
          but if 2 adjacent characters are equal than previous diagonal value will be non-zero. In this example, since
          first index of both string where equal we populate dp with 1, now when second character was also equals we needed
          previous state hence it was previous state + 1(current match)
     */
    public int maxLCS(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i -1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                } else
                    // can ignore this as well since default is already 0
                    dp[i][j] = 0;
            }
        }
        return max;
    }

    public String lcs(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        String[][] dp = new String[len1 + 1][len2 + 1];
        for (String[] row : dp) {
            Arrays.fill(row, "");
        }
        String str = "";
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i -1) == str2.charAt(j - 1)) {
                    dp[i][j] =  dp[i - 1][j - 1] + str1.charAt(i -1);
                    if (dp[i][j].length() > str.length())
                        str = dp[i][j];
                }
            }
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubstring().lcs("bbbaaaabaa", "abbbbbabā"));
    }
}
