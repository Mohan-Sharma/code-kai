package com.code.kai.leetcode.curated75.medium.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class AllSubstring {
    public static List<String> allSubstrings(String string) {
        List<String> result = new ArrayList<>();
        generateSubstring(string, result, "", string.length() - 1);
        return result;
    }

    private static void generateSubstring(String string, List<String> result, String substring, int index) {
        if (string.length() == 0) {
            result.add(substring);
            return;
        }
        String newSub = string.charAt(index) + substring;
        generateSubstring(string.substring(0, index), result, newSub, index-1);
        generateSubstring(substring, result, "", substring.length() - 1);
    }

    public static void main(String[] args) {
        List<String> result = allSubstrings("test");
        for (String sub: result) {
            System.out.println(sub);
        }
    }
}
