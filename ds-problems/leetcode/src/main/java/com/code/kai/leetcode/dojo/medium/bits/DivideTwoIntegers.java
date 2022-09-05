package com.code.kai.leetcode.dojo.medium.bits;

/**
 * @author Mohan Sharma
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {

        int result = 0;

        if (dividend == 1 << 31 && divisor == -1)
            return Integer.MAX_VALUE;
        int sign = dividend < 0 == divisor < 0 ? 1 : -1;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (divisor <= dividend) {
            int count = 1, pivot = divisor;
            while (pivot << 1  <= dividend) {
                pivot = pivot << 1;
                count = 1 << count;
            }
            result += count;
            dividend -= pivot;
        }
        return sign * result;
    }

    public static void main(String[] args) {
        System.out.println(new DivideTwoIntegers().divide(7, -3));
    }
}
