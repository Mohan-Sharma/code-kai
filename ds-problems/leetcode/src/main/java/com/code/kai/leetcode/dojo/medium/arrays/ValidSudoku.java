package com.code.kai.leetcode.dojo.medium.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohan Sharma
 */
public class ValidSudoku {

    //Since we need to check if value is unique through the row/col and box.
    //Each box can be formed if we do i/3. j/3 so first box is 0,0 then 0,1 then 0,2 etc
    // Since we need to check uniqueness we can have set and let's put the value in such
    // a way that we can differentiate the val in a rol or col or box e.g. 1 in row 0 or
    // 1 in col 0 or 1 in box 0,0 etc. Next time 1 comes in row 0 it will return false on add
    public boolean isValidSudoku(char[][] board) {
        Set<String> memory = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];
                if (val == '.')
                    continue;
                if (
                        (!memory.add(val + " in row " + i)) ||
                                (!memory.add(val + " in col " + j)) ||
                                (!memory.add(val + " in box " + (i/3 +","+j/3)))
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudokuWithLotOfSpace(char[][] board) {
        Map<String, Set<Character>> dp = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            dp.put("r-" + i, new HashSet<>());
            dp.put("c-" + i, new HashSet<>());
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dp.put("r-" + i + "c-" + j, new HashSet<>());
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];
                if (val == '.')
                    continue;
                if (!dp.get("r-" + i).add(val) ||
                        !dp.get("c-" + j).add(val) ||
                        !dp.get("r-" + i/3 + "c-" + j/3).add(val))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]
                {{'5','3','.','.','7','.','.','.','.'}
                        ,{'6','.','.','1','9','5','.','.','.'}
                        ,{'.','9','8','.','.','.','.','6','.'}
                        ,{'8','.','.','.','6','.','.','.','3'}
                        ,{'4','.','.','8','.','3','.','.','1'}
                        ,{'7','.','.','.','2','.','.','.','6'}
                        ,{'.','6','.','.','.','.','2','8','.'}
                        ,{'.','.','.','4','1','9','.','.','5'}
                        ,{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(new ValidSudoku().isValidSudokuWithLotOfSpace(board));
    }
}