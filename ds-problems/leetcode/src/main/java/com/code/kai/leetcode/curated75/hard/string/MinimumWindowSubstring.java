package com.code.kai.leetcode.curated75.hard.string;

/**
 * @author Mohan Sharma
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s.equals(t))
            return t;
        int start = 0, end = 0, need = t.length(), have = 0;
        int[] having = new int[128];
        int[] needing = new int[128];
        String res = "";
        t.chars().forEach(c -> needing[c]++);
        while (end < s.length()) {
            char ch = s.charAt(end);
            having[ch]++;
            end++;
            if (needing[ch] > 0 && having[ch] <= needing[ch]) {
                have++;
            }
            while (have == need) {
                if (res.length() > end - start || res.length() == 0)  {
                    res = s.substring(start, end);
                }
                char sch = s.charAt(start++);
                having[sch]--;
                if (needing[sch] > 0 && having[sch] < needing[sch])
                    have--;
            }
        }
        return res;
    }

    public static String minWindowUsingTemplate(String s, String t) {
        if (s.equals(t))
            return t;
        int start = 0, end = 0, counter = t.length();
        int[] dp = new int[128];
        String res = "";
        t.chars().forEach(c -> dp[c]++);
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (dp[ch] > 0) {
                counter--;
            }
            dp[ch]--;
            end++;
            while (counter == 0) {
                if (res.length() > end - start || res.length() == 0)  {
                    res = s.substring(start, end);
                }
                char sch = s.charAt(start++);
                dp[sch]++;
                if (dp[sch] > 0)
                    counter++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(minWindowUsingTemplate("ADOBECODEBANC", "ABC"));
    }
}
