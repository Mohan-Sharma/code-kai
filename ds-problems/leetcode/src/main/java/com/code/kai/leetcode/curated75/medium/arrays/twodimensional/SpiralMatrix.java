package com.code.kai.leetcode.curated75.medium.arrays.twodimensional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        int rowBegin = 0, rowEnd = matrix.length, colBegin = 0, colEnd = matrix[0].length;
        List<Integer> result = new ArrayList<>(rowEnd*colEnd);
        while (rowBegin < rowEnd && colBegin < colEnd) {
            //navigate right
            for (int i = colBegin; i < colEnd; i++) {
                result.add(matrix[rowBegin][i]);
            }
            //processed first row so
            rowBegin++;
            //navigate down
            for (int i = rowBegin; i < rowEnd; i++) {
                result.add(matrix[i][colEnd - 1]);
            }
            //processed last col so
            colEnd--;
            //navigate left
            if (rowBegin < rowEnd) {
                for (int i = colEnd - 1; i >= colBegin; i--) {
                    result.add(matrix[rowEnd - 1][i]);
                }
            }
            //processed last row so
            rowEnd--;

            if (colBegin < colEnd) {
                //navigate top
                for (int i = rowEnd - 1; i >= rowBegin; i--) {
                    result.add(matrix[i][colBegin]);
                }
            }
            // processed first col so
            colBegin++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }
}
