package com.code.kai.leetcode.curated75.easy.bits;

/**
 * @author Mohan Sharma
 */
public class SumWithoutOperator {

    public int getSum(int a, int b) {
        if (b == 0)
            return a;
        return getSum(a^b, a&b << 1);
    }
}
