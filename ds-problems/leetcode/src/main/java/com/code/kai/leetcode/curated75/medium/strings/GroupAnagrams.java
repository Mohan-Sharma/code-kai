package com.code.kai.leetcode.curated75.medium.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class GroupAnagrams {

    //Brute force way would be to get a string then iterate over the remaining string check if both
    // are anagram and store in map of list it would be O(n^3) // Map<String.valueOf(chars[]), List<String>>
    // If we think through all we need is know if the content of the anagram string are similar
    // can we use hashing in such a way that even if different order characters but same count of chars
    // always give same hash, then create a hash key to group the strings
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
