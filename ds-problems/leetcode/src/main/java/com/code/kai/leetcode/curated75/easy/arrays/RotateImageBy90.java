package com.code.kai.leetcode.curated75.easy.arrays;

import java.util.Arrays;

public class RotateImageBy90 {
    public static void rotate(int[][] matrix) {
        int n = matrix.length - 1;
        for (int i = 0; i < n / 2; i++) {
            int last = n - i;
            for(int j = i; j < last; j++) {
                int offset = j - i;
                int top = matrix[i][j]; // save top

                // left -> top
                matrix[i][j] = matrix[last-offset][i];

                // bottom -> left
                matrix[last-offset][i] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[j][last];

                // top -> right
                matrix[j][last] = top; // right <- saved top
            }
        }
    }

    public static void rotateUsingTransposeMirror(int[][] matrix) {
        int n = matrix.length;
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}};
        rotateUsingTransposeMirror(matrix);
        for (int[] arr: matrix)
            System.out.println(Arrays.toString(arr));
    }
}
