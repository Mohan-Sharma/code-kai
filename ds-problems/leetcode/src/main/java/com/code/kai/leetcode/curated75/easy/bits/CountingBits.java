package com.code.kai.leetcode.curated75.easy.bits;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class CountingBits {
    
    public static int[] countBits(int n) {
        if (n < 0) {
            return new int[] {};
        }
        int[] result = new int[n+1];
        int offset = 1;
        for (int i = 1; i <= n; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            result[i] = 1 + result[i - offset];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(3)));
    }
}
