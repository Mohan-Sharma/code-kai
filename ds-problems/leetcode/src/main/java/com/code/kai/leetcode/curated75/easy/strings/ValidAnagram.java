package com.code.kai.leetcode.curated75.easy.strings;

import java.util.stream.IntStream;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        int[] space = new int[26];
        for (char c : s.toCharArray()) {
            space['z' - c]++;
        }

        for (char c : t.toCharArray()) {
            space['z' - c]--;
        }

        return IntStream.of(space).noneMatch(data -> data != 0);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("zb", "ab"));
    }
}
