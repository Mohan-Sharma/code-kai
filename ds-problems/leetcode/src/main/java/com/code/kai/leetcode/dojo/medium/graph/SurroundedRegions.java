package com.code.kai.leetcode.dojo.medium.graph;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class SurroundedRegions {

    // Problems says - capture all valid regions (a region is valid if the group/single O is surrounded by X in 4 directions: up, down, left, right)
    // and then replace O with X for these valid regions and return the board.
    // So the solution would be to find invalid areas(anything on the border or not surrounded by X in all 4 directions) and mark all the connected one's
    // left, down, up, right. So the left out is the ans
    public void solve(char[][] board) {
        int rows = board.length, cols = board[0].length;

        //mark invalid areas
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // process only the borders
                if ((List.of(0, rows - 1).contains(i) || List.of(0, cols - 1).contains(j)) && board[i][j] == 'O') {
                    markAreaInvalid(board, i, j);
                }
            }
        }

        //mark remaining area, in other words flip the valid areas from O to X
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // process only the borders
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        // unmark invalid areas to original O
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // process only the borders
                if (board[i][j] == 'I') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void markAreaInvalid(char[][] board, int row, int col) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 'O')
            return;
        board[row][col] = 'I';

        markAreaInvalid(board, row + 1, col);
        markAreaInvalid(board, row - 1, col);
        markAreaInvalid(board, row, col + 1);
        markAreaInvalid(board, row, col - 1);
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'},{'X','O','X','X'}};
        new SurroundedRegions().solve(board);

        System.out.println(Arrays.deepToString(board));
    }
}
