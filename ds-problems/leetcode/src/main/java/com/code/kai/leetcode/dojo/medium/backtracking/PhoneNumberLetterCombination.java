package com.code.kai.leetcode.dojo.medium.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class PhoneNumberLetterCombination {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0)
            return result;
        letterCombinationsRecursively(0, result, constructMap(), digits,"");
        return result;
    }

    private Map<Character, List<Character>> constructMap() {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', List.of('a', 'b', 'c'));
        map.put('3', List.of('d', 'e', 'f'));
        map.put('4', List.of('g', 'h', 'i'));
        map.put('5', List.of('j', 'k', 'l'));
        map.put('6', List.of('m', 'n', 'o'));
        map.put('7', List.of('p', 'q', 'r', 's'));
        map.put('8', List.of('t', 'u', 'v'));
        map.put('9', List.of('w', 'x', 'y', 'z'));
        return map;
    }

    private void letterCombinationsRecursively(int index, List<String> result, Map<Character, List<Character>> map, String digits, String subStr) {
        if (subStr.length() == digits.length()) {
            result.add(subStr);
            return;
        }
        if (index >= digits.length())
            return;
        for (char c : map.get(digits.charAt(index))) {
            letterCombinationsRecursively(index + 1, result, map, digits, subStr + c);
        }
    }

    public static void main(String[] args) {
        System.out.println(new PhoneNumberLetterCombination().letterCombinations(""));
    }
}
