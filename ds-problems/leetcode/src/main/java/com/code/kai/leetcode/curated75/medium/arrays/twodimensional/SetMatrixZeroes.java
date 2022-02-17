package com.code.kai.leetcode.curated75.medium.arrays.twodimensional;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        boolean firstColContainsZero = checkIfFirstColContainsZero(matrix);
        boolean firstRowContainsZero = checkIfFirstRowContainsZero(matrix);
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstColContainsZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firstRowContainsZero) {
            Arrays.fill(matrix[0], 0);
        }
    }

    private static boolean checkIfFirstRowContainsZero(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0)
                return true;
        }
        return false;
    }

    private static boolean checkIfFirstColContainsZero(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
