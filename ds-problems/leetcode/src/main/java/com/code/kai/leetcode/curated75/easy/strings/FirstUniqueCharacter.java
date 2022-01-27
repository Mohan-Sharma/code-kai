package com.code.kai.leetcode.curated75.easy.strings;

public class FirstUniqueCharacter {
    public static int firstUniqChar(String s) {
        int[] array = new int[123];
        for (char c : s.toCharArray()) {
            array[c]++;
        }
        for (int i=0; i<s.length(); i++) {
            if (array[s.charAt(i)] == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("aadadaad"));
    }
}
