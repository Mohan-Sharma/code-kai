package com.code.kai.leetcode.curated75.easy.strings;

/**
 * @author Mohan Sharma
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            char charAtStart = Character.toLowerCase(s.charAt(start));
            char charAtEnd = Character.toLowerCase(s.charAt(end));
            if (!(charAtStart >= 'a' && charAtStart <= 'z') && !(charAtStart >= '0' && charAtStart <= '9'))
                start++;
            else if (!(charAtEnd >= 'a' && charAtEnd <= 'z') && !(charAtEnd >= '0' && charAtEnd <= '9'))
                end--;
            else if (charAtStart != charAtEnd)
                return false;
            else {
                start++;
                end--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));
    }
}
