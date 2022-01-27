package com.code.kai.leetcode.curated75.easy.bits;

/**
 * @author Mohan Sharma
 */
public class ReverseBits {

    public static int reverseBits(int n) { //010
        int result = 0;
        for (int i=0; i< 31; i++) {
            int setBit = (n >> i) & 1;
            result = (result | setBit) << 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(1));
    }
}
