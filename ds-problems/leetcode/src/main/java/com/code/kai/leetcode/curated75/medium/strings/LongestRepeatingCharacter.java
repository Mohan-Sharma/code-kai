package com.code.kai.leetcode.curated75.medium.strings;

/**
 * @author Mohan Sharma
 */
public class LongestRepeatingCharacter {

    public static int characterReplacement(String s, int k) {
        if (s.length() < 2)
            return s.length();
        int start=0, end=0, maxLen = -1, maxCount = 0;
        // since all uppercase letter we can always fit the characters
        // in an array of length 26
        int[] dp = new int[26];
        while (end < s.length()) {
            // get the current iteration character and put it to space
            int ch = s.charAt(end) - 'A';
            dp[ch]++;
            end++;
            // get the maximum count of character till this point
            // e.g. for AAB
            // when end = 0, maxCount will be 1 since dp['A'] will be 1
            // when end = 1, maxCount will be 2 since dp['A'] will be 2 now
            // when end = 2, maxCount will be 2 since dp['B'] is 1 but maxCount of previous 'A' was 2
            maxCount = Math.max(maxCount, dp[ch]);
            // if length at this point of iteration: ((end - start) - max count of any char) > k
            // means the string is no longer eligible to count longest repeating char string, so we
            // might need to push out some char from the front to make it eligible again
            if (end - start - maxCount > k) {
                int leftMostChar = s.charAt(start++) - 'A';
                dp[leftMostChar]--;
                // since maxLength == maxCount + k so finding maxLength basically is the same as finding maxCount.
                // Hence, it won't impact the ans even if maxCount is not reduced, so we don't have decrease maxCount when start++.
                // bcs decreasing does not impact ans but increasing maxCount does
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABAA", 1));
    }
}
