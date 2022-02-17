package com.code.kai.leetcode.curated75.medium.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class LongestSubstring {

    public static int longestSubstringStorage(String s) {
        if (s.length() < 2)
            return s.length();
        Map<Character, Integer> storage = new HashMap<>();
        int i = 0, j = 0, max = -1;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (storage.containsKey(ch)) {
                j = Math.max(j, storage.get(ch) + 1);
            }
            max = Math.max(max, i - j + 1);
            storage.put(ch, i++);
        }
        return max;
    }

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

    public static int longestSubstring(String s) {
        if (s.length() < 2)
            return s.length();
        int i = 0, j= 1, length = 1;
        String sub =  "" + s.charAt(0);

        while (j < s.length()) {
            if (sub.indexOf(s.charAt(j)) >= 0) {
                char ch = s.charAt(j);
                while (i < j) {
                    if (sub.charAt(i) == ch) {
                        sub = sub.substring(i + 1) + ch;
                        i = 0;
                        break;
                    }
                    i++;
                }
            } else {
                sub += s.charAt(j);
                length = Math.max(length, sub.length());
            }
            j++;
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring("bbbbb"));
    }
}
