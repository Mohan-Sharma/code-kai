package com.code.kai.leetcode.curated75.easy.strings;

public class HaystackNeedle {
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int h = 0;
        int startIndex = -1;
        int n = 0;
        while(h < haystack.length()) {
            if (haystack.charAt(h) == needle.charAt(n)) {
                startIndex = h;
                while (h < haystack.length() && n < needle.length()) {
                    if (haystack.charAt(h + 1) == needle.charAt(n + 1)) {
                        h++;
                        n++;
                        if (n == needle.length())
                            return startIndex;
                    } else {
                        n = 0;
                        startIndex = -1;
                        break;
                    }
                }
            } else {
                h++;
            }
        }
        if (n == needle.length())
            return startIndex;
        else
            return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issip"));
    }
}
