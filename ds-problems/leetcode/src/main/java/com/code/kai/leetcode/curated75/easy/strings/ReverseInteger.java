package com.code.kai.leetcode.curated75.easy.strings;

public class ReverseInteger {
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result > Integer.MAX_VALUE/10 || result > Integer.MAX_VALUE/10 && x%10 > 7)
                return 0;
            if (result < Integer.MIN_VALUE/10 || result < Integer.MIN_VALUE/10 && x%10 < -8)
                return 0;
            result = result*10 + x%10;
            x = x/10;
        }
        return result;
    }

    public static void main(String[] args) {
        int x = 1463847412; //214748369 //2147483647
        System.out.println(reverse(x));
    }
}
