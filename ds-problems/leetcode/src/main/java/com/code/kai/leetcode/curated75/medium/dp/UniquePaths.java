package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class UniquePaths {

    public static int uniquePaths(int m, int n) {
        return uniquePathsBottomUp(m, n);
    }

    private static int uniquePathsTopDown(int m, int n, int[][] dp) {
        int count = 0;
        if (m <= 0 || n <= 0)
            return 0;
        if (m == 1 && n == 1)
            return 1;
        if (dp[m][n] != 0)
            return dp[m][n];
        count += uniquePathsTopDown(m-1, n, dp);
        dp[m][n] = count;
        count += uniquePathsTopDown(m, n - 1, dp);
        dp[m][n] = count;
        return count;
    }

    private static int uniquePathsBottomUp(int m, int n) {
        int[] row = new int[n];
        Arrays.fill(row, 1);

        for (int i = 0; i < m-1; i++) {
            int[] newRow = new int[n];
            newRow[n-1] = 1;
            for (int j = n-2; j >= 0; j--) {
                newRow[j] = newRow[j+1] + row[j];
            }
            row = newRow;
        }
        return row[0];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
    }
}
