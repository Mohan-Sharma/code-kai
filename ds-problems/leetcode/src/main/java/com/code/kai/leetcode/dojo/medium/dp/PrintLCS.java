package com.code.kai.leetcode.dojo.medium.dp;

import com.code.kai.leetcode.curated75.medium.dp.LongestCommonSubsequence;
import java.util.Arrays;

/**
 * {@link LongestCommonSubsequence}
 */
public class PrintLCS {
    public String lcsTabulation(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        String[][] dp = new String[len1 + 1][len2 + 1];
        for (String[] row : dp) {
            Arrays.fill(row, "");
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) ==  text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + text1.charAt(i -1) ;
                else
                    dp[i][j] =  dp[i - 1][j].length() >= dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
            }
        }
        return dp[len1][len2];
    }

    public String lcsUsingDPArray(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        populateLCSDpArray(text1, text2, dp);
        int i = 0, j = 0;
        StringBuilder str = new StringBuilder();
        while (i < len1 && j < len2) {
            if (text1.charAt(i) == text2.charAt(j)) {
                str.append(text1.charAt(i));
                i++;
                j++;
            } else if (dp[i+1][j] > dp[i][j+1]) {
                i++;
            } else j++;
        }
        return str.toString();
    }

    // If we start from 0 and go to end, lcs will be at the last index and to construct the string
    // we need to start from the last index dp[n1][n2] and come up to 0 index to get the string in
    // reverse order. Hence it's better if we get the lcs at dp[0][0] by starting from end of string
    // and construct the string from front to end to get the string in correct order not reversed order.
    public void populateLCSDpArray(String text1, String text2, int[][] dp) {
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
        System.out.println(new PrintLCS().lcsUsingDPArray("abmamaz", "zamamba"));
    }
}
