package com.code.kai.leetcode.curated75.medium.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohan Sharma
 */
public class LongestSubstring {


    // why O(2n) if string is abcdd, then first the string is scan till last d then it encounters duplicate so it removes one by one scanning
    // again till d so O(2n)
    public static int lengthOfLongestSubstring_O_2n(String s) {
        int length = s.length();
        if (length < 2)
            return length;
        int maxLength = 0;
        Set<Character> hash = new HashSet<>();
        int start = 0, end = 0;
        while (end < length) {
            char ech = s.charAt(end++);
            while (!hash.add(ech)) {
                hash.remove(s.charAt(start++));
            }
            maxLength = Math.max(maxLength, end - start);
        }
        return maxLength;
    }

    public static int longestSubstringStorage(String s) {
        if (s.length() < 2)
            return s.length();
        Map<Character, Integer> storage = new HashMap<>();
        int end = 0, start = 0, max = -1;
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (storage.containsKey(ch)) {
                start = Math.max(start, storage.get(ch) + 1);
            }
            max = Math.max(max, end - start + 1);
            storage.put(ch, end++);
        }
        return max;
    }

    public static int longestSubstringUsingTemplate(String s) {
        if (s.length() < 2)
            return s.length();
        int start = 0, end = 0, counter = 0, maxLength = -1;
        int[] dp = new int[128];

        while (end < s.length()) {
            char ch = s.charAt(end);
            if (dp[ch] > 0) {
                counter++;
            }
            dp[ch]++;
            end++;
            while (counter > 0) {
                char sch = s.charAt(start++);
                if (dp[sch] > 1)
                    counter--;
                dp[sch]--;
            }
            maxLength = Math.max(maxLength, end - start);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring_O_2n("abcabcbb"));
    }
}
