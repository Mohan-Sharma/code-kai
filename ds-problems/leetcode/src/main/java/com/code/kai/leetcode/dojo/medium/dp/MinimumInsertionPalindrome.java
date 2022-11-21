package com.code.kai.leetcode.dojo.medium.dp;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Count minimum number of characters inserted to make the string palindrome
    Solution: We know to make a string palindrome, just reverse the string and append to original string.
    But in this case the number of chars will be the length of the string.
    We can do something better - if we know the longest palindromic subsequence keeping that aside we
    can reverse the remaining subsequence and insert them. e.g. abcxyyxcd in this example the longest palindromic
    subsequence is cxyyxc lets take ab and d apart and reverse and insert them to make palindromic
    e.g. abd cxyyxc dba in short we inserted (length - LPS length)
 */
public class MinimumInsertionPalindrome {

    public static int minInsertion(String str) {
        int lps = lcs(str);
        return str.length() - lps;
    }

    private static int lcs(String s) {
        String rs = new StringBuilder(s).reverse().toString();
        int len1 = s.length(), len2 = rs.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == rs.charAt(j - 1))
                    dp[i][j] = 1 + dp[i -1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[len1][len2];
    }
}
