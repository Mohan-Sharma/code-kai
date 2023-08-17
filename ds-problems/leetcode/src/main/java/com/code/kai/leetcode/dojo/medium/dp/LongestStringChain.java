package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Mohan Sharma
 */
/*
    Problem: Suppose we have string array [a, b, ba, bca], longest string chain will be either [a, ba, bca] or [b, ba, bca]
    Constraints: string at index i and i + 1 of the result must have the same order of chars and only 1 char more in the i+1 string.
 */
public class LongestStringChain {

    /*
        Intuition: First thing is since we need the longest meaning we should start with the smallest string and go the longest string of the array
        so better to sort it.
        Now every string is itself a chain of 1 length now suppose [a, ba] is the input array initially they are individually chain of 1
        meaning at index 0 chain length is 1 and for index 1 is also chain length of 1 - dp[1, 1]
        Since string at index 1 fits the above criteria of constraint, it's chain of length should become 1 + dp[0] = dp[1, 2].
        So we are seeing the possibility of using LIS algo logic here
     */
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int len = words.length;
        int[] dp = new int[len];
        int maxLen = 1;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (fitsCriteria(words[j], words[i]) && dp[i] < 1 + dp[j]) {
                    dp[i] = 1 + dp[j];
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    private boolean fitsCriteria(String first, String second) {
        if (first.length() + 1 != second.length())
            return false;
        int i = 0, j = 0;
        while (i < first.length() && j < second.length()) {
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else j++;
        }
        return i == first.length() && (j == second.length() || j == second.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new LongestStringChain().longestStrChain(new String[] {"xbc","pcxbcf","xb","cxbc","pcxbc"}));
    }
}
