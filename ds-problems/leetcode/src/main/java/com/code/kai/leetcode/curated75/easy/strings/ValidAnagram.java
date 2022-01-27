package com.code.kai.leetcode.curated75.easy.strings;

import java.util.stream.IntStream;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        int[] storage = new int[26];
        for (char c : s.toCharArray()) {
            storage['z' - c]++;
        }

        for (char c : t.toCharArray()) {
            storage['z' - c]--;
        }

        return IntStream.of(storage).noneMatch(data -> data != 0);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("a", "ab"));
    }
}
