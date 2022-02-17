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
            //char type 0~127 is enough for constraint 0 <= strs[i].length <= 100
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
       System.out.println(groupAnagrams(new String[] {"aab", "baa", "cab"}));
    }
}
