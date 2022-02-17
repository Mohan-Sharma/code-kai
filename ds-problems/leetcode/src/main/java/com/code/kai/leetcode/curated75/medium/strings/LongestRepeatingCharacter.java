package com.code.kai.leetcode.curated75.medium.strings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class LongestRepeatingCharacter {

    public static int characterReplacement(String s, int k) {
        if (s.length() < 2)
            return s.length();
        int j=0, i=0, max = -1, maxCount = 0;
        Map<Character, Integer> dp = new HashMap<>();
        while (i < s.length()) {
            dp.compute(s.charAt(i), (key, val) -> val == null ? 1 : val + 1);
            maxCount = dp.values().stream().max(Comparator.comparingInt(a -> a)).orElse(0);
            int length = i - j + 1;
            if (length - maxCount > k) {
                char leftMostChar = s.charAt(j);
                dp.put(leftMostChar, dp.getOrDefault(leftMostChar, 0) - 1);
                j++;
            }
            max = Math.max(max, i - j + 1);
            i++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }
}
