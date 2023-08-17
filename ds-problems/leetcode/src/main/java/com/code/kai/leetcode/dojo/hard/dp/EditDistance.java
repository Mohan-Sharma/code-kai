package com.code.kai.leetcode.dojo.hard.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Given 2 string w1 and w2 need to find the minimum number of operations to convert w1 to w2. Operations
    allowed are insert, delete and replace.

    Solution: We can start pattern matching means index by index matching e.g. w1=horse and w2=ros , f(5, 3).
    If at an index both chars match means no operations required, and we move the index of both. If they don't match we can either
    1. Insert: 1 operation assuming that insertion matched one char we move the index of w2, since the char was
    inserted at the end of w1 i.e. f(5, 2)
    2. Delete: 1 operation, assuming we deleted so let's move the index of w1 by 1 f(4, 3)
    3. Replace: 1 operation, assuming we replace with something matching both index will move by 1 f(4, 2)

    Now when we reach -ve index for w2 and w1 is not -ve yet means we need length of w1 chars to be deleted.
    if we reach -ve index for w1 and w2 is not -ve yet means we need to insert the length of chars of w2 to make it equal
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return minDistanceBottomUpMemoization(word1, word2, word1.length() - 1, word2.length() - 1, dp);
    }

    private int minDistanceBottomUp(String word1, String word2, int i, int j) {
        if (i < 0)
            return j + 1; // insert the length of w2 to make w1 == w2
        if (j < 0)
            return i + 1; // delete the length of w1 to make w1 == w2
        if (word1.charAt(i) ==  word2.charAt(j))
            return minDistanceBottomUp(word1, word2, i - 1, j - 1);
        else {
            int insert = 1 + minDistanceBottomUp(word1, word2, i, j - 1);
            int delete = 1 + minDistanceBottomUp(word1, word2, i - 1, j);
            int replace = 1 + minDistanceBottomUp(word1, word2, i - 1, j - 1);
            return Math.min(insert, Math.min(delete, replace));
        }
    }

    private int minDistanceBottomUpMemoization(String word1, String word2, int i, int j, int[][] dp) {
        if (i < 0)
            return j + 1; // insert the length of w2 to make w1 == w2
        if (j < 0)
            return i + 1; // delete the length of w1 to make w1 == w2
        if (dp[i][j] != -1)
            return dp[i][j];
        if (word1.charAt(i) ==  word2.charAt(j))
            return dp[i][j] = minDistanceBottomUpMemoization(word1, word2, i - 1, j - 1, dp);
        else {
            int insert = 1 + minDistanceBottomUpMemoization(word1, word2, i, j - 1, dp);
            int delete = 1 + minDistanceBottomUpMemoization(word1, word2, i - 1, j, dp);
            int replace = 1 + minDistanceBottomUpMemoization(word1, word2, i - 1, j - 1, dp);
            return dp[i][j] = Math.min(insert, Math.min(delete, replace));
        }
    }

    private int minDistanceTabulation(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len2 ; i++) {
            dp[0][i] = i; // since using 1 based indexing now | first row all cols
        }
        for (int i = 0; i <= len1 ; i++) {
            dp[i][0] = i; // all rows first col
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) ==  word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    int insert = 1 + dp[i][j - 1];
                    int delete = 1 + dp[i - 1][j];
                    int replace = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return dp[len1][len2];
    }

    private int minDistanceTabulationSpaceOptimized(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2 + 1];

        for (int i = 0; i <= len2 ; i++) {
            dp[i] = i; // first row all columns
        }

        for (int i = 1; i <= len1; i++) {
            int[] tempDP = new int[dp.length];
            tempDP[0] = i; // all rows first col
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) ==  word2.charAt(j - 1))
                    tempDP[j] = dp[j - 1];
                else {
                    int insert = 1 + tempDP[j - 1];
                    int delete = 1 + dp[j];
                    int replace = 1 + dp[j - 1];
                    tempDP[j] = Math.min(insert, Math.min(delete, replace));
                }
            }
            dp = tempDP;
        }
        return dp[len2];
    }


    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistanceTabulationSpaceOptimized("horse", "ros"));
    }
}
