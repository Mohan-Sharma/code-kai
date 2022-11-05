package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class TriangleMinimumPathSum {

    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        //return minimumTotalBottomUp(triangle, 0, 0);
        return minimumTotalBottomUpMemoization(triangle, 0, 0, dp);
    }

    private int minimumTotalBottomUp(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size() - 1)
            return triangle.get(row).get(col);

        Integer current = triangle.get(row).get(col);
        int down = current + minimumTotalBottomUp(triangle, row + 1, col);
        int diag = current + minimumTotalBottomUp(triangle, row + 1, col + 1);

        return Math.min(down, diag);
    }

    private int minimumTotalBottomUpMemoization(List<List<Integer>> triangle, int row, int col, int[][] dp) {
        if (row == triangle.size() - 1)
            return triangle.get(row).get(col);

        if (dp[row][col] != -1)
            return dp[row][col];
        Integer current = triangle.get(row).get(col);
        int down = current + minimumTotalBottomUpMemoization(triangle, row + 1, col, dp);
        int diag = current + minimumTotalBottomUpMemoization(triangle, row + 1, col + 1, dp);
        int min = Math.min(down, diag);
        dp[row][col] = min;
        return min;
    }

    private int minimumTotalTopDownTabulation(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            dp[triangle.size() - 1][i] = triangle.get(triangle.size() - 1).get(i);
        }

        for (int i = triangle.size() - 2; i >= 0 ; i--) {
            int colSize = triangle.get(i).size();
            for (int j = 0; j < colSize; j++) {
                Integer current = triangle.get(i).get(j);
                int down = current + dp[ i + 1][j];
                int diag = current + dp[ i + 1][j + 1];
                int min = Math.min(down, diag);
                dp[i][j] = min;
            }
        }
        return dp[0][0];
    }

    private int minimumTotalTopDownSpaceOptimized(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.get(triangle.size() - 1).size()];
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }

        for (int i = triangle.size() - 2; i >= 0 ; i--) {
            int colSize = triangle.get(i).size();
            int[] tempDP = new int[dp.length];
            for (int j = 0; j < colSize; j++) {
                Integer current = triangle.get(i).get(j);
                int down = current + dp[j];
                int diag = current + dp[j + 1];
                int min = Math.min(down, diag);
                tempDP[j] = min;
            }
            dp = tempDP;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new TriangleMinimumPathSum().minimumTotalTopDownSpaceOptimized(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
    }
}
