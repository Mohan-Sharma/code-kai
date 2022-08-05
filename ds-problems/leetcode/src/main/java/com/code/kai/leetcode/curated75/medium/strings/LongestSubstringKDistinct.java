package com.code.kai.leetcode.curated75.medium.strings;

/**
 * @author Mohan Sharma
 */
public class LongestSubstringKDistinct {

    public int lengthOfLongestSubstringKDistinctUsingTemplate(String s, int k) {
        if (s.length() < 1) {
            return 0;
        }
        if (k < 1) {
            return 0;
        }
        int start = 0, end = 0, counter = 0, maxLen = -1;
        int[] dp = new int[128];
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (dp[ch] == 0) {
                counter++;
            }
            dp[ch]++; end++;
            while (counter > k) {
                char sch = s.charAt(start++);
                if (dp[sch] == 1) {
                    counter--;
                }
                dp[sch]--;
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }
}
