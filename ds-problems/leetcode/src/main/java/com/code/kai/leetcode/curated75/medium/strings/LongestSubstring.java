package com.code.kai.leetcode.curated75.medium.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class LongestSubstring {

    //it works
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

    //little slow but works fine. Idea is whenever you find a duplicate clear the map
    // and move the pointer back to the last repeating char + 1
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();
        int length = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i=0; i<s.length(); ) {
            char c = s.charAt(i);
            if (charMap.containsKey(c)) {
                i = charMap.get(c) + 1;
                charMap.clear();
            } else {
                charMap.put(c, i++);
            }
            int currentLength = charMap.size();
            if (currentLength > length)
                length = currentLength;
        }
        return length;
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
        System.out.println(longestSubstringUsingTemplate("bbbbb"));
    }
}
