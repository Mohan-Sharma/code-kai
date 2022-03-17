package com.code.kai.leetcode.curated75.medium.strings;

/**
 * @author Mohan Sharma
 */
public class LongestRepeatingCharacter {

    public static int characterReplacement(String s, int k) {
        if (s.length() < 2)
            return s.length();
        int start=0, end=0, maxLen = -1, maxCount = 0;
        int[] dp = new int[26];
        while (end < s.length()) {
            int ch = s.charAt(end) - 'A';
            dp[ch]++;
            end++;
            maxCount = Math.max(maxCount, dp[ch]);
            while (end - start - maxCount > k) {
                int leftMostChar = s.charAt(start++) - 'A';
                dp[leftMostChar]--;
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }
}
