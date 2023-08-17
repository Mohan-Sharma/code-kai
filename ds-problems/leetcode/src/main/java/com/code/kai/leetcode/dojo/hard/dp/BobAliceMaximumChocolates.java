package com.code.kai.leetcode.dojo.hard.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class BobAliceMaximumChocolates {

    public int maximumChocolates(int r, int c, int[][] grid) {
        //return maximumChocolatesBottomUp(0, 0, c - 1, grid);
        return maximumChocolatesMemoization(0, 0, c - 1, grid, new HashMap<>());
    }

    private int maximumChocolatesBottomUp(int row, int aCol, int bCol, int[][] grid) {
        if (row == grid.length - 1) {
            if (aCol == bCol)
                return grid[row][aCol]; // common chocolate
            else
                return grid[row][aCol] + grid[row][bCol];
        }

        int max = Integer.MIN_VALUE;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newACol = aCol + i, newBCol = bCol + j;
                if (newACol >= 0 && newBCol >= 0 && newACol < grid[0].length && newBCol < grid[0].length) {
                    int chocolates = maximumChocolatesBottomUp(row + 1, newACol, newBCol, grid);
                    if (aCol == bCol)
                        chocolates += grid[row][aCol];
                    else
                        chocolates += (grid[row][aCol] + grid[row][bCol]);
                    max = Math.max(max, chocolates);
                }
            }
        }
        return max;
    }

    private int maximumChocolatesMemoization(int row, int aCol, int bCol, int[][] grid, Map<String, Integer> dp) {
        if (row == grid.length - 1) {
            if (aCol == bCol)
                return grid[row][aCol]; // common chocolate
            else
                return grid[row][aCol] + grid[row][bCol];
        }

        String key = row + "" + aCol + "" + bCol;
        if (dp.containsKey(key))
            return dp.get(key);
        int max = Integer.MIN_VALUE;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newACol = aCol + i, newBCol = bCol + j;
                if (newACol >= 0 && newBCol >= 0 && newACol < grid[0].length && newBCol < grid[0].length) {
                    int chocolates = maximumChocolatesMemoization(row + 1, newACol, newBCol, grid, dp);
                    if (aCol == bCol)
                        chocolates += grid[row][aCol];
                    else
                        chocolates += (grid[row][aCol] + grid[row][bCol]);
                    max = Math.max(max, chocolates);
                }
            }
        }
        dp.put(key, max);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new BobAliceMaximumChocolates().maximumChocolates(3, 4, new int[][] {{2, 3, 1, 2}, {3, 4, 2, 2}, {5, 6, 3, 5}}));
    }
}
