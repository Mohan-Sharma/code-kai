package com.code.kai.leetcode.dojo.medium.graph;

/**
 * @author Mohan Sharma
 */
public class MaxAreaIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(sinkLand(grid, i, j), maxArea);
                }
            }
        }
        return maxArea;
    }

    int sinkLand(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != 1)
            return 0;
        int count = 1;
        grid[r][c] = 0;
        count += sinkLand(grid, r+1, c);
        count += sinkLand(grid, r-1, c);
        count += sinkLand(grid, r, c+1);
        count += sinkLand(grid, r, c-1);
        return count;
    }
}
