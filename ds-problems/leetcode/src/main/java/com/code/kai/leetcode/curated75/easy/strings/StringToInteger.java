package com.code.kai.leetcode.curated75.easy.strings;

public class StringToInteger {
    public static int myAtoi(String str) {
        int result = 0;
        int len = str.length();
        int exp = 0;
        boolean negative = false;
        if (len == 0)
            return result;
        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(!Character.isDigit(ch) && result != 0) {
                return result;
            } else if (!Character.isSpaceChar(ch) &&
                    !Character.isDigit(ch) &&
                    ch != '-' &&
                    ch != '+' &&
                    result == 0) {
                return 0;
            } else if (Character.isDigit(ch) ||  ch == '-' || ch == '+') {
                if (ch == '-') {
                    if (len > i+ 1 && str.charAt(i+1) == '+' && result == 0){
                        return 0;
                    }
                    negative = true;
                } else if (ch == '+' && len > i+ 1 && str.charAt(i+1) == '-' && result == 0) {
                    return 0;
                } else {
                    int val = ch - '0';
                    if (result > Integer.MAX_VALUE / 10 || (result > Integer.MAX_VALUE / 10 && val > 7))
                        return Integer.MIN_VALUE;
                    if (result < Integer.MIN_VALUE / 10 || (result > Integer.MIN_VALUE / 10 && val < -8))
                        return Integer.MAX_VALUE;
                    result = result * 10 + val;
                }
            }
        }
        if (negative)
            result *= -1;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("+-4"));
    }
}
