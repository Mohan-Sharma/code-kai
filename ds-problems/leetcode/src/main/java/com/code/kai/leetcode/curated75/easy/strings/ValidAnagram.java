package com.code.kai.leetcode.curated75.easy.strings;

import java.util.stream.IntStream;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        final int[] dp = new int[26];
        s.chars().forEach(c -> dp[c - 'a']++);
        t.chars().forEach(c -> dp[c - 'a']--);
        return IntStream.of(dp).noneMatch(data -> data != 0);
    }

    public static void main(String[] args) {
        System.out.println(new ValidAnagram().isAnagram("ba", "ab"));
    }
}
