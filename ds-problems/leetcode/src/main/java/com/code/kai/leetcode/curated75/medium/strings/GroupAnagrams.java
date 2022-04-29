package com.code.kai.leetcode.curated75.medium.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> space = new HashMap<>();
        for (String word : strs) {
            // char maximum value is 65535 range (0 - 65535) and even if all characters are same
            // according to constraint max will be 100, 0 <= strs[i].length <= 100
            // hence char array should not be a problem
            // error when chars[0] = 65535 + 1;
            // if constraint increase use int[] chars = new int[26]; and String key = Arrays.toString(chars);
            char[] chars = new char[26];
            for (char c : word.toCharArray()) {
                chars[c - 'a']++;
            }

            String key = String.valueOf(chars);
            List<String> anagrams = space.getOrDefault(key, new ArrayList<>());
            anagrams.add(word);
            space.put(key, anagrams);
        }
        return new ArrayList<>(space.values());
    }

    public static void main(String[] args) {
       System.out.println(groupAnagrams(new String[] {"aaa", "aaa", "cab"}));
    }
}
