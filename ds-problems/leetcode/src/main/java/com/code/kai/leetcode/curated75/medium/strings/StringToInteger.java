package com.code.kai.leetcode.curated75.medium.strings;

/**
 * @author Mohan Sharma
 */
public class StringToInteger {
    // handle 4 cases
    // 1. discards all leading whitespaces
    // 2. sign of the number
    // 3. overflow
    // 4. invalid input
    public int myAtoi(String s) {
        int result = 0, start = 0;
        while (start < s.length() && s.charAt(start) == ' ')
            start++;
        if (s.length() == 0 || s.length() <= start) {
            return result;
        }
        boolean isNegative = false;
        if (s.charAt(start) == '-' || s.charAt(start) == '+') {
            isNegative = s.charAt(start) == '-';
            start++;
        }
        while (start < s.length() && s.charAt(start) >= '0' && s.charAt(start) <= '9') {
            // Integer.MAX_VALUE / 10 gives 214748364 now it does not matter if it is -2147483648 or 2147483647
            // we have already checked till second last digit if the last digit is > 7 means it won't fit in max_value but
            // let's say last digit is 8 or > 8 in either case we should return min_value
            // hence for this question according to constraints > 7 conditions should work fine
            if ((result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && s.charAt(start) - '0' > 7)) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = 10 * result + (s.charAt(start++) - '0');
        }
        return isNegative ? result * -1 : result;
    }

    public static void main(String[] args) {
        System.out.println(new StringToInteger().myAtoi("-2147483648"));
    }
}
