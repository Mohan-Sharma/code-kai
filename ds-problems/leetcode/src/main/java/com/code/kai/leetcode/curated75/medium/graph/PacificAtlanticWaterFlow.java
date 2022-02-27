package com.code.kai.leetcode.curated75.medium.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int rows = heights.length, columns = heights[0].length;
        boolean[][] pacific =  new boolean[rows][columns];
        boolean[][] atlantic =  new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            populateVisitMatrix(heights, pacific, -1, i, 0);
            populateVisitMatrix(heights, atlantic, -1, i, columns - 1);
        }

        for (int i = 0; i < columns; i++) {
            populateVisitMatrix(heights, pacific, -1, 0, i);
            populateVisitMatrix(heights, atlantic, -1, rows - 1, i);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    private void populateVisitMatrix(int[][] heights, boolean[][] visitedMatrix, int prevHeight, int row, int col) {
        if (row < 0 || col < 0 || row >= heights.length || col >= heights[0].length || visitedMatrix[row][col] || heights[row][col] < prevHeight) {
            return;
        }
        int currentHeight = heights[row][col];
        visitedMatrix[row][col] = true;
        populateVisitMatrix(heights, visitedMatrix, currentHeight, row + 1, col);
        populateVisitMatrix(heights, visitedMatrix, currentHeight, row - 1, col);
        populateVisitMatrix(heights, visitedMatrix, currentHeight, row, col + 1);
        populateVisitMatrix(heights, visitedMatrix, currentHeight, row, col - 1);
    }
}
