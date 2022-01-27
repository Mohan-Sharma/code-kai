package com.code.kai.leetcode.curated75.easy.strings;

public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int start = 0; int end = s.length() - 1;
        while (start < end) {
            int f = s.charAt(start);
            int l = s.charAt(end);
            if ((f < 48 || f > 57) && (f < 'a' || f > 'z')) {
                start++;
            }
            else if ((l < 48 || l > 57) &&  (l < 'a' || l > 'z')) {
                end--;
            } else {
                if ( f != l)
                    return false;
                start++;
                end--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("PP"));
    }
}
