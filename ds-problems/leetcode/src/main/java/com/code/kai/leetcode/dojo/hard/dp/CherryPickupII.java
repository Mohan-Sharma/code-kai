package com.code.kai.leetcode.dojo.hard.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class CherryPickupII {

    public int cherryPickup(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][][] dp = new int[rows][cols][cols];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }
        return cherryPickupBottomUpMemoization(grid, 0, 0, cols - 1, dp);
    }

    public int cherryPickupBottomUp(int[][] grid, int row, int col1, int col2) {
        if (row >= grid.length || col1 < 0 || col2 < 0 || col1 >= grid[0].length  || col2 >= grid[0].length)
            return Integer.MIN_VALUE;
        if (row == grid.length - 1) {
            return col1 == col2 ? grid[row][col1] : grid[row][col1] + grid[row][col2];
        }

        int max = Integer.MIN_VALUE;
        for (int i : List.of(-1, 0, 1)) {
            for (int j  : List.of(-1, 0, 1)) {
                int firstRobo = grid[row][col1];
                int secondRobo = grid[row][col2];
                int prevMax = cherryPickupBottomUp(grid, row + 1, col1 + i , col2 + j);
                if (col1 ==  col2) {
                    if (prevMax != Integer.MIN_VALUE)
                        prevMax += firstRobo;
                } else {
                    if (prevMax != Integer.MIN_VALUE)
                        prevMax = prevMax + firstRobo + secondRobo;
                }
                max = Math.max(prevMax, max);
            }
        }
        return max;
    }

    public int cherryPickupBottomUpMemoization(int[][] grid, int row, int col1, int col2, int[][][] dp) {
        if (row >= grid.length || col1 < 0 || col2 < 0 || col1 >= grid[0].length  || col2 >= grid[0].length)
            return Integer.MIN_VALUE;
        if (row == grid.length - 1) {
            return col1 == col2 ? grid[row][col1] : grid[row][col1] + grid[row][col2];
        }
        if (dp[row][col1][col2] != -1)
            return dp[row][col1][col2];
        int max = Integer.MIN_VALUE;
        for (int i : List.of(-1, 0, 1)) {
            for (int j  : List.of(-1, 0, 1)) {
                int firstRobo = grid[row][col1];
                int secondRobo = grid[row][col2];
                int prevMax = cherryPickupBottomUpMemoization(grid, row + 1, col1 + i , col2 + j, dp);
                if (col1 ==  col2) {
                    if (prevMax != Integer.MIN_VALUE)
                        prevMax += firstRobo;
                } else {
                    if (prevMax != Integer.MIN_VALUE)
                        prevMax = prevMax + firstRobo + secondRobo;
                }
                max = Math.max(prevMax, max);
            }
        }
        dp[row][col1][col2] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new CherryPickupII().cherryPickup(new int[][]{{3,1,1}, {2,5,1}, {1,5,5}, {2,1,1}}));
    }
}
