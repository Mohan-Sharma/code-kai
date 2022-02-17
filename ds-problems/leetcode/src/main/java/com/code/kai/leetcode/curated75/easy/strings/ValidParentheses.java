package com.code.kai.leetcode.curated75.easy.strings;

import java.util.Stack;

/**
 * @author Mohan Sharma
 */
public class ValidParentheses {

    public static boolean isValid(String s) {
        Stack<Character> space = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (space.isEmpty() || space.pop() != '(')
                    return false;
            } else if (c == '}') {
                if (space.isEmpty() || space.pop() != '{')
                    return false;
            } else if (c == ']') {
                if (space.isEmpty() || space.pop() != '[')
                    return false;
            } else space.add(c);
        }
        return space.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("[{(}]"));
    }
}
