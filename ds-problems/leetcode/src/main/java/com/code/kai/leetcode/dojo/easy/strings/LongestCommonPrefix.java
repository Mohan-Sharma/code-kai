package com.code.kai.leetcode.dojo.easy.strings;

/**
 * @author Mohan Sharma
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // indexOf returns 0 when both string are same, so it returns the first occurrence index
            // of first matching character only when both strings are same
            //while (strs[i].indexOf(prefix) != 0) {
            // starts with will return true only when substring contains in a string
            // e.g if prefix is flow and string is flower it will return true but
            // if prefix is flower and string is flow it will be false hence we start
            // trimming flower from the end at each iteration of the loop
            while (!strs[i].startsWith(prefix)) {
                // at each iteration the last character is removed sine substring's endIndex is exclusive
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[] {"flower","flow","flight"}));
    }
}
