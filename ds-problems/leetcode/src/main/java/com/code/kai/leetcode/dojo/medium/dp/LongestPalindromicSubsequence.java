package com.code.kai.leetcode.dojo.medium.dp;

/**
 * @author Mohan Sharma
 */
public class LongestPalindromicSubsequence {

    /*
        We know a palindrome string will be same if we reverse it.
        Now if we reverse the string s to say s' and find the Longest common subsequence of both s and s'
        we will get our ans. Why? let's say s=aabcbd and s'=dbcbaa. Now LCS(s, s') will always be bcb. There
        will be aa palindromic as well but not longest.
     */
    public int longestPalindromeSubsequence(String s) {
        String r = new StringBuilder(s).reverse().toString();
        return longestCommonSubstring(s, r);
    }

    private static int longestCommonSubsequence(String s, String s1) {
        int len1 = s.length(), len2 = s1.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == s1.charAt(j - 1))
                    dp[i][j] = 1 + dp[i -1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[len1][len2];
    }

    private static int longestCommonSubstring(String s, String r) {
        int len1 = s.length(), len2 = r.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == r.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
                else
                    dp[i][j] = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubsequence("abmamaz"));
    }
}
