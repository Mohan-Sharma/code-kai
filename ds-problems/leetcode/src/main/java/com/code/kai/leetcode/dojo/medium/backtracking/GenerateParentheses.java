package com.code.kai.leetcode.dojo.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class GenerateParentheses {

    // Open as long as open < n and close as long as close < open and add to result if substr length == 2*n
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisRecursively(0, 0, n, result, "");
        return result;
    }

    private void generateParenthesisRecursively(int open, int close, int totalLength, List<String> result, String subStr) {
        if (subStr.length() == 2 * totalLength) {
            result.add(subStr);
            return;
        }

        if (open < totalLength) {
            generateParenthesisRecursively(open + 1, close, totalLength, result, subStr + "(");
        }
        if (close < open) {
            generateParenthesisRecursively(open, close + 1, totalLength, result, subStr + ")");
        }
    }

    public static void main(String[] args) {
        System.out.println(new GenerateParentheses(). generateParenthesis(3));
    }
}
