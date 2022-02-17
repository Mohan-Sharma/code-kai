package com.code.kai.leetcode.curated75.medium.strings;

/**
 * @author Mohan Sharma
 */
public class LongestPalindromicSubstring {

    int start = 0, maxLen = -1, count=0;

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            extend(s, i, i);
            extend(s, i, i+1);
        }
        System.out.println(count);
        return s.substring(start, start + maxLen);
    }

    private void extend(String str, int i, int j) {
        //at ith index single char is always palindrome or expand i to left and right to check if the adjacent chars are palindrome
        while (i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)) {
            i--;
            j++;
            count++;
        }
        // we know maxLen = j - i + 1 but in the above loop, i & j are moved by total 2 positions in either side so original len= j - i + 1 -2
        if (maxLen < j - i -1) {
            start = i + 1; // since i was decremented by 1 so original position will be i + 1;
            maxLen = j - i - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
    }
}
