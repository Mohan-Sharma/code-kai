package com.code.kai.leetcode.curated75.medium.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class LongestSubstring {

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

    private static void getIndexOfFirstDuplicate(String substring) {
        for (int i=0; i<substring.length(); i++) {

        }
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdfdabc"));
    }
}
