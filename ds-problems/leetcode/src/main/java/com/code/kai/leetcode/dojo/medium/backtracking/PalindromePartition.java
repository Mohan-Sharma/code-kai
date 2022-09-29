package com.code.kai.leetcode.dojo.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class PalindromePartition {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partitionBacktracking(s,0, result, new ArrayList<>());
        return result;
    }

    void partitionBacktracking(String s, int start, List<List<String>> result, List<String> set) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(set));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String substring = s.substring(start, i + 1);
            if (isPalindrome(substring)) {
                set.add(substring);
                partitionBacktracking(s, i + 1, result, set);
                set.remove(set.size() - 1);
            }
        }
    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 0)
            return false;
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            else {
                start++;
                end--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartition().partition("cdd"));
    }
}
