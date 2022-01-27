package com.code.kai.leetcode.curated75.easy.bits;

/**
 * @author Mohan Sharma
 */
public class NumberOf1Bits {
    public static int hammingWeight(int n) {
        int count = 0;
        int pivot = 1;
        while (n != 0) {
            count += (n & pivot);
            n >>>= pivot;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(15));
    }
}
