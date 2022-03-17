package com.code.kai.leetcode.curated75.medium.strings;

/**
 * @author Mohan Sharma
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        // single character string or empty string is always palindromic
        if (s.length() < 2) {
            return s;
        }
        String sub = "";
        // start at i index and check by expanding in both direction of left and right
        for (int i = 0; i < s.length(); i++) {
            // check for odd length substring e.g. AMAMB
            // result will MAM but if we want to get even length substr like AMAAMB
            // this method won't work but below method will work
            String first = getPalindromicSubString(s, i, i);
            String second = getPalindromicSubString(s, i, i+1);
            // get the longer substring and update result
            String longStr = first.length() > second.length() ? first : second;
            if (longStr.length() > sub.length()) {
                sub = longStr;
            }
        }
        return sub;
    }

    private String getPalindromicSubString(String str, int start, int end) {
        // at some ith index single char is always palindrome so expand
        // start to left and end to right to check if the adjacent chars are palindrome
        while (start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)) {
            start--;
            end++;
        }
        // we know max length for 0-based index system is end - start + 1 but in the above loop,
        // start & end are moved by total 2 positions in either side so original len will be
        // end - start + 1 - 2
        // again since start was decremented by 1 so original start position will be start + 1;
        int beginIndex = start + 1;
        int length = end - start - 1;
        return str.substring(beginIndex, beginIndex + length);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("aba"));
    }
}
