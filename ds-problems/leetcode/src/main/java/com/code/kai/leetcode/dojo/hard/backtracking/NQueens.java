package com.code.kai.leetcode.dojo.hard.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class NQueens {

    // Idea: for the first row there will be 4 places or cols to fill Q. Let's say we fill in first col and move to second row to again have
    // 4 places to fill Q but should fulfill the condition that Queens cannot be along the same row, same col and along diagonals
    // Since there will be 4 positions to fill in each recursion use for-loop 1..4; Use a char array and initial values of '.'
    // then at a position which satisfies the condition fill with 'Q' then convert to string and add to list, fills the condition arrays, then go for next row.
    // suppose on the next row no suitable positions is left, then backtrack and remove the previous string and for new position construct new string and so on.
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        nQueenBacktracking(n, 0, result, new ArrayList<>(), new boolean[n], new boolean[2*n - 1], new boolean[2*n - 1]);
        return result;
    }

    void nQueenBacktracking(int n, int row, List<List<String>> result, List<String> sets, boolean[] colUsed, boolean[] fD, boolean[] sD) {
        if (row >= n) {
            result.add(new ArrayList<>(sets));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (colUsed[i] || fD[row - i + n - 1] || sD[row + i]) {
                continue;
            }
            char[] chars = new char[4];
            Arrays.fill(chars, '.');
            chars[i] = 'Q';
            String string = String.valueOf(chars);
            sets.add(string);
            colUsed[i] = true;
            fD[row - i + n - 1] = true;
            sD[row + i] = true;
            nQueenBacktracking(n, row + 1, result, sets, colUsed, fD, sD);
            sets.remove(string);
            colUsed[i] = false;
            fD[row - i + n - 1] = false;
            sD[row + i] = false;
        }
    }

    public static void main(String[] args) {
        List<List<String>> result = new NQueens().solveNQueens(4);
        System.out.println(result);
    }
}
