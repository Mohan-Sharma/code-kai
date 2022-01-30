package com.code.kai.leetcode.curated75.medium.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class PrintAllSubsequence {

    public static void allSubsequences(String str, String sub, List<String> result, Map<String, List<String>> dp) {
        String key = sub + str;
        if (str.length() <= 0 && key.length() > 0 && !dp.containsKey(key)) {
            result.add(sub);
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            allSubsequences(str.substring(i +1), sub + c, result, dp);
            allSubsequences(str.substring(i +1), sub, result, dp);
        }
        if (!dp.containsKey(key)) {
            List<String> existingSubs = dp.getOrDefault(key, new ArrayList<>());
            existingSubs.add(sub);
            dp.put(key, existingSubs);
        }
    }

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> dp = new HashMap<>();
        allSubsequences("abc", "", result, dp);
        System.out.println(result);
    }
}
