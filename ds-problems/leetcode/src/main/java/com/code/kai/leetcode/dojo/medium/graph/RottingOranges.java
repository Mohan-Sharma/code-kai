package com.code.kai.leetcode.dojo.medium.graph;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class RottingOranges {

    // Problem: The ask is to process the next minute all the rotten oranges. So DFS won't work since in DFS
    // we will process one at a time and in case of 2 rotten oranges in the same row but distance apart can't be
    // process in the first part or let's say we have rotten oranges at grid[0][0], grid[3][0], grid[0][3] ideally
    // in the next min all of them should be processed.
    // Solution: We use BFS or a queue which at say min 1: process all the above 3 rotten oranges, after process all 3 increment time
    // and add to queue their adjacent one's for next min processing only if they are not rotten and within bound
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, freshCount = 0, time = 0;

        final Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2)
                    q.add(new int[] {i, j});
                if (grid[i][j] == 1)
                    freshCount++;
            }
        }

        while (!q.isEmpty() && freshCount > 0) {
            int batchSize = q.size();
            for (int i = 0; i < batchSize; i++) {
                int[] point = q.poll();
                List<int[]> list = List.of(new int[]{0, 1}, new int[]{0, -1}, new int[]{1, 0}, new int[]{-1, 0});
                for (int[] adjacent : list) {
                    int row = point[0] + adjacent[0], col = point[1] + adjacent[1];
                    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != 1)
                        continue;
                    grid[row][col] = 2;
                    q.add(new int[] {row, col});
                    freshCount--;
                }
            }
            time++;
        }
        return freshCount == 0 ? time : -1;
    }

    public static void main(String[] args) {
        System.out.println(new RottingOranges().orangesRotting(new int[][] {{2,1,1},{0,1,1},{1,0,1}}));
    }
}
