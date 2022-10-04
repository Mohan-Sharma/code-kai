package com.code.kai.leetcode.dojo.medium.graph;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class SurroundedRegions {

    // Problems says - capture all regions and convert o to x and return the board.
    // Here a regions is valid is o's are surrounded by x in 4 sides up, down, left, right.
    // So the solution would be to find invalid areas near to border and mark all the connected one's
    // left,down, up, right. So the left out is the ans
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
        char[][] board = new char[][]{{'X','X','X','X'},{'X','X','O','X'},{'O','O','X','X'},{'X','X','X','X'}};
        new SurroundedRegions().solve(board);

        System.out.println(Arrays.deepToString(board));
    }
}
