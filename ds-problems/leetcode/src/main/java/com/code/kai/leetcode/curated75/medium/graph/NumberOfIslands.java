package com.code.kai.leetcode.curated75.medium.graph;

/**
 * @author Mohan Sharma
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int rows = grid.length, columns = grid[0].length, noOfIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == '1') {
                    sinkLand(grid, i, j);
                    noOfIslands++;
                }
            }
        }
        return noOfIslands;
    }

    private void sinkLand(char[][] islands, int row, int col) {
        if (row < 0 || col < 0 || row >= islands.length || col >= islands[0].length || islands[row][col] != '1')
            return;
        islands[row][col] = '0';

        sinkLand(islands, row + 1, col);
        sinkLand(islands, row - 1, col);
        sinkLand(islands, row, col + 1);
        sinkLand(islands, row, col - 1);
    }
}
