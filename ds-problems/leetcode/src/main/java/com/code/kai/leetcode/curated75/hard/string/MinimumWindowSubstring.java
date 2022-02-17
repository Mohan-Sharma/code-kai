package com.code.kai.leetcode.curated75.hard.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        Map<Character, Integer> dp = new HashMap<>();
        for (char c : t.toCharArray()) {
            dp.compute(c, (k, v) -> v == null ?  1 : v + 1);
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (dp.containsKey(c1)) {
                if (dp.get(c1) > 0)
                    counter--;
                dp.put(c1, dp.getOrDefault(c1, 0) - 1);
            }
            end++;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                final char c2 = s.charAt(start);
                if (dp.containsKey(c2)) {
                    dp.put(c2, dp.getOrDefault(c2, 0) + 1);
                    if (dp.get(c2) > 0)
                        counter++;
                }
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
