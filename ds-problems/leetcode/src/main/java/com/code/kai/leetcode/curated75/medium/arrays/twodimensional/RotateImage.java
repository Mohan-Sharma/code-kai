package com.code.kai.leetcode.curated75.medium.arrays.twodimensional;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class RotateImage {

    public static void rotate(int[][] matrix) {
        int colEnd = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < colEnd; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < colEnd / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][colEnd - j - 1];
                matrix[i][colEnd - j - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
