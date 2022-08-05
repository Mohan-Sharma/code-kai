package com.code.kai.leetcode.curated75.medium.strings;

/**
 * @author Mohan Sharma
 */
public class CountPalindromicSubstrings {

    public int countSubstrings(String s) {
        int result = 0;
        // start at i index and check by expanding in both direction of left and right
        for (int i = 0; i < s.length(); i++) {
            // for odd length substring e.g. MAM
            // MAM will be counted as palindromic but MAAM will be ignored
            result += getPalindromicSubStringCount(s, i, i);
            // but this method will handle MAAM as plaindromic but MAM will be ignored
            // hence we need to handle both even length and odd length counts
            result += getPalindromicSubStringCount(s, i, i+1);
        }
        return result;
    }

    private int getPalindromicSubStringCount(String str, int start, int end) {
        int count = 0;
        // at some ith index single char is always palindrome so increment count and expand
        // start to left and end to right to check if the adjacent chars are palindrome
        while (start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)) {
            start--;
            end++;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new CountPalindromicSubstrings().countSubstrings("MAAM"));
    }
}
